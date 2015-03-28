import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.io.Closeables;
import org.jclouds.ContextBuilder;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.CloudLoadBalancersApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.AccessRule;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.AddNode;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.ConnectionThrottle;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.CreateLoadBalancer;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.HealthMonitor;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.LoadBalancer;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.Node;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.VirtualIP;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.internal.BaseLoadBalancer;
import org.jclouds.rackspace.cloudloadbalancers.v1.domain.internal.BaseNode;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.AccessRuleApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.ConnectionApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.ContentCachingApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.ErrorPageApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.HealthMonitorApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.LoadBalancerApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.features.NodeApi;
import org.jclouds.rackspace.cloudloadbalancers.v1.predicates.LoadBalancerPredicates;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import static org.jclouds.rackspace.cloudloadbalancers.v1.predicates.LoadBalancerPredicates.awaitAvailable;
import static org.jclouds.rackspace.cloudloadbalancers.v1.predicates.LoadBalancerPredicates.awaitDeleted;

public class CloudLoadBalancers {
    // The jclouds Provider for the Rackspace Cloud Block Storage US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudloadbalancers-us");

    public static final String REGION = System.getProperty("region", "IAD");

    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static void main(String[] args) throws Exception {

        CloudLoadBalancersApi clbApi = authenticate(USERNAME, API_KEY);

        LoadBalancerApi lbApi = clbApi.getLoadBalancerApi(REGION);
        LoadBalancer loadBalancer = createLoadBalancer(lbApi);

        NodeApi nodeApi = clbApi.getNodeApi(REGION, loadBalancer.getId());
        createNodes(nodeApi);

        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        HealthMonitorApi healthMonitorApi = clbApi.getHealthMonitorApi(REGION, loadBalancer.getId());
        createHealthMonitor(healthMonitorApi);
        HealthMonitor healthMonitor = getHealthMonitor(healthMonitorApi);

        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        ConnectionApi connectionApi = clbApi.getConnectionApi(REGION, loadBalancer.getId());
        setThrottling(connectionApi);
        
        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        AccessRuleApi accessRuleApi = clbApi.getAccessRuleApi(REGION, loadBalancer.getId());
        blacklistIPs(accessRuleApi);

        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        ContentCachingApi contentCachingApi = clbApi.getContentCachingApi(REGION, loadBalancer.getId());
        enableContentCaching(contentCachingApi);

        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        ErrorPageApi errorPageApi = clbApi.getErrorPageApi(REGION, loadBalancer.getId());
        setCustomErrorPage(errorPageApi);

        deleteResources(clbApi, lbApi, loadBalancer);
    }

    public static CloudLoadBalancersApi authenticate(String username, String apiKey) {
        CloudLoadBalancersApi clbApi = ContextBuilder.newBuilder(PROVIDER)
                .credentials(USERNAME, API_KEY)
                .buildApi(CloudLoadBalancersApi.class);

        return clbApi;
    }

    public static LoadBalancer createLoadBalancer(LoadBalancerApi lbApi) throws TimeoutException {
        CreateLoadBalancer createLB = CreateLoadBalancer.builder()
            .name("My Load Balancer")
            .protocol("HTTP")
            .port(80)
            .algorithm(BaseLoadBalancer.Algorithm.RANDOM)
            .virtualIPType(VirtualIP.Type.PUBLIC)
            .build();

        LoadBalancer loadBalancer = lbApi.create(createLB);

        // Wait for the Load Balancer to become active before moving on
        if (!awaitAvailable(lbApi).apply(loadBalancer)) {
            throw new TimeoutException("Timeout on loadBalancer: " + loadBalancer); 
        }

        return loadBalancer;
    }

    public static void selectServers() {
        NovaApi novaApi = ContextBuilder.newBuilder("rackspace-cloudservers-us")
                .credentials(USERNAME, API_KEY)
                .buildApi(NovaApi.class);
        ServerApi serverApi = novaApi.getServerApi(REGION);

        Server server1 = serverApi.get("{serverId}");
        Server server2 = serverApi.get("{serverId}");
    }

    public static Set<AddNode> createNodes(NodeApi nodeApi) {
        AddNode node1 = AddNode.builder()
            .address("10.180.1.1")
            .condition(BaseNode.Condition.DISABLED)
            .port(80)
            .weight(20)
            .build();

        AddNode node2 = AddNode.builder()
            .address("10.180.1.2")
            .condition(BaseNode.Condition.ENABLED)
            .port(80)
            .weight(20)
            .build();

        Set<AddNode> addNodes = Sets.newHashSet();
        addNodes.add(node1);
        addNodes.add(node2);

        nodeApi.add(addNodes);

        return addNodes;
    }

    public static void createHealthMonitor(HealthMonitorApi healthMonitorApi) {
        HealthMonitor healthMonitor = HealthMonitor.builder()
            .type(HealthMonitor.Type.CONNECT)
            .delay(3599)
            .timeout(30)
            .attemptsBeforeDeactivation(2)
            .build();

        healthMonitorApi.createOrUpdate(healthMonitor);
    }

    public static HealthMonitor getHealthMonitor(HealthMonitorApi healthMonitorApi) {
        HealthMonitor healthMonitor = healthMonitorApi.get();

        return healthMonitor;
    }

    public static void setThrottling(ConnectionApi connectionApi) {
        ConnectionThrottle throttle = ConnectionThrottle.builder()
            .maxConnectionRate(10000)
            .maxConnections(5000)
            .minConnections(2)
            .rateInterval(5)
            .build();

        connectionApi.createOrUpdateConnectionThrottle(throttle);
    }

    public static void blacklistIPs(AccessRuleApi accessRuleApi) {
        AccessRule rule1 = AccessRule.deny("206.160.165.0/24");
        AccessRule rule2 = AccessRule.allow("206.160.165.0/2");
        AccessRule blacklisted = AccessRule.deny("0.0.0.0/0");

        List<AccessRule> accessList = ImmutableList.of(rule1, rule2, blacklisted);

        accessRuleApi.create(accessList);
    }

    public static void enableContentCaching(ContentCachingApi contentCachingApi) {
        contentCachingApi.enable();
    }

    public static void setCustomErrorPage(ErrorPageApi errorPageApi) {
        String content = "<html><body>Something went wrong...</body></html>";
        errorPageApi.create(content);
    }

    public static void deleteNodes(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) {
        NodeApi nodeApi = clbApi.getNodeApi(REGION, loadBalancer.getId());

        Set<Node> nodes = nodeApi.list().concat().toSet();

        Iterable<Integer> nodeIds = Iterables.transform(nodes, new NodeToId());
        nodeApi.remove(nodeIds);
    }

    public static void deleteLoadBalancer(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) throws TimeoutException {
        LoadBalancerApi lbApi = clbApi.getLoadBalancerApi(REGION);
        lbApi.delete(loadBalancer.getId());
        
        // Wait for the Load Balancer to be deleted
        if (!awaitDeleted(lbApi).apply(loadBalancer)) {
            throw new TimeoutException("Timeout on loadBalancer: " + loadBalancer); 
        }
    }

    public static class NodeToId implements Function<Node, Integer> {
        public Integer apply(Node node) {
            return node.getId();
        }
    }

    public static void deleteResources(CloudLoadBalancersApi clbApi, LoadBalancerApi lbApi, LoadBalancer loadBalancer)
          throws IOException, TimeoutException, InterruptedException {
        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        deleteNodes(clbApi, loadBalancer);

        waitForLoadBalancerToBecomeActive(lbApi, loadBalancer);
        deleteLoadBalancer(clbApi, loadBalancer);

        Closeables.close(clbApi, true);
    }

    public static void waitForLoadBalancerToBecomeActive(LoadBalancerApi lbApi, LoadBalancer loadBalancer)
        throws TimeoutException {
        if (!LoadBalancerPredicates.awaitAvailable(lbApi).apply(loadBalancer)) {
            throw new TimeoutException("Timeout on loadBalancer: " + loadBalancer);
        }
    }
}
