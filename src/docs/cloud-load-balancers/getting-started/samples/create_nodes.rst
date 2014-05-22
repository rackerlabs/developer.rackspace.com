.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  // Create the first node
  $serverOneNode = $loadBalancer->node(array(
      'address'   => $serverOne->addresses->private[0]->addr,
      'port'      => 8080,
      'condition' => 'ENABLED'
  ));

  // Create the second node
  $serverTwoNode = $loadBalancer->node(array(
      'address'   => $serverTwo->addresses->private[0]->addr,
      'port'      => 8080,
      'condition' => 'ENABLED'
  ));

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
