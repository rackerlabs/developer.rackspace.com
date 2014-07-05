.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  HealthMonitor healthMonitor = await cloudLoadBalancerProvider.GetHealthMonitorAsync(loadBalancerId, CancellationToken.None);

.. code-block:: java

  HealthMonitorApi hmApi = clbApi.getHealthMonitorApiForZoneAndLoadBalancer("{region}", loadBalancer.getId());
  HealthMonitor monitor = hmApi.get();

.. code-block:: javascript

  lb.getHealthMonitor(function (err, monitor) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your health monitor here
  });

.. code-block:: php

  $healthMonitor = $loadBalancer->healthMonitor();

  // Output the monitoring type, its delay and timeout, and the amount of
  // times it will poll the Load Balancer before deactivating
  printf(
      "Monitoring type: %s, delay: %s, timeout: %s, attempts before deactivation: %s",
      $healthMonitor->type, $healthMonitor->delay, $healthMonitor->timeout
  );

.. code-block:: python

  load_balancer.get_health_monitor()

.. code-block:: ruby

  @balancer.health_monitor

.. code-block:: sh

  curl -s -X GET $ENDPOINT/loadbalancers/{loadBalancerId}/healthmonitor \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool

