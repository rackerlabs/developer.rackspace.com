import static org.jclouds.openstack.cinder.v1.predicates.SnapshotPredicates.awaitAvailable;
import static org.jclouds.openstack.cinder.v1.predicates.SnapshotPredicates.awaitDeleted;
import static org.jclouds.openstack.cinder.v1.predicates.VolumePredicates.awaitAvailable;
import static org.jclouds.openstack.cinder.v1.predicates.VolumePredicates.awaitDeleted;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.cinder.v1.CinderApi;
import org.jclouds.openstack.cinder.v1.domain.Snapshot;
import org.jclouds.openstack.cinder.v1.domain.Volume;
import org.jclouds.openstack.cinder.v1.features.SnapshotApi;
import org.jclouds.openstack.cinder.v1.features.VolumeApi;
import org.jclouds.openstack.cinder.v1.options.CreateSnapshotOptions;
import org.jclouds.openstack.cinder.v1.options.CreateVolumeOptions;

import com.google.common.io.Closeables;

public class CloudBlockStorage {
    // The jclouds Provider for the Rackspace Cloud Block Storage US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudblockstorage-us");
    // jclouds refers to "regions" as "zones"
    public static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String VOLUME_NAME = System.getProperty("volumeName", "sample-volume");
    public static final String SNAPSHOT_NAME = System.getProperty("snapshotName", "sample-snapshot");

    public static void main(String[] args) throws Exception {

        CinderApi cinderApi = authenticate(USERNAME, API_KEY);

        VolumeApi volumeApi = cinderApi.getVolumeApiForZone(REGION);
        Volume volume = createVolume(volumeApi);
        Volume showVolume = showVolume(volumeApi, volume.getId());
        List<? extends Volume> volumes = listVolumes(volumeApi);

        SnapshotApi snapshotApi = cinderApi.getSnapshotApiForZone(REGION);
        Snapshot snapshot = createSnapshot(snapshotApi, volume);
        Snapshot showSnapshot = showSnapshot(snapshotApi, snapshot.getId());
        List<? extends Snapshot> snapshots = listSnapshots(snapshotApi);

        deleteResources(cinderApi, snapshot, volume);
    }

    public static CinderApi authenticate(String username, String apiKey) {
        CinderApi cinderApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(CinderApi.class);

        return cinderApi;
    }

    public static Volume createVolume(VolumeApi volumeApi) throws TimeoutException {
        CreateVolumeOptions options = CreateVolumeOptions.Builder
            .name(VOLUME_NAME)
            .description("Volume Description")
            .volumeType("SATA");

        Volume volume = volumeApi.create(100, options);

        // Wait until the Volume is available
        if (!awaitAvailable(volumeApi).apply(volume)) {
            throw new TimeoutException("Timeout on volume: " + volume); 
        }

        return volume;
    }

    public static Volume showVolume(VolumeApi volumeApi, String volumeId) {
        Volume volume = volumeApi.get(volumeId);

        return volume;
    }

    public static List<? extends Volume> listVolumes(VolumeApi volumeApi) {
        List<? extends Volume> volumes = volumeApi.listInDetail().toList();

        return volumes;
    }

    public static Snapshot createSnapshot(SnapshotApi snapshotApi, Volume volume) throws TimeoutException {
        CreateSnapshotOptions options = CreateSnapshotOptions.Builder
            .name(SNAPSHOT_NAME)
            .description("Snapshot Description");

        Snapshot snapshot = snapshotApi.create(volume.getId(), options);

        // Wait until the Snapshot is available
        if (!awaitAvailable(snapshotApi).apply(snapshot)) {
            throw new TimeoutException("Timeout on snapshot: " + snapshot); 
        }

        return snapshot;
    }

    public static Snapshot showSnapshot(SnapshotApi snapshotApi, String snapshotId) {
        Snapshot snapshot = snapshotApi.get(snapshotId);

        return snapshot;
    }

    public static List<? extends Snapshot> listSnapshots(SnapshotApi snapshotApi) {
        List<? extends Snapshot> snapshots = snapshotApi.listInDetail().toList();

        return snapshots;
    }

    public static void deleteSnapshot(SnapshotApi snapshotApi, Snapshot snapshot) throws TimeoutException {
        snapshotApi.delete(snapshot.getId());

        // Wait until the Snapshot is available
        if (!awaitDeleted(snapshotApi).apply(snapshot)) {
            throw new TimeoutException("Timeout on snapshot: " + snapshot); 
        }
    }

    public static void deleteVolume(VolumeApi volumeApi, Volume volume) throws TimeoutException {
        volumeApi.delete(volume.getId());

        // Wait until the Volume is deleted
        if (!awaitDeleted(volumeApi).apply(volume)) {
           throw new TimeoutException("Timeout on volume: " + volume); 
        }
    }

    public static void deleteResources(CinderApi cinderApi, Snapshot snapshot, Volume volume)
          throws IOException, TimeoutException {
       deleteSnapshot(cinderApi.getSnapshotApiForZone(REGION), snapshot);
       deleteVolume(cinderApi.getVolumeApiForZone(REGION), volume);

       Closeables.close(cinderApi, true);
   }
}
