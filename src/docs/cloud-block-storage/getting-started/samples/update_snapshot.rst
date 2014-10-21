.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

	options := &UpdateMetadataOpts{
  	Metadata: map[string]interface{}{
	 	"key": "v1",
  	},
	}
	r, err := UpdateMetadata(serviceClient, "{snapshotId}", options).ExtractMetadata()

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

	// assuming we've already loaded the details of a snapshot into a
  // local variable named snapshot

  snapshot.name = 'New Snapshot Name';

  client.updateSnapshot(snapshot, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $snapshot->rename(array(
    'display_name' => 'new_snapshot_name'
  ));

.. code-block:: python

  snapshot.update(display_name='New Snapshot Name')

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -X PUT $ENDPOINT/snapshots/{snapshotId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "snapshot": {
        "display_name": "New Snapshot Name"
      }
    }' | python -m json.tool
