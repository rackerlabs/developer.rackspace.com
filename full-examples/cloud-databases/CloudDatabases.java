import static org.jclouds.openstack.trove.v1.predicates.InstancePredicates.awaitAvailable;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.trove.v1.TroveApi;
import org.jclouds.openstack.trove.v1.domain.Flavor;
import org.jclouds.openstack.trove.v1.domain.Instance;
import org.jclouds.openstack.trove.v1.features.DatabaseApi;
import org.jclouds.openstack.trove.v1.features.FlavorApi;
import org.jclouds.openstack.trove.v1.features.InstanceApi;
import org.jclouds.openstack.trove.v1.features.UserApi;
import org.jclouds.openstack.trove.v1.utils.TroveUtils;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.io.Closeables;

public class CloudDatabases {
    // The jclouds Provider for the Rackspace Cloud Databases US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-clouddatabases-us");
    // jclouds refers to "regions" as "zones"
    public static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String INSTANCE_NAME = System.getProperty("instanceName", "sample-instance");
    public static final String DATABASE_NAME = System.getProperty("databaseName", "sample-database");
    public static final String DATABASE_USER_NAME = System.getProperty("databaseUserName", "sampleDbUser");

    public static void main(String[] args) throws Exception {

        TroveApi troveApi = authenticate(USERNAME, API_KEY);

        FlavorApi flavorApi = troveApi.getFlavorApiForZone(REGION);
        List<? extends Flavor> flavors = listFlavors(flavorApi);
        Flavor flavor = getFlavor(flavorApi);

        InstanceApi instanceApi = troveApi.getInstanceApiForZone(REGION);
        Instance instance = createInstance(instanceApi, flavor);

        DatabaseApi databaseApi = troveApi.getDatabaseApiForZoneAndInstance(REGION, instance.getId());
        createDatabase(databaseApi);

        UserApi userApi = troveApi.getUserApiForZoneAndInstance(REGION, instance.getId());
        createUser(databaseApi, instance);

        String rootPassword = enableRootUser(instanceApi, instance);
        checkRootStatus(troveApi, instance);

        deleteResources(troveApi, instance);
    }

    public static TroveApi authenticate(String username, String apiKey) {
        TroveApi troveApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(TroveApi.class);

        return troveApi;
    }

    public static List<? extends Flavor> listFlavors(FlavorApi flavorApi) {
        FluentIterable<Flavor> flavors = flavorApi.list();

        return flavors;
    }

    public static Flavor getFlavor(FlavorApi flavorApi) {
        Flavor flavor = Iterables.getFirst(flavorApi.list(), null);

        return flavor;
    }

    public static Instance createInstance(InstanceApi troveApi, Flavor flavor)
            throws TimeoutException {
        TroveUtils utils = new TroveUtils(troveApi);
        Instance instance = utils.getWorkingInstance(REGION, INSTANCE_NAME, Integer.toString(flavor.getId()), 1);

        // Wait for the Instance to become Active before moving on
        awaitAvailable(instanceApi).apply(instance);

        return instance;
    }

    public static String enableRootUser(InstanceApi instanceApi, Instance instance) {
        String password = instanceApi.enableRoot(instance.getId());

        return password;
    }

    public static void checkRootStatus(InstanceApi instanceApi, Instance instance) {
        instanceApi.isRooted(instance.getId());
    }

    public static void createDatabase(DatabaseApi databaseApi) {
        databaseApi.create(DATABASE_NAME);
    }

    public static void createUser(UserApi userApi, Instance instance) {
        userApi.create(DATABASE_USER_NAME, "password123", DATABASE_NAME);
    }

    private static void deleteResources(TroveApi troveApi, Instance instance)
            throws IOException {
        troveApi.getUserApiForZoneAndInstance(REGION, instance.getId()).delete(DATABASE_USER_NAME);
        troveApi.getDatabaseApiForZoneAndInstance(REGION, instance.getId()).delete(DATABASE_NAME);
        troveApi.getInstanceApiForZone(REGION).delete(instance.getId());

        Closeables.close(troveApi, true);
    }
}
