import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.jclouds.ContextBuilder;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.*;
import org.jclouds.openstack.nova.v2_0.extensions.KeyPairApi;
import org.jclouds.openstack.nova.v2_0.features.FlavorApi;
import org.jclouds.openstack.nova.v2_0.features.ImageApi;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
import org.jclouds.openstack.nova.v2_0.options.CreateServerOptions;
// TODO: ServerPredicates is only available in jclouds 1.7.3
//import org.jclouds.openstack.nova.v2_0.predicates.ServerPredicates;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.google.common.base.Charsets.UTF_8;

public class CloudServers {
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudservers-us");
    public static final String ZONE = System.getProperty("zone", "IAD");

    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static void main(String[] args) throws Exception {
        NovaApi novaApi = ContextBuilder.newBuilder(PROVIDER)
                .credentials(USERNAME, API_KEY)
                .buildApi(NovaApi.class);

        List<? extends Image> images = listImages(novaApi);
        Image image = getImage(novaApi, images);
        List<? extends Flavor> flavors = listFlavors(novaApi);
        Flavor flavor = getFlavor(novaApi, flavors);
        KeyPair keyPair = createNewKeyPair(novaApi);
        ServerCreated serverCreated = createServerWithKeypair(novaApi, image, flavor, keyPair);
        Server server = queryServerBuild(novaApi, serverCreated);
        resizeServer(novaApi, server, flavors.get(1));
        deleteServer(novaApi, server);
        deleteKeyPair(novaApi, keyPair);
    }

    public static void uploadExistingKeyPair(NovaApi novaApi) throws IOException {
        File keyPairFile = new File("{/home/my-user/.ssh/id_rsa.pub}");
        String publicKey = Files.toString(keyPairFile, UTF_8);

        KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone("{region}").get();
        KeyPair keyPair = keyPairApi.createWithPublicKey("my-keypair", publicKey);
    }

    public static List<? extends Image> listImages(NovaApi novaApi) {
        ImageApi imageApi = novaApi.getImageApiForZone(ZONE);
        ImmutableList<? extends Image> images = imageApi.listInDetail().concat().toList();

        return images;
    }

    public static Image getImage(NovaApi novaApi, List<? extends Image> images) {
        String imageId = images.get(0).getId();
        ImageApi imageApi = novaApi.getImageApiForZone(ZONE);
        Image image = imageApi.get(imageId);

        return image;
    }

    public static List<? extends Flavor> listFlavors(NovaApi novaApi) {
        FlavorApi flavorApi = novaApi.getFlavorApiForZone(ZONE);
        ImmutableList<? extends Flavor> flavors = flavorApi.listInDetail().concat().toList();

        return flavors;
    }

    public static Flavor getFlavor(NovaApi novaApi, List<? extends Flavor> flavors) {
        String flavorId = flavors.get(0).getId();
        FlavorApi flavorApi = novaApi.getFlavorApiForZone(ZONE);
        Flavor flavor = flavorApi.get(flavorId);

        return flavor;
    }

    public static KeyPair createNewKeyPair(NovaApi novaApi) throws IOException {
        KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone(ZONE).get();
        KeyPair keyPair = keyPairApi.create("my-keypair");

        File keyPairFile = new File("my-keypair.pem");
        Files.write(keyPair.getPrivateKey(), keyPairFile, UTF_8);

        return keyPair;
    }

    public static ServerCreated createServerWithKeypair(NovaApi novaApi, Image image, Flavor flavor, KeyPair keyPair)
            throws TimeoutException {
        ServerApi serverApi = novaApi.getServerApiForZone(ZONE);
        CreateServerOptions options = CreateServerOptions.Builder.keyPairName(keyPair.getName());
        ServerCreated serverCreated = serverApi.create("My new server", image.getId(), flavor.getId(), options);

        return serverCreated;
    }

    public static Server queryServerBuild(NovaApi novaApi, ServerCreated serverCreated) throws TimeoutException {
        ServerApi serverApi = novaApi.getServerApiForZone(ZONE);

// TODO: ServerPredicates is only available in jclouds 1.7.3
//        if (!ServerPredicates.awaitActive(serverApi).apply(serverCreated.getId())) {
//            throw new TimeoutException("Timeout on server: " + serverCreated);
//        }

        Server server = serverApi.get(serverCreated.getId());

        return server;
    }

    public static void resizeServer(NovaApi novaApi, Server server, Flavor flavors) {
        ServerApi serverApi = novaApi.getServerApiForZone(ZONE);
        serverApi.resize(server.getId(), flavors.getId());
    }

    public static void deleteServer(NovaApi novaApi, Server server) {
        ServerApi serverApi = novaApi.getServerApiForZone(ZONE);
        serverApi.delete(server.getId());
    }

    public static void deleteKeyPair(NovaApi novaApi, KeyPair keyPair) {
        KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone(ZONE).get();
        keyPairApi.delete(keyPair.getName());
    }
}
