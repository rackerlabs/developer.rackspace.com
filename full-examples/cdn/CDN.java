import java.net.URI;
import java.util.ArrayList;

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
        ImmutableList<Flavor> flavors = flavorApi.list().toList();

        // Get flavor
        Flavor flavor = flavorApi.get(FLAVOR_ID);

        // Create service
        ServiceApi serviceApi = cdnApi.getServiceApi();
        URI serviceURI = serviceApi.create(
            CreateService.builder()
                .name("jclouds_example_site")
                .domains(
                    ImmutableList.of(
                        Domain.builder().domain("jclouds-example.com").build()))
                .origins(
                    ImmutableList.of(
                        Origin.builder().origin("origin1.jclouds-example.com").build()))
                .caching(new ArrayList<Caching>())
                .restrictions(new ArrayList<Restriction>())
                .flavorId(flavor.getId())
                .build());

        // List services
        ImmutableList<Service> services = cdnApi.getServiceApi().list().concat().toList();

        // Get service
        Service service = serviceApi.get(services.get(0).getId());

        // TODO: Replace this when we have an awaitDeployed predicate on Service.
        Thread.sleep(15000);

        // Update service
        serviceURI = serviceApi.update(
            service.getId(),
            service,
            service.toUpdatableService()
                .name("updated service name")
                .build());

        // Delete service
        serviceApi.delete(service.getId());
    }

    public static PoppyApi authenticate(String username, String apiKey) {
        PoppyApi cdnApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(PoppyApi.class);

        return cdnApi;
    }
}
