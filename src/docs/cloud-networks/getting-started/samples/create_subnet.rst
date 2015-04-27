.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  opts := osSubnets.CreateOpts{
    Name: "MyNewSubnet",
    CIDR: "192.168.101.1/24",
    NetworkID: network.ID,
    IPVersion: osSubnets.IPv4,
  }

  subnet, err := subnets.Create(client, opts).Extract()

.. code-block:: java

  SubnetApi subnetApi = neutronApi.getSubnetApi("{region}");

  Subnet subnet = subnetApi.create(Subnet.createBuilder(network.getId(), "192.168.101.1/24")
      .ipVersion(4)
      .name("MyNewSubnet")
      .build());

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

  curl -X "POST" "https://dfw.networks.api.rackspacecloud.com/v2.0/subnets" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-type: application/json" \
    -d $'{
      "subnet": {
        "name": "MyNewSubnet",
        "cidr": "192.168.101.1/24",
        "network_id": "'$NETWORK_ID'",
        "ip_version": "4"
      }
    }' \
    -H "Accept: application/json" | python -m json.tool
