.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $volume->delete();

.. code-block:: python

  vol.delete()
  # Or:
  # cbs.delete(vol)

.. code-block:: ruby

  volume.destroy

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/volumes/{volumeId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" | python -m json.tool
