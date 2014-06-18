import java.io.IOException;

import org.jclouds.ContextBuilder;
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
    // jclouds refers to "regions" as "zones"
    private static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    private static final String USERNAME = System.getProperty("username", "{username}");
    private static final String API_KEY = System.getProperty("apikey", "{apiKey}");
    // Ubuntu 12.04 LTS (Precise Pangolin) Server Image
    private static final String SERVER_IMAGE_ID = "ffa476b1-9b14-46bd-99a8-862d1d94eb7a";
    // 512 MB Standard Instance
    private static final String SERVER_FLAVOR_ID = "2";

    private static final String GROUP_NAME = "autoscale-group";
    private static final String WEBHOOK_NAME = "autoscale-webhook";

    public static void main(String[] args) throws Exception {

        AutoscaleApi autoscaleApi = authenticate(USERNAME, API_KEY);

        GroupApi groupApi = autoscaleApi.getGroupApiForZone(REGION);
        Group group = createScalingGroup(groupApi);

        PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup(REGION, group.getId());
        String policyId = getPolicyId(policyApi);

        WebhookApi webhookApi = autoscaleApi.getWebhookApiForZoneAndGroupAndPolicy(REGION, group.getId(), policyId);
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
        GroupConfiguration groupConfiguration = GroupConfiguration.builder()
            .maxEntities(5)
            .cooldown(2)
            .name(GROUP_NAME)
            .minEntities(0)
            .metadata(ImmutableMap.of("notes", "This is an autoscale group for examples"))
            .build();

        LaunchConfiguration launchConfiguration = LaunchConfiguration.builder()
            .loadBalancers(ImmutableList.of(LoadBalancer.builder().port(8080).id(9099).build()))
            .serverName(GROUP_NAME)
            .serverImageRef(SERVER_IMAGE_ID)
            .serverFlavorRef(SERVER_FLAVOR_ID)
            .serverDiskConfig("AUTO")
            .serverMetadata(ImmutableMap.of("notes", "Server examples notes"))
            .networks(ImmutableList.of("internal", "public"))
            .personalities(ImmutableList.of(
                  Personality.builder().path("filepath").contents("VGhpcyBpcyBhIHRlc3QgZmlsZS4=").build()))
                  .type(LaunchConfigurationType.LAUNCH_SERVER)
                  .build();

        CreateScalingPolicy scalingPolicy = CreateScalingPolicy.builder()
            .cooldown(0)
            .type(ScalingPolicyType.WEBHOOK)
            .name(GROUP_NAME)
            .targetType(ScalingPolicyTargetType.PERCENT_CHANGE)
            .target("1")
            .build();

        Group group = groupApi.create(groupConfiguration, launchConfiguration, ImmutableList.of(scalingPolicy));

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
       WebhookApi webhookApi = autoscaleApi.getWebhookApiForZoneAndGroupAndPolicy(REGION, group.getId(), policyId);
       webhookApi.delete(webhookId);
    }

    public static void deleteScalingPolicy(AutoscaleApi autoscaleApi, Group group, String policyId) {
       PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup(REGION, group.getId());
       policyApi.delete(policyId);
    }

    public static void deleteScalingGroup(AutoscaleApi autoscaleApi, Group group) {
       GroupApi groupApi = autoscaleApi.getGroupApiForZone(REGION);
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
