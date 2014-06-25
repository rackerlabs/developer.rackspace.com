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
        FluentIterable<Flavor> flavors = listFlavors(flavorApi);
        Flavor flavor = getFlavor(flavorApi);

        InstanceApi instanceApi = troveApi.getInstanceApiForZone(REGION);
        Instance instance = createInstance(troveApi, instanceApi, flavor);

        DatabaseApi databaseApi = troveApi.getDatabaseApiForZoneAndInstance(REGION, instance.getId());
        createDatabase(databaseApi, DATABASE_NAME);

        UserApi userApi = troveApi.getUserApiForZoneAndInstance(REGION, instance.getId());
        createUser(userApi, DATABASE_USER_NAME, DATABASE_NAME);

        String rootPassword = enableRootUser(instanceApi, instance);
        boolean rootEnabled = checkRootStatus(instanceApi, instance);

        deleteResources(troveApi, userApi, databaseApi, instanceApi, instance);
    }

    public static TroveApi authenticate(String username, String apiKey) {
        TroveApi troveApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(TroveApi.class);

        return troveApi;
    }

    public static FluentIterable<Flavor> listFlavors(FlavorApi flavorApi) {
        FluentIterable<Flavor> flavors = flavorApi.list();

        return flavors;
    }

    public static Flavor getFlavor(TroveApi troveApi) {
        // List your flavors and get the first.
        FlavorApi flavorApi = troveApi.getFlavorApiForZone(REGION);
        Flavor flavor = Iterables.getFirst(flavorApi.list(), null);

        return flavor;
    }

    public static Instance createInstance(TroveApi troveApi, InstanceApi instanceApi, Flavor flavor) throws TimeoutException {
        TroveUtils utils = new TroveUtils(troveApi);
        Instance instance = utils.getWorkingInstance(REGION, INSTANCE_NAME, Integer.toString(flavor.getId()), 1);

        // Wait for the Instance to become Active before moving on
        if (!awaitAvailable(instanceApi).apply(instance)) {
            throw new TimeoutException("Timeout on instance: " + instance);
        }

        return instance;
    }

    public static void createDatabase(DatabaseApi databaseApi, String databaseName) {
        databaseApi.create(databaseName);
    }

    public static void createUser(UserApi userApi, String databaseUserName, String databaseName) {
        userApi.create(databaseUserName, "password123", databaseName);
    }

    public static String enableRootUser(InstanceApi instanceApi, Instance instance) {
        String password = instanceApi.enableRoot(instance.getId());

        return password;
    }

    public static boolean checkRootStatus(InstanceApi instanceApi, Instance instance) {
        return instanceApi.isRooted(instance.getId());
    }

    public static void deleteUser(UserApi userApi, String databaseUserName) {
        userApi.delete(databaseUserName);
    }

    public static void deleteDatabase(DatabaseApi databaseApi, String databaseName) {
        databaseApi.delete(databaseName);
    }

    public static void deleteInstance(InstanceApi instanceApi, Instance instance) {
        instanceApi.delete(instance.getId());
    }

    public static void deleteResources(TroveApi troveApi, UserApi userApi, DatabaseApi databaseApi,
          InstanceApi instanceApi, Instance instance) throws IOException {
        deleteUser(userApi, DATABASE_USER_NAME);
        deleteDatabase(databaseApi, DATABASE_NAME);
        deleteInstance(instanceApi, instance);

        Closeables.close(troveApi, true);
    }
}
