.. code-block:: csharp

  new CloudBlockStorageProvider({cloudIdentity}).CreateSnapshot(
	"{volumeId}", 
	displayName: "{name}", 
	displayDescription: "{description}", 
	region: "{region}");

.. code-block:: go

  import "github.com/rackspace/gophercloud/blockstorage/v1/snapshots"
  opts := snapshots.CreateOpts{VolumeID: "{volumeId}, Name: "{snapshotName}"}
  s, err := snapshots.Create(client, opts).Extract()

.. code-block:: java

  VolumeApi volumeApi = cinderApi.getVolumeApiForZone("{region}");
  SnapshotApi snapshotApi = cinderApi.getSnapshotApiForZone("{region}");

  // Get the volume to snapshot
  Volume volume = volumeApi.get("{volumeId}")

  CreateSnapshotOptions options = CreateSnapshotOptions.Builder
      .name("{name}")
      .description("This is the description");

  Snapshot snapshot = snapshotApi.create("{volumeId}", options);

.. code-block:: javascript

  // To create a snapshot for a volume, the volume should be detached from
  // any server. You must supply a name for the snapshot, and may provide
  // an optional description.

  client.createSnapshot({
    name: 'name-here',
    description: 'This is the description',
    volumeId: '{volumeId}'
  }, function(err, snapshot) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your newly created snapshot
  });

.. code-block:: php

    // To create a snapshot for a volume, the volume should be detached from
    // any server. You must supply a name for the snapshot, and may provide
    // an optional description.

    $snapshot = $volumeService->snapshot();
    $snapshot->create(array(
        'display_name' => 'name-here',
        'display_description' => 'This is the description',
        'volume_id' => $volume->id()
    ));

.. code-block:: python

  # To create a snapshot for a volume, the volume should be detached from
  # any server. You must supply a name for the snapshot, and may provide
  # an optional description.

  snap = vol.create_snapshot('name-here', 'This is the description')

.. code-block:: ruby

  # To create a snapshot for a volume, the volume should be detached from
  # any server. You must supply a name for the snapshot, and may provide
  # an optional description.

  snapshot = volume.create_snapshot(
    :display_name => 'name-here',
    :display_description => 'This is the description'
  )

.. code-block:: sh

  $ curl -X POST -d \
    '{
    "snapshot": {
        "display_name": "{name}",
        "display_description": "{description}",
        "volume_id": "{volumeId}"
      }
    }'\
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/snapshots | python -m json.tool