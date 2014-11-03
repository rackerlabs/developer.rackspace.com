.. code-block:: csharp

  // You can wait for any number of states, e.g. Active, Reboot, etc.
  ServerState[] errorStates = new ServerState[1] { ServerState.Active };
  ServerState[] serverStates = new ServerState[1] { ServerState.Unknown };
  cloudServersProvider.WaitForServerState("{server_id}", serverStates, errorStates);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  ServerApi serverApi = novaApi.getServerApiForZone("{region}");

  ServerPredicates.awaitActive(serverApi).apply("{serverId}")

.. code-block:: javascript

  server.setWait({ status: server.STATUS.running }, 6000, function(err) {
    if (err) {
      // TODO handle err as appropriate
    }

    // TODO continue after status is running
  });

.. code-block:: php

  $server->waitFor('ACTIVE', 600);

.. code-block:: python

  pyrax.utils.wait_for_build(server, verbose=True)

.. code-block:: ruby

  server.wait_for { active }

.. code-block:: sh

  # from resulting json below see "status"
  curl -X GET $ENDPOINT/{serverId} \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
