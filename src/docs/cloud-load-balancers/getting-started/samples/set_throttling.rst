.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  int maxConnectionRate = 10000;
  int maxConnections = 5000;
  int minConnections = 2;
  TimeSpan rateInterval = TimeSpan.FromSeconds(5);
  ConnectionThrottles throttles = new ConnectionThrottles(
	maxConnectionRate, 
	maxConnections, 
	minConnections, 
	rateInterval);
  await cloudLoadBalancerProvider.UpdateThrottlesAsync(
	loadBalancerId, 
	throttles, 
	AsyncCompletionOption.RequestCompleted, 
	CancellationToken.None, 
	null);    

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  ConnectionApi connectionApi =
      clbApi.getConnectionApiForZoneAndLoadBalancer("{region}", "{loadBalancerId}");

  ConnectionThrottle throttle = ConnectionThrottle.builder()
      .maxConnectionRate(10000)
      .maxConnections(5000)
      .minConnections(2)
      .rateInterval(5)
      .build();

  connectionApi.createOrUpdateConnectionThrottle(throttle);

.. code-block:: javascript

  lb.updateConnectionThrottle({
    minConnections: 2,
    maxConnections: 5000,
    maxConnectionRate: 10000,
    rateInterval: 5
  }, function (err) {
    if (err) {
      // TODO handle as appropriate
    }
  });


.. code-block:: php

  $throttle = $loadBalancer->connectionThrottle();

  // Allow a maximum of 5,000 simultaneous connections (maxConnections) per minute (rateInterval).
  // Limit each IP to a maximum of 50 connections (maxConnectionRate).
  $throttle->create(array(
      'maxConnections'    => 5000,
      'minConnections'    => 10,
      'rateInterval'      => 60,
      'maxConnectionRate' => 50
  ));

.. code-block:: python

  load_balancer.add_connection_throttle(maxConnectionRate=10000,
                                        maxConnections=5000,
                                        minConnections=2,
                                        rateInterval=5)

.. code-block:: ruby

  @balancer.enable_connection_throttling(5000,  # max_connections
    2,  # min_connections
    10000,  # max_connection_rate
    5)  # rate_interval

  @balancer.wait_for { ready? }

.. code-block:: sh

  curl -X PUT $ENDPOINT/loadbalancers/{loadBalancerId}/connectionthrottle \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d \
      '{
          "connectionThrottle": {
              "maxConnections": 5000,
              "minConnections": 2,
              "maxConnectionRate": 10000,
              "rateInterval": 5
          }
      }'
