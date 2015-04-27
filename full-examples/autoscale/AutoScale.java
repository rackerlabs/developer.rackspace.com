import java.io.IOException;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.rackspace.autoscale.v1.AutoscaleApi;
import org.jclouds.rackspace.autoscale.v1.domain.CreateScalingPolicy;
import org.jclouds.rackspace.autoscale.v1.domain.CreateScalingPolicy.ScalingPolicyTargetType;
import org.jclouds.rackspace.autoscale.v1.domain.CreateScalingPolicy.ScalingPolicyType;
import org.jclouds.rackspace.autoscale.v1.domain.Group;
import org.jclouds.rackspace.autoscale.v1.domain.GroupConfiguration;
import org.jclouds.rackspace.autoscale.v1.domain.LaunchConfiguration;
import org.jclouds.rackspace.autoscale.v1.domain.LaunchConfiguration.LaunchConfigurationType;
import org.jclouds.rackspace.autoscale.v1.domain.LoadBalancer;
import org.jclouds.rackspace.autoscale.v1.domain.Personality;
import org.jclouds.rackspace.autoscale.v1.domain.ScalingPolicy;
import org.jclouds.rackspace.autoscale.v1.domain.Webhook;
import org.jclouds.rackspace.autoscale.v1.features.GroupApi;
import org.jclouds.rackspace.autoscale.v1.features.PolicyApi;
import org.jclouds.rackspace.autoscale.v1.features.WebhookApi;
import org.jclouds.rackspace.autoscale.v1.utils.AutoscaleUtils;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Closeables;

public class AutoScale {
    // The jclouds Provider for the Rackspace Auto Scale US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    private static final String PROVIDER = System.getProperty("provider.autoscale", "rackspace-autoscale-us");

    private static final String REGION = System.getProperty("region", "IAD");

    // Authentication credentials
    private static final String USERNAME = System.getProperty("username", "{username}");
    private static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    // Ubuntu 14.04 LTS Server Image
    private static final String IMAGE_ID = "5cc098a5-7286-4b96-b3a2-49f4c4f82537";

    public static final String FLAVOR_ID = System.getProperty("flavorid", "general1-1");

    private static final String GROUP_NAME = "autoscale-group";
    private static final String WEBHOOK_NAME = "autoscale-webhook";

    private static final String PUBLIC_NET = "00000000-0000-0000-0000-000000000000";
    private static final String SERVICE_NET = "11111111-1111-1111-1111-111111111111";

    public static void main(String[] args) throws Exception {
        AutoscaleApi autoscaleApi = authenticate(USERNAME, API_KEY);

        GroupApi groupApi = autoscaleApi.getGroupApi(REGION);
        Group group = createScalingGroup(groupApi);

        PolicyApi policyApi = autoscaleApi.getPolicyApi(REGION, group.getId());
        String policyId = getPolicyId(policyApi);

        WebhookApi webhookApi = autoscaleApi.getWebhookApi(REGION, group.getId(), policyId);
        String webhookId = createWebhook(webhookApi, WEBHOOK_NAME);
        executeWebhook(webhookApi, webhookId);

        deleteResources(autoscaleApi, group, policyId, webhookId);
    }

    public static AutoscaleApi authenticate(String username, String apiKey) {
        AutoscaleApi autoscaleApi = ContextBuilder.newBuilder(PROVIDER)
                .credentials(username, apiKey)
                .buildApi(AutoscaleApi.class);

        return autoscaleApi;
    }

    public static Group createScalingGroup(GroupApi groupApi) {
        GroupConfiguration groupConfig = GroupConfiguration.builder()
                .maxEntities(5)
                .cooldown(2)
                .name(GROUP_NAME)
                .minEntities(0)
                .metadata(ImmutableMap.of("notes", "This is an autoscale group for examples"))
                .build();

        LaunchConfiguration launchConfig = LaunchConfiguration.builder()
                .loadBalancers(ImmutableList.of(LoadBalancer.builder().port(8080).id(9099).build()))
                .serverName(GROUP_NAME)
                .serverImageRef(IMAGE_ID)
                .serverFlavorRef(FLAVOR_ID)
                .serverDiskConfig(Server.DISK_CONFIG_AUTO)
                .serverMetadata(ImmutableMap.of("notes", "Server examples notes"))
                .networks(ImmutableList.of(PUBLIC_NET, SERVICE_NET))
                .personalities(ImmutableList.of(
                        Personality.builder()
                                .path("filepath")
                                .contents("VGhpcyBpcyBhIHRlc3QgZmlsZS4=")
                                .build()))
                .type(LaunchConfigurationType.LAUNCH_SERVER)
                .build();

        CreateScalingPolicy scalingPolicy = CreateScalingPolicy.builder()
                .cooldown(0)
                .type(ScalingPolicyType.WEBHOOK)
                .name(GROUP_NAME)
                .targetType(ScalingPolicyTargetType.PERCENT_CHANGE)
                .target("1")
                .build();

        Group group = groupApi.create(groupConfig, launchConfig, ImmutableList.of(scalingPolicy));

        return group;
    }

    public static String getPolicyId(PolicyApi policyApi) {
        FluentIterable<ScalingPolicy> policies = policyApi.list();

        return policies.first().get().getId();
    }

    public static String createWebhook(WebhookApi webhookApi, String webhookName) {
        FluentIterable<Webhook> result = webhookApi.create(webhookName, ImmutableMap.<String, Object>of());

        return result.first().get().getId();
    }

    public static void executeWebhook(WebhookApi webhookApi, String webhookId) throws IOException {
        AutoscaleUtils.execute(webhookApi.get(webhookId));
    }

    public static void deleteWebhook(AutoscaleApi autoscaleApi, Group group, String policyId, String webhookId) {
        WebhookApi webhookApi = autoscaleApi.getWebhookApi(REGION, group.getId(), policyId);
        webhookApi.delete(webhookId);
    }

    public static void deleteScalingPolicy(AutoscaleApi autoscaleApi, Group group, String policyId) {
        PolicyApi policyApi = autoscaleApi.getPolicyApi(REGION, group.getId());
        policyApi.delete(policyId);
    }

    public static void deleteScalingGroup(AutoscaleApi autoscaleApi, Group group) {
        GroupApi groupApi = autoscaleApi.getGroupApi(REGION);
        groupApi.delete(group.getId());
    }

    public static void deleteResources(AutoscaleApi autoscaleApi, Group group, String policyId, String webhookId)
            throws IOException {
        deleteWebhook(autoscaleApi, group, policyId, webhookId);
        deleteScalingPolicy(autoscaleApi, group, policyId);
        deleteScalingGroup(autoscaleApi, group);

        Closeables.close(autoscaleApi, true);
    }
}
