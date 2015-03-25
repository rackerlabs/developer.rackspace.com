.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

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
