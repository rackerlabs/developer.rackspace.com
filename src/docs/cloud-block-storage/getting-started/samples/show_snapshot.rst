.. code-block:: csharp

  Snapshot snapshot = 
	new CloudBlockStorageProvider({cloudIdentity}).ShowSnapshot("{snapshotId}", "{region}");

.. code-block:: go

  s, err := snapshots.Get(client, "{snapshotId}").Extract()

.. code-block:: java

  SnapshotApi snapshotApi = cinderApi.getSnapshotApiForZone("{region}");

  Snapshot snapshot = snapshotApi.get("{snapshotId}");

.. code-block:: javascript

  client.getShapshot('{snapshotId}', function(err, snapshot) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use the retrieved snapshot
  });

.. code-block:: php

  $snapshot = $volumeService->snapshot('{snapshotId}');

.. code-block:: python

  snapshot = cbs.get_snapshot('{snapshotId}')

.. code-block:: ruby

  snapshot @client.snapshots.get('{snapshotId}')

 .. code-block:: sh

    $ curl -X GET $ENDPOINT/snapshots/{snapshotId}
      -H "X-Auth-Token: $TOKEN" \
      -H "Content-Type: application/json" | python -m json.tool
