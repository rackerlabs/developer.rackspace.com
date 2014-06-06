import java.io.IOException;
import java.net.URI;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobRequestSigner;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.http.HttpRequest;
import org.jclouds.io.Payload;
import org.jclouds.io.Payloads;
import org.jclouds.openstack.swift.v1.blobstore.RegionScopedBlobStoreContext;
import org.jclouds.openstack.swift.v1.domain.SwiftObject;
import org.jclouds.openstack.swift.v1.features.AccountApi;
import org.jclouds.openstack.swift.v1.features.ObjectApi;
import org.jclouds.rackspace.cloudfiles.v1.CloudFilesApi;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.ByteSource;
import com.google.common.io.Closeables;

public class CloudFiles {
    // The jclouds Provider for the Rackspace Cloud Files US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudfiles-us");
    // jclouds refers to "regions" as "zones"
    public static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String CONTAINER_NAME = System.getProperty("containerName", "sample_container");
    public static final String OBJECT_NAME = System.getProperty("objectName", "sample_object");
    public static final String TEMP_URL_KEY = System.getProperty("tempUrlKey", "jnRB6#1sduo8YGUF&%7r7guf6f");

    public static void main(String[] args) throws Exception {

        ContextBuilder builder = ContextBuilder.newBuilder(PROVIDER)
            .credentials(USERNAME, API_KEY);

        BlobStore blobStore = builder.buildView(RegionScopedBlobStoreContext.class).blobStoreInRegion(REGION);
        BlobRequestSigner signer = builder.buildView(RegionScopedBlobStoreContext.class).signerInRegion(REGION);
        CloudFilesApi cloudFilesApi = blobStore.getContext().unwrapApi(CloudFilesApi.class);

        createContainer(cloudFilesApi);
        uploadObject(cloudFilesApi);

        SwiftObject object = getObject(cloudFilesApi);
        updateObjectMetadata(cloudFilesApi);

        URI tempURL = getObjectTempUrl(cloudFilesApi, signer);

        URI uri = enableCDN(cloudFilesApi);
        disableCDN(cloudFilesApi);

        deleteResources(cloudFilesApi);
    }

    public static void createContainer(CloudFilesApi cloudFilesApi) {
        cloudFilesApi.getContainerApiForRegion(REGION).create(CONTAINER_NAME);
    }

    public static void deleteContainer(CloudFilesApi cloudFilesApi) {
        cloudFilesApi.getContainerApiForRegion(REGION).deleteIfEmpty(CONTAINER_NAME);
    }

    public static void uploadObject(CloudFilesApi cloudFilesApi) {
        Payload payload = Payloads.newByteSourcePayload(ByteSource.wrap("Hello Cloud Files!".getBytes()));

        ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer(REGION, CONTAINER_NAME);
        objectApi.put(OBJECT_NAME, payload);
    }

    public static SwiftObject getObject(CloudFilesApi cloudFilesApi) {
        ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer(REGION, CONTAINER_NAME);
        SwiftObject object = objectApi.get(OBJECT_NAME);

        return object;
    }

    public static void updateObjectMetadata(CloudFilesApi cloudFilesApi) {
        ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer(REGION, CONTAINER_NAME);
        objectApi.updateMetadata(OBJECT_NAME, ImmutableMap.of("some-key", "another-value"));
    }

    public static URI getObjectTempUrl(CloudFilesApi cloudFilesApi, BlobRequestSigner signer) {
        AccountApi accountApi = cloudFilesApi.getAccountApiForRegion(REGION);
        accountApi.updateTemporaryUrlKey(TEMP_URL_KEY);

        HttpRequest request = signer.signGetBlob(CONTAINER_NAME, OBJECT_NAME);
        URI tempUrl = request.getEndpoint();

        return tempUrl;
    }

    public static void deleteObject(CloudFilesApi cloudFilesApi) {
        ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer(REGION, CONTAINER_NAME);
        objectApi.delete(OBJECT_NAME);
    }

    public static URI enableCDN(CloudFilesApi cloudFilesApi) {
        URI cdnUri = cloudFilesApi.getCDNApiForRegion(REGION).enable(CONTAINER_NAME);

        return cdnUri;
    }

    public static void disableCDN(CloudFilesApi cloudFilesApi) {
        cloudFilesApi.getCDNApiForRegion(REGION).disable(CONTAINER_NAME);
    }

    private static void deleteResources(CloudFilesApi cloudFilesApi) throws IOException {
        deleteObject(cloudFilesApi);
        deleteContainer(cloudFilesApi);
        Closeables.close(cloudFilesApi, true);
    }
}
