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
import org.jclouds.openstack.swift.v1.features.ContainerApi;
import org.jclouds.openstack.swift.v1.features.ObjectApi;
import org.jclouds.rackspace.cloudfiles.v1.CloudFilesApi;
import org.jclouds.rackspace.cloudfiles.v1.features.CDNApi;

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

        ContainerApi containerApi = cloudFilesApi.getContainerApiForRegion(REGION);
        createContainer(containerApi);

        ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer(REGION, CONTAINER_NAME);
        uploadObject(objectApi);
        SwiftObject object = getObject(objectApi);
        updateObjectMetadata(objectApi);

        AccountApi accountApi = cloudFilesApi.getAccountApiForRegion(REGION);
        URI tempURL = getObjectTempUrl(accountApi, signer);

        CDNApi cdnApi = cloudFilesApi.getCDNApiForRegion(REGION);
        URI uri = enableCDN(cdnApi);
        disableCDN(cdnApi);

        deleteResources(cloudFilesApi, containerApi, objectApi);
    }

    public static void createContainer(ContainerApi containerApi) {
        containerApi.create(CONTAINER_NAME);
    }

    public static void uploadObject(ObjectApi objectApi) {
        Payload payload = Payloads.newByteSourcePayload(ByteSource.wrap("Hello Cloud Files!".getBytes()));

        objectApi.put(OBJECT_NAME, payload);
    }

    public static SwiftObject getObject(ObjectApi objectApi) {
        SwiftObject object = objectApi.get(OBJECT_NAME);

        return object;
    }

    public static void updateObjectMetadata(ObjectApi objectApi) {
        objectApi.updateMetadata(OBJECT_NAME, ImmutableMap.of("some-key", "another-value"));
    }

    public static URI getObjectTempUrl(AccountApi accountApi, BlobRequestSigner signer) {
        accountApi.updateTemporaryUrlKey(TEMP_URL_KEY);

        HttpRequest request = signer.signGetBlob(CONTAINER_NAME, OBJECT_NAME);
        URI tempUrl = request.getEndpoint();

        return tempUrl;
    }

    public static URI enableCDN(CDNApi cdnApi) {
        URI cdnUri = cdnApi.enable(CONTAINER_NAME);

        return cdnUri;
    }

    public static void disableCDN(CDNApi cdnApi) {
        cdnApi.disable(CONTAINER_NAME);
    }

    public static void deleteObject(ObjectApi objectApi) {
        objectApi.delete(OBJECT_NAME);
    }

    public static void deleteContainer(ContainerApi containerApi) {
        containerApi.deleteIfEmpty(CONTAINER_NAME);
    }

    public static void deleteResources(CloudFilesApi cloudFilesApi, ContainerApi containerApi, ObjectApi objectApi)
            throws IOException {
        deleteObject(objectApi);
        deleteContainer(containerApi);

        Closeables.close(cloudFilesApi, true);
    }
}
