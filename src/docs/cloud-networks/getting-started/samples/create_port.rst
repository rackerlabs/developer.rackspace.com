.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  opts := osPorts.CreateOpts{
    NetworkID: network.ID,
    Name: "MyNewPort",
  }
  
  port, err := ports.Create(client, opts).Extract()

.. code-block:: java

  PortApi portApi = neutronApi.getPortApi("{region}");
  Port port = portApi.create(Port.createBuilder(network.getId())
      .name("MyNewPort")
      .build());

.. code-block:: javascript

  client.createPort({
    name: 'MyNewPort',
    networkId: network.id
  }, function(err, port) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your port here
  });

.. code-block:: php

  $port = $networkingService->createPort(array(
      'name'      => 'MyNewPort',
      'networkId' => $network->getId()
  ));

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @port = @client.ports.new({:name => "MyNewPort", :network_id => @network.id}).save

.. code-block:: sh

  // Not currently supported by this SDK

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
