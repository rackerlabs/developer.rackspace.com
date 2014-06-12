import static org.jclouds.compute.predicates.NodePredicates.inGroup;
import static org.jclouds.rackspace.cloudloadbalancers.v1.domain.VirtualIP.Type.PUBLIC;
import static org.jclouds.rackspace.cloudloadbalancers.v1.domain.internal.BaseLoadBalancer.Algorithm.RANDOM;
import static org.jclouds.rackspace.cloudloadbalancers.v1.domain.internal.BaseNode.Condition.DISABLED;
import static org.jclouds.rackspace.cloudloadbalancers.v1.domain.internal.BaseNode.Condition.ENABLED;
import static org.jclouds.rackspace.cloudloadbalancers.v1.predicates.LoadBalancerPredicates.awaitAvailable;
import static org.jclouds.rackspace.cloudloadbalancers.v1.predicates.LoadBalancerPredicates.awaitDeleted;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.RunNodesException;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.Template;
import org.jclouds.openstack.nova.v2_0.domain.zonescoped.ZoneAndId;
import org.jclouds.rackspace.cloudloadbalancers.v1.CloudLoadBalancersApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.AccessRule;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.AddNode;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.ConnectionThrottle;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.CreateLoadBalancer;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.HealthMonitor;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.LoadBalancer;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.Node;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.AccessRuleApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.ConnectionApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.ContentCachingApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.ErrorPageApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.HealthMonitorApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.LoadBalancerApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.NodeApi;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.io.Closeables;

public class CloudLoadBalancers {
    // The jclouds Provider for the Rackspace Cloud Block Storage US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudloadbalancers-us");
    // jclouds refers to "regions" as "zones"
    public static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String LOAD_BALANCER_NAME = System.getProperty("loadBalancerName", "sample-load-balancer");
    public static final String SERVER_NAME = System.getProperty("serverName", "sample-server");
    public static final String GROUP_NAME = System.getProperty("groupName", "group");

    public static void main(String[] args) throws Exception {
        ComputeService computeService = ContextBuilder.newBuilder("rackspace-cloudservers-us")
            .credentials(USERNAME, API_KEY)
            .buildView(ComputeServiceContext.class).getComputeService();

        CloudLoadBalancersApi clbApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(USERNAME, API_KEY)
            .buildApi(CloudLoadBalancersApi.class);

        // create the server and load balancer nodes
        Set<? extends NodeMetadata> serverNode = createServer(computeService);
        Set<AddNode> loadBalancerNodes = createNodes(serverNode);

        // create a load balancer
        LoadBalancer loadBalancer = createLoadBalancer(clbApi, loadBalancerNodes);

        // create a health monitor
        createHealthMonitor(clbApi, loadBalancer);
        HealthMonitor healthMonitor = getHealthMonitor(clbApi, loadBalancer);

        // set load balancer features
        setThrottling(clbApi, loadBalancer);
        blacklistIPs(clbApi, loadBalancer);
        enableContentCaching(clbApi, loadBalancer);
        setCustomErrorPage(clbApi, loadBalancer);

        // deleted the nodes, load balancer and server
        deleteNodes(clbApi, loadBalancer);
        deleteLoadBalancer(clbApi, loadBalancer);
        deleteServer(computeService);

        deleteResources(computeService, clbApi);
   }

    private static Set<? extends NodeMetadata> createServer(ComputeService computeService) throws RunNodesException {
        ZoneAndId zoneAndId = ZoneAndId.fromZoneAndId(REGION, "performance1-1");
        Template template = computeService.templateBuilder()
            .locationId(REGION)
            .osDescriptionMatches(".*Ubuntu 12.04.*")
            .hardwareId(zoneAndId.slashEncode())
            .build();

        // This method will continue to poll for the server status and won't return until this server is ACTIVE
        Set<? extends NodeMetadata> serverNode = computeService.createNodesInGroup(GROUP_NAME, 1, template);

        return serverNode;
    }

    private static LoadBalancer createLoadBalancer(CloudLoadBalancersApi clbApi, Set<AddNode> addNodes) throws TimeoutException {
        CreateLoadBalancer createLB = CreateLoadBalancer.builder()
            .name(LOAD_BALANCER_NAME)
            .protocol("HTTP")
            .port(80)
            .algorithm(RANDOM)
            .nodes(addNodes)
            .virtualIPType(PUBLIC)
            .build();

        LoadBalancerApi lbApi = clbApi.getLoadBalancerApiForZone(REGION);
        LoadBalancer loadBalancer = lbApi.create(createLB);

        // Wait for the Load Balancer to become Active before moving on
        awaitAvailable(lbApi).apply(loadBalancer);
        return loadBalancer;
    }

    private static Set<AddNode> createNodes(Set<? extends NodeMetadata> serverNodes) {
        Set<AddNode> addLBNodes = Sets.newHashSet();

        AddNode node1 = AddNode.builder()
            .address("10.180.1.1")
            .condition(DISABLED)
            .port(80)
            .weight(20)
            .build();

        AddNode node2 = AddNode.builder()
            .address("10.180.1.2")
            .condition(ENABLED)
            .port(80)
            .weight(20)
            .build();

        addLBNodes.add(node1);
        addLBNodes.add(node2);
        for (NodeMetadata node : serverNodes) {
            String privateAddress = node.getPrivateAddresses().iterator().next();

            AddNode addNode = AddNode.builder()
                .address(privateAddress)
                .condition(ENABLED)
                .port(80)
                .weight(20)
                .build();

            addLBNodes.add(addNode);
        }

        return addLBNodes;
    }

    private static void createHealthMonitor(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        HealthMonitor healthMonitor = HealthMonitor.builder()
            .type(HealthMonitor.Type.CONNECT)
            .delay(3599)
            .timeout(30)
            .attemptsBeforeDeactivation(2)
            .build();

        clbApi.getHealthMonitorApiForZoneAndLoadBalancer(REGION, loadBalancer.getId())
            .createOrUpdate(healthMonitor);
    }

    private static HealthMonitor getHealthMonitor(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        HealthMonitorApi hmApi = clbApi.getHealthMonitorApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        HealthMonitor monitor = hmApi.get();

        return monitor;
    }

    private static void setThrottling(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        ConnectionThrottle throttle = ConnectionThrottle.builder()
            .maxConnectionRate(10000)
            .maxConnections(5000)
            .minConnections(2)
            .rateInterval(5)
            .build();

        ConnectionApi connectionApi = clbApi.getConnectionApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        connectionApi.createOrUpdateConnectionThrottle(throttle);
    }

    private static void blacklistIPs(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        AccessRule rule1 = AccessRule.deny("206.160.165.0/24");
        AccessRule rule2 = AccessRule.allow("206.160.165.0/2");
        AccessRule blacklisted = AccessRule.deny("0.0.0.0/0");

        List<AccessRule> accessList = ImmutableList.<AccessRule> of(rule1, rule2, blacklisted);

        AccessRuleApi accessRuleApi = clbApi.getAccessRuleApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        accessRuleApi.create(accessList);
    }

    private static void enableContentCaching(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        ContentCachingApi contentCachingApi = clbApi.getContentCachingApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        contentCachingApi.enable();
    }

    private static void setCustomErrorPage(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        ErrorPageApi errorPageApi = clbApi.getErrorPageApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());

        String content = "<html><body>Something went wrong...</body></html>";
        errorPageApi.create(content);
    }

    private static void deleteNodes(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) throws TimeoutException {
        NodeApi nodeApi = clbApi.getNodeApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        Set<Node> nodes = nodeApi.list().concat().toSet();

        Iterable<Integer> nodeIds = Iterables.transform(nodes, new NodeToId());
        nodeApi.remove(nodeIds);
    }

    private static void deleteLoadBalancer(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) throws TimeoutException {
        LoadBalancerApi lbApi = clbApi.getLoadBalancerApiForZone(REGION);
        lbApi.delete(loadBalancer.getId());
        awaitDeleted(lbApi).apply(loadBalancer);
    }

    private static void deleteServer(ComputeService computeService) {
        computeService.destroyNodesMatching(inGroup(GROUP_NAME));
    }

    private static class NodeToId implements Function<Node, Integer> {
        public Integer apply(Node node) {
            return node.getId();
        }
    }

    private static void deleteResources(ComputeService computeService, CloudLoadBalancersApi clbApi)
          throws IOException {
        Closeables.close(computeService.getContext(), true);
        Closeables.close(clbApi, true);
    }
}
