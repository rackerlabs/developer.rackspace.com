.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

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

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
