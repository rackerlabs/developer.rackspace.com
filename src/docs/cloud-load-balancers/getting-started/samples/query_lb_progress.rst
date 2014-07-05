.. code-block:: csharp

  int limit = 1;
  ReadOnlyCollection<LoadBalancer> loadBalancers = await (await cloudLoadBalancerProvider.ListLoadBalancersAsync(null, limit, CancellationToken.None)).GetAllPagesAsync(CancellationToken.None, null);
  LoadBalancerStatus loadBalancerStatus = loadBalancers[0].Status;
  // Values include:
  // LoadBalancerStatus.Active;
  // LoadBalancerStatus.Build;
  // LoadBalancerStatus.Deleted;
  // LoadBalancerStatus.Error;
  // LoadBalancerStatus.PendingDelete;
  // LoadBalancerStatus.PendingUpdate;
  // LoadBalancerStatus.Suspended;

.. code-block:: java

  LoadBalancer loadBalancer = lbApi.create(createLB);

  if (!LoadBalancerPredicates.awaitAvailable(lbApi).apply(loadBalancer)) {
      throw new TimeoutException("Timeout on creating load balancer: " + loadBalancer);
  }

.. code-block:: javascript

  // Poll the resource until it reaches an ACTIVE state, with a 2500ms frequency
  lb.setWait({
    status: 'ACTIVE'
  }, 2500, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

    // Write a callback which outputs the name, status and progress of a LB
    $callback = function($lb) {
        if (!empty($lb->error)) {
            var_dump($lb->error);
            exit;
        } else {
            printf(
                "Waiting on %s/%-12s %4s%%",
                $lb->name(),
                $lb->status(),
                isset($lb->progress) ? $lb->progress : 0
            );
        }
    };

    // Poll the resource until it reaches an ACTIVE state, with a 600s (5 min) timeout
    // Please be aware this is a blocking operation
    $server->waitFor('ACTIVE', 300, $callback);

.. code-block:: python

  pyrax.utils.wait_until(load_balancer, "status", "ACTIVE", interval=1,
                         attempts=30)

.. code-block:: ruby

  @balancer.wait_for { ready? }

.. code-block:: sh

  curl -s -X GET $ENDPOINT/loadbalancers/{loadBalancerId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool

  # Watch for loadBalancer.status to become "ACTIVE"
