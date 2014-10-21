.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  int attemptsBeforeDeactivation = 3;
  TimeSpan timeout = TimeSpan.FromSeconds(30);
  TimeSpan delay = TimeSpan.FromSeconds(30);
  HealthMonitor healthMonitor = 
	new ConnectionHealthMonitor(
		attemptsBeforeDeactivation, 
		timeout, 
		delay);
  await cloudLoadBalancerProvider.SetHealthMonitorAsync(
	loadBalancerId, 
	healthMonitor, 
	AsyncCompletionOption.RequestCompleted, 
	CancellationToken.None, 
	null);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  HealthMonitorApi healthMonitorApi =
      clbApi.getHealthMonitorApiForZoneAndLoadBalancer("{region}", "{loadBalancerId}");

  HealthMonitor healthMonitor = HealthMonitor.builder()
      .type(HealthMonitor.Type.CONNECT)
      .delay(3599)
      .timeout(30)
      .attemptsBeforeDeactivation(2)
      .build();

  healthMonitorApi.createOrUpdate(healthMonitor);

.. code-block:: javascript

  // using our previously created lb

  lb.updateHealthMonitor({
    type: 'CONNECT',
    attemptsBeforeDeactivation: 2,
    delay: 3599,
    timeout: 30
  }, function (err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $healthMonitor = $loadBalancer->healthMonitor();
  $healthMonitor->update(array(
      'delay'   => 120,
      'timeout' => 60,
      'type'    => 'CONNECT',
      'attemptsBeforeDeactivation' => 3
  ));

.. code-block:: python

  load_balancer.add_health_monitor(type="CONNECT", delay=10, timeout=10,
                                   attemptsBeforeDeactivation=3)

.. code-block:: ruby

  @balancer.enable_health_monitor('CONNECT', 10, 10, 3)
  @balancer.wait_for { ready? }

.. code-block:: sh

  curl -X PUT $ENDPOINT/loadbalancers/{loadBalancerId}/healthmonitor \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d \
      '{
          "healthMonitor": {
              "type": "CONNECT",
              "delay": 10,
              "timeout": 10,
              "attemptsBeforeDeactivation": 3
          }
      }'
