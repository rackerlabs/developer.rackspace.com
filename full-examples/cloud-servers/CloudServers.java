import static com.google.common.base.Charsets.UTF_8;
import static org.jclouds.openstack.nova.v2_0.predicates.ServerPredicates.awaitActive;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Flavor;
import org.jclouds.openstack.nova.v2_0.domain.Image;
import org.jclouds.openstack.nova.v2_0.domain.KeyPair;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.domain.ServerCreated;
import org.jclouds.openstack.nova.v2_0.extensions.KeyPairApi;
import org.jclouds.openstack.nova.v2_0.features.FlavorApi;
import org.jclouds.openstack.nova.v2_0.features.ImageApi;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
import org.jclouds.openstack.nova.v2_0.options.CreateServerOptions;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.io.Closeables;
import com.google.common.io.Files;

public class CloudServers {
    // The jclouds Provider for the Rackspace Cloud Servers US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudservers-us");
    // jclouds refers to "regions" as "zones"
    public static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String FLAVOR_ID = System.getProperty("flavorid", "performance1-1");

    public static void main(String[] args) throws Exception {

        NovaApi novaApi = authenticate(USERNAME, API_KEY);

        ImageApi imageApi = novaApi.getImageApiForZone(REGION);
        List<? extends Image> images = listImages(imageApi);
        Image image = getImage(imageApi, images);

        FlavorApi flavorApi = novaApi.getFlavorApiForZone(REGION);
        List<? extends Flavor> flavors = listFlavors(flavorApi);
        Flavor flavor = getFlavor(flavorApi, FLAVOR_ID);

        KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone(REGION).get();
        KeyPair keyPair = createNewKeyPair(keyPairApi);
        ServerCreated serverCreated = createServerWithKeypair(novaApi, image, flavor, keyPair);
        Server server = queryServerBuild(novaApi, serverCreated);

        deleteResources(novaApi, keyPair, server);
    }

    public static NovaApi authenticate(String username, String apiKey) {
        NovaApi novaApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(NovaApi.class);

        return novaApi;
    }

    public static void uploadExistingKeyPair(NovaApi novaApi) throws IOException {
        File keyPairFile = new File("{/home/my-user/.ssh/id_rsa.pub}");
        String publicKey = Files.toString(keyPairFile, UTF_8);

        KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone(REGION).get();
        KeyPair keyPair = keyPairApi.createWithPublicKey("my-keypair", publicKey);
    }

    public static List<? extends Image> listImages(ImageApi imageApi) {
        ImmutableList<? extends Image> images = imageApi.listInDetail().concat().toList();

        return images;
    }

    public static Image getImage(ImageApi imageApi, List<? extends Image> images) {
        Image ubuntu1404Image = Iterables.find(images, new Predicate<Image>() {
            public boolean apply(Image image) {
                return image.getName().equals("Ubuntu 14.04 LTS (Trusty Tahr)");
            }
        });
        Image image = imageApi.get(ubuntu1404Image.getId());

        return image;
    }

    public static List<? extends Flavor> listFlavors(FlavorApi flavorApi) {
        ImmutableList<? extends Flavor> flavors = flavorApi.listInDetail().concat().toList();

        return flavors;
    }

    public static Flavor getFlavor(FlavorApi flavorApi, String flavorId) {
        Flavor flavor = flavorApi.get(flavorId);

        return flavor;
    }

    public static KeyPair createNewKeyPair(KeyPairApi keyPairApi) throws IOException {
        KeyPair keyPair = keyPairApi.create("my-keypair");

        File keyPairFile = new File("my-keypair.pem");
        Files.write(keyPair.getPrivateKey(), keyPairFile, UTF_8);

        return keyPair;
    }

    public static ServerCreated createServerWithKeypair(NovaApi novaApi, Image image, Flavor flavor, KeyPair keyPair){
        ServerApi serverApi = novaApi.getServerApiForZone(REGION);
        CreateServerOptions options = CreateServerOptions.Builder.keyPairName(keyPair.getName());
        ServerCreated serverCreated = serverApi.create("My new server", image.getId(), flavor.getId(), options);

        return serverCreated;
    }

    public static Server queryServerBuild(NovaApi novaApi, ServerCreated serverCreated) throws TimeoutException {
        ServerApi serverApi = novaApi.getServerApiForZone(REGION);

        // Wait until the Server is Active
        if (!awaitActive(serverApi).apply(serverCreated.getId())) {
            throw new TimeoutException("Timeout on server: " + serverCreated);
        }

        Server server = serverApi.get(serverCreated.getId());

        return server;
    }

    public static void deleteServer(NovaApi novaApi, Server server) {
        ServerApi serverApi = novaApi.getServerApiForZone(REGION);
        serverApi.delete(server.getId());
    }

    public static void deleteKeyPair(NovaApi novaApi, KeyPair keyPair) {
        KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone(REGION).get();
        keyPairApi.delete(keyPair.getName());
    }

    private static void deleteResources(NovaApi novaApi, KeyPair keyPair, Server server) throws IOException {
       deleteKeyPair(novaApi, keyPair);
       deleteServer(novaApi, server);

       Closeables.close(novaApi, true);
    }
}
