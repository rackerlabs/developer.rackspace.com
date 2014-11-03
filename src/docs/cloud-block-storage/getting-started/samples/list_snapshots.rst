.. code-block:: csharp

  IEnumerable<Snapshot> snapshots = cbsProvider.ListSnapshots(region: "{region}");

.. code-block:: go

  snapshots.List(client).EachPage(func(page pagination.Page) (bool, error) {
    snapshotList, err := snapshots.ExtractSnapshots(page)
    for _, s := range snapshotList {
      // ...
    }
    return true, nil
  })

.. code-block:: java

  SnapshotApi snapshotApi = cinderApi.getSnapshotApiForZone("{region}");

  List<Snapshot> snapshots = snapshotApi.listInDetail().toList();

.. code-block:: javascript

  client.getSnapshots(function(err, snapshots) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your list of snapshots
  });

.. code-block:: php

  $snapshots = $volumeService->snapshotList(array(
    'volume_id' => $volume->id()
  ));

.. code-block:: python

  snapshots = vol.list_snapshots()

.. code-block:: ruby

  snapshots = @client.snapshots.all

  # To fetch only snapshots associated with a given volume:
  volume.snapshots

.. code-block:: sh

  curl -X GET $ENDPOINT/snapshots \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" | python -m json.tool
