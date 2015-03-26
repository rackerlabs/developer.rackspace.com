.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  err := ports.List(client, osPorts.ListOpts{}).EachPage(func(page pagination.Page) (bool, error) {
    portList, err := osPorts.ExtractPorts(page)

    for _, port := range portList {
    
    }

    return true, nil
  })

.. code-block:: java

  List<Port> ports = portApi.list().concat().toList();

.. code-block:: javascript

  client.getPorts(function(err, ports) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your ports here
  });

.. code-block:: php

  $ports = $networkingService->listPorts();

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @client.ports

.. code-block:: sh

  // Not currently supported by this SDK

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
