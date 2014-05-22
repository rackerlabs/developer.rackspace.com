.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

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
