.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.createSubnet({
    name: 'MyNewSubnet',
    cidr: '192.168.101.1/24',
    networkId: network.id,
    ipVersion: 4
  }, function(err, subnet) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your subnet here
  });

.. code-block:: php

  $subnet = $networkingService->createSubnet(array(
      'name'      => 'MyNewSubnet',
      'cidr'      => '192.168.101.1/24',
      'networkId' => $network->getId(),
      'ipVersion' => 4
  ));

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @subnet = @client.subnets.new({
    :name       => "MyNewsubnet",
    :cidr       => "192.168.101.1/24",
    :network_id => @network.id,
    :ip_version => "4"
  }).save

.. code-block:: sh

  // Not currently supported by this SDK

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
