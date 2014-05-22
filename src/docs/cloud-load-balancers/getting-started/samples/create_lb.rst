.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  // Get an empty Load Balancer object
  $loadBalancer = $service->loadBalancer();

  // Add the IP types that your Load Balancer will support
  $loadBalancer->addVirtualIp('PUBLIC', 4);
  $loadBalancer->addVirtualIp('PUBLIC', 6);

  // Send to the API
  $loadBalancer->create(array(
      'name'     => 'My Load Balancer',
      'port'     => 80,
      'protocol' => 'HTTP'
  ));

.. code-block:: python

  clb = pyrax.cloud_loadbalancers
  virtual_ipv4 = clb.VirtualIP(type="PUBLIC", ipVersion='IPV4')

  load_balancer = clb.create("balanced", port=80, protocol="HTTP",
                             virtual_ips=[virtual_ipv4])

.. code-block:: ruby

  @balancer = @client.load_balancers.create(
    :name => 'balanced',
    :protocol => 'HTTP',
    :port => 8080,
    :virtual_ips => [{ :type => 'PUBLIC' }],
    :nodes => []
  )

.. code-block:: sh
