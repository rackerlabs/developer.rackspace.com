.. code-block:: csharp

.. code-block:: java

  ServerApi serverApi = novaApi.getServerApiForZone("{region}");
  serverApi.resize("{serverId}", "{flavorId}");

.. code-block:: javascript

.. code-block:: php

  // Load your new flavor
  $newFlavor = $compute->flavor('{newFlavorId}');

  $server->resize($newFlavor);

.. code-block:: python

.. code-block:: ruby

  server.resize('{newFlavorId}')

.. code-block:: sh

  # First, you'll need to note the flavor ref number from the flavor list.
  curl -X PUT $ENDPOINT/{serverId}/action \ -d \
    '{
      "resize" : {
        "flavorRef" : "{newFlavorId}"
       }
    }' \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
