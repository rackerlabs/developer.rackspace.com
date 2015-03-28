import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Collections;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.poppy.v1.PoppyApi;
import org.jclouds.openstack.poppy.v1.features.FlavorApi;
import org.jclouds.openstack.poppy.v1.features.ServiceApi;
import org.jclouds.openstack.poppy.v1.domain.CreateService;
import org.jclouds.openstack.poppy.v1.domain.Flavor;
import org.jclouds.openstack.poppy.v1.domain.Service;
import org.jclouds.openstack.poppy.v1.domain.Origin;
import org.jclouds.openstack.poppy.v1.domain.Domain;
import org.jclouds.openstack.poppy.v1.domain.Caching;
import org.jclouds.openstack.poppy.v1.domain.Restriction;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Closeables;

public class CDN {
    // The jclouds Provider for the Rackspace CDN US service. It contains information
    // about the service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cdn-us");

    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");
    public static final String FLAVOR_ID = "cdn";

    public static void main(String[] args) throws Exception {
        PoppyApi cdnApi = authenticate(USERNAME, API_KEY);

        // List flavors
        FlavorApi flavorApi = cdnApi.getFlavorApi();
        List<Flavor> flavors = listFlavors(flavorApi);

        // Get flavor
        Flavor flavor = getFlavor(flavorApi, FLAVOR_ID);

        // Create service
        ServiceApi serviceApi = cdnApi.getServiceApi();
        URI serviceURI = createService(serviceApi, flavor.getId());

        // List services
        List<Service> services = listServices(serviceApi);

        // Get service
        Service service = getService(serviceApi, services.get(0).getId());

        // Update service
        serviceURI = updateService(serviceApi, service);

        // Delete service
        deleteResources(cdnApi, service);
    }

    public static PoppyApi authenticate(String username, String apiKey) {
        PoppyApi cdnApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(PoppyApi.class);

        return cdnApi;
    }

    public static List<Flavor> listFlavors(FlavorApi flavorApi) {
        return flavorApi.list().toList();
    }

    public static Flavor getFlavor(FlavorApi flavorApi, String flavorId) {
        return flavorApi.get(flavorId);
    }

    public static URI createService(ServiceApi serviceApi, String flavorId) {
        // TODO: Remove the .caching(...) and .restrictions(...) calls below
        // when this bug is fixed: https://issues.apache.org/jira/browse/JCLOUDS-877
        return serviceApi.create(
            CreateService.builder()
                .name("jclouds_example_site")
                .domains(
                    ImmutableList.of(
                        Domain.builder().domain("jclouds-example.com").build()))
                .origins(
                    ImmutableList.of(
                        Origin.builder().origin("origin1.jclouds-example.com").build()))
                .caching(Collections.<Caching>emptyList())
                .restrictions(Collections.<Restriction>emptyList())
                .flavorId(flavorId)
                .build());
    }

    public static List<Service> listServices(ServiceApi serviceApi) {
        return serviceApi.list().concat().toList();
    }

    public static Service getService(ServiceApi serviceApi, String serviceId) {
        return serviceApi.get(serviceId);
    }

    public static URI updateService(ServiceApi serviceApi, Service service)
        throws InterruptedException {
        // TODO: Replace this when we have an awaitDeployed predicate on
        // Service (see https://github.com/jclouds/jclouds-labs-openstack/pull/186).
        Thread.sleep(15000);

        return serviceApi.update(
            service.getId(),
            service,
            service.toUpdatableService()
                .name("updated service name")
                .build());
    }

    public static void deleteResources(PoppyApi cdnApi, Service service)
        throws IOException {
        ServiceApi serviceApi = cdnApi.getServiceApi();
        serviceApi.delete(service.getId());

        Closeables.close(cdnApi, true);
    }
}
