.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  NodeConfiguration nodeConfiguration = 
	new NodeConfiguration(
		"{host_domain}", 
		80, 
		NodeCondition.Enabled, 
		NodeType.Primary, 
		null);
  Node node = 
	await cloudLoadBalancerProvider.AddNodeAsync(
		loadBalancerId, 
		nodeConfiguration, 
		AsyncCompletionOption.RequestCompleted, 
		CancellationToken.None, 
		null);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  NodeApi nodeApi = clbApi.getNodeApiForZoneAndLoadBalancer("{region}", "{loadBalancerId}");

  AddNode node1 = AddNode.builder()
      .address(server1.getAccessIPv4())
      .condition(BaseNode.Condition.DISABLED)
      .port(80)
      .weight(20)
      .build();

  AddNode node2 = AddNode.builder()
      .address(server2.getAccessIPv4())
      .condition(BaseNode.Condition.ENABLED)
      .port(80)
      .weight(20)
      .build();

  Set<AddNode> addNodes = Sets.newHashSet();
  addNodes.add(node1);
  addNodes.add(node2);

  nodeApi.add(addNodes);

.. code-block:: javascript

  var nodes = [];

  // using our servers array from above
  servers.forEach(function(server) {
    nodes.push({
      address: server.addresses.['private'][0].addr,
      port: 80,
      weight: 20,
      condition: 'ENABLED',
      type: 'PRIMARY'
    });
  });

  client.addNodes(lb, nodes, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  use OpenCloud\LoadBalancer\Enum\NodeCondition;

  $serverOneNode = $loadBalancer->node(array(
      'address'   => $serverOne->addresses->private[0]->addr,
      'port'      => 8080,
      'condition' => NodeCondition::ENABLED
  ));
  $serverOneNode->create();

  $serverTwoNode = $loadBalancer->node(array(
      'address'   => $serverTwo->addresses->private[0]->addr,
      'port'      => 8080,
      'condition' => NodeCondition::ENABLED
  ));
  $serverTwoNode->create();

.. code-block:: python

  server_one_node = clb.Node(address=server_one.accessIPv4, port=8080, condition="ENABLED")
  server_two_node = clb.Node(address=server_two.accessIPv4, port=8080, condition="ENABLED")
  load_balancer.add_nodes([server_one_node, server_two_node])
  pyrax.utils.wait_until(load_balancer, "status", "ACTIVE", interval=1, attempts=30, verbose=True)

.. code-block:: ruby

  @server_one_node = @balancer.nodes.create(
    :address => @server_one.private_ip_address
    :port => 8080,
    :condition => 'ENABLED'
  )
  @balancer.wait_for { ready? }

  @server_two_node = @balancer.nodes.create(
    :address => @server_two.private_ip_address
    :port => 8080,
    :condition => 'ENABLED'
  )
  @balancer.wait_for { ready? }

.. code-block:: sh

  curl -s -X POST $ENDPOINT/loadbalancers/{loadBalancerId}/nodes \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d \
      '{
          "nodes": [
              {
                  "address": "{serverOnePrivateAddress}",
                  "port": 8080,
                  "condition": "ENABLED",
                  "type": "PRIMARY"
              },
              {
                  "address": "{serverOnePrivateAddress}",
                  "port": 8080,
                  "condition": "ENABLED",
                  "type": "PRIMARY"
              }
          ]
      }' | python -m json.tool
