.. code-block:: csharp

  cloudServersProvider.DeleteServer("{server_id}");

.. code-block:: go

	err := Delete(serviceClient, "{serverId}")

.. code-block:: java

  ServerApi serverApi = novaApi.getServerApiForZone("{region}");

  serverApi.delete("{serverId}");

.. code-block:: javascript

  client.destroyServer(server, function(err) {
    // TODO handle err as appropriate
  });

.. code-block:: php

  $server->delete();

.. code-block:: python

  server.delete()

.. code-block:: ruby

  server.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/$SERVER_ID -H "X-Auth-Token: $TOKEN"
