.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  opts := osNetworks.CreateOpts{
    Name: "MyNewNetwork",
  }

  network, err := networks.Create(client, opts).Extract()

.. code-block:: java

  NetworkApi networkApi = neutronApi.getNetworkApi("{region}");

  Network network = networkApi.create(Network.createBuilder("MyNewNetwork").build());

.. code-block:: javascript

  client.createNetwork({
    name: 'MyNewNetwork'
  }, function(err, network) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your network here
  });

.. code-block:: php

  $networkingService = $client->networkingService(null, '{region}');

  $network = $networkingService->createNetwork(array(
      'name' => 'MyNewNetwork'
  ));

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @network = @client.networks.new(:name => "MyNewNetwork").save

.. code-block:: sh

  curl -X "POST" "https://dfw.networks.api.rackspacecloud.com/v2.0/networks" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-type: application/json" \
    -d $'{"network": {"name": "MyNewNetwork"} }' \
    -H "Accept: application/json" | python -m json.tool


