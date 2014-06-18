import static org.jclouds.openstack.nova.v2_0.predicates.ServerPredicates.awaitActive;
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
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Image;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.domain.ServerCreated;
import org.jclouds.openstack.nova.v2_0.features.ImageApi;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
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
import com.google.common.base.Predicate;
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

    public static final String FLAVOR_ID = System.getProperty("flavorid", "performance1-1");

    public static void main(String[] args) throws Exception {

        NovaApi novaApi = authenticate(USERNAME, API_KEY);
        Server server = createServer(novaApi);
        Set<AddNode> loadBalancerNodes = createNodes(server);

        CloudLoadBalancersApi clbApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(USERNAME, API_KEY)
            .buildApi(CloudLoadBalancersApi.class);

        LoadBalancerApi lbApi = clbApi.getLoadBalancerApiForZone(REGION);
        LoadBalancer loadBalancer = createLoadBalancer(clbApi, lbApi, loadBalancerNodes);

        HealthMonitorApi healthMonitorApi = clbApi.getHealthMonitorApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        createHealthMonitor(healthMonitorApi);
        HealthMonitor healthMonitor = getHealthMonitor(healthMonitorApi);

        ConnectionApi connectionApi = clbApi.getConnectionApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        setThrottling(connectionApi);
        
        AccessRuleApi accessRuleApi = clbApi.getAccessRuleApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        blacklistIPs(accessRuleApi);

        ContentCachingApi contentCachingApi = clbApi.getContentCachingApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        enableContentCaching(contentCachingApi);

        ErrorPageApi errorPageApi = clbApi.getErrorPageApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());
        setCustomErrorPage(errorPageApi);

        deleteResources(novaApi, clbApi, server, loadBalancer);
    }

    public static NovaApi authenticate(String username, String apiKey) {
        NovaApi novaApi = ContextBuilder.newBuilder("rackspace-cloudservers-us")
            .credentials(username, apiKey)
            .buildApi(NovaApi.class);

        return novaApi;
    }

    public static Server createServer(NovaApi novaApi) throws TimeoutException {
        ServerApi serverApi = novaApi.getServerApiForZone(REGION);
        ImageApi imageApi = novaApi.getImageApiForZone(REGION);
        
        ImmutableList<? extends Image> images = imageApi.listInDetail().concat().toList();
        Image ubuntu1404Image = Iterables.find(images, new Predicate<Image>() {
            public boolean apply(Image image) {
                return image.getName().equals("Ubuntu 14.04 LTS (Trusty Tahr)");
            }
        });

        Image image = imageApi.get(ubuntu1404Image.getId());
        ServerCreated serverCreated = serverApi.create("My new server", image.getId(), FLAVOR_ID);

        // Wait until the server is active
        if (!awaitActive(serverApi).apply(serverCreated.getId())) {
            throw new TimeoutException("Timeout on server: " + serverCreated);
        }

        Server server = serverApi.get(serverCreated.getId());

        return server;
    }

    public static LoadBalancer createLoadBalancer(CloudLoadBalancersApi clbApi, LoadBalancerApi lbApi, Set<AddNode> addNodes) throws TimeoutException {
        CreateLoadBalancer createLB = CreateLoadBalancer.builder()
            .name(LOAD_BALANCER_NAME)
            .protocol("HTTP")
            .port(80)
            .algorithm(RANDOM)
            .nodes(addNodes)
            .virtualIPType(PUBLIC)
            .build();

        LoadBalancer loadBalancer = lbApi.create(createLB);

        // Wait for the Load Balancer to become active before moving on
        if (!awaitAvailable(lbApi).apply(loadBalancer)) {
            throw new TimeoutException("Timeout on loadBalancer: " + loadBalancer); 
        }

        return loadBalancer;
    }


    public static Set<AddNode> createNodes(Server server) { 
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

        String address = server.getAddresses().values().iterator().next().getAddr();
        AddNode addNode = AddNode.builder()
            .address(address)
            .condition(ENABLED)
            .port(80)
            .weight(20)
            .build();

        addLBNodes.add(node1);
        addLBNodes.add(node2);
        addLBNodes.add(addNode);

        return addLBNodes;
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
        HealthMonitor monitor = healthMonitorApi.get();

        return monitor;
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

        List<AccessRule> accessList = ImmutableList.<AccessRule> of(rule1, rule2, blacklisted);

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
        NodeApi nodeApi = clbApi.getNodeApiForZoneAndLoadBalancer(REGION, loadBalancer.getId());

        Set<Node> nodes = nodeApi.list().concat().toSet();

        Iterable<Integer> nodeIds = Iterables.transform(nodes, new NodeToId());
        nodeApi.remove(nodeIds);
    }

    public static void deleteLoadBalancer(CloudLoadBalancersApi clbApi, LoadBalancer loadBalancer) throws TimeoutException {
        LoadBalancerApi lbApi = clbApi.getLoadBalancerApiForZone(REGION);
        lbApi.delete(loadBalancer.getId());
        
        // Wait for the Load Balancer to be deleted
        if (!awaitDeleted(lbApi).apply(loadBalancer)) {
            throw new TimeoutException("Timeout on loadBalancer: " + loadBalancer); 
        }
    }

    public static void deleteServer(NovaApi novaApi, Server server) {
        ServerApi serverApi = novaApi.getServerApiForZone(REGION);
        serverApi.delete(server.getId());
    }

    public static class NodeToId implements Function<Node, Integer> {
        public Integer apply(Node node) {
            return node.getId();
        }
    }

    public static void deleteResources(NovaApi novaApi, CloudLoadBalancersApi clbApi, Server server, LoadBalancer loadBalancer)
          throws IOException, TimeoutException {
        deleteNodes(clbApi, loadBalancer);
        deleteLoadBalancer(clbApi, loadBalancer);
        deleteServer(novaApi, server);

        Closeables.close(novaApi, true);
        Closeables.close(clbApi, true);
    }
}
