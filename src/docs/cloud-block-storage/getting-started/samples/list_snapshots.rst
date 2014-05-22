.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $snapshots = $volumeService->snapshotList(array(
    'volume_id' => $volume->id()
  ));

.. code-block:: python

  snaps = vol.list_snapshots()

.. code-block:: ruby

  @client.snapshots.all

  # To fetch only snapshots associated with a given volume:
  volume.snapshots

.. code-block:: sh

  $ curl -X GET $ENDPOINT/snapshots \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" | python -m json.tool
