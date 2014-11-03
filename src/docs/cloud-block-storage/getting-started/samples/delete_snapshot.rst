.. code-block:: csharp

  new CloudBlockStorageProvider({cloudIdentity}).DeleteSnapshot("{snapshotId}", "{region}");

.. code-block:: go

  err := snapshots.Delete(client, "{snapshotId}").ExtractErr()

.. code-block:: java

  SnapshotApi snapshotApi = cinderApi.getSnapshotApiForZone("{region}");

  snapshotApi.delete("{snapshotId}");

.. code-block:: javascript

  client.deleteSnapshot(snapshotId, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $snapshot->delete();

.. code-block:: python

  snap.delete()
  # Or:
  # cbs.delete_snapshot('{snapId}')

.. code-block:: ruby

  snapshot.destroy

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/snapshots/{snapshotId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" | python -m json.tool
