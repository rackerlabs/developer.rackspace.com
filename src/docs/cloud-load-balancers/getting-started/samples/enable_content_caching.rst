.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  await cloudLoadBalancerProvider.SetContentCachingAsync(loadBalancerId, true, AsyncCompletionOption.RequestCompleted, CancellationToken.None, null);

.. code-block:: java

  ContentCachingApi contentCachingApi = clbApi.getContentCachingApiForZoneAndLoadBalancer("{region}", loadBalancer.getId());
  contentCachingApi.enable();

.. code-block:: javascript

  // Send false for the second argument to disable content caching
  client.updateContentCaching(lb, true, function (err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  // To check whether caching is enabled
  $loadBalancer->hasContentCaching();

  // To enable caching
  $loadBalancer->enableContentCaching(true);

  // To disable caching
  $loadBalancer->enableContentCaching(false);

.. code-block:: python

  # Check if content caching is enabled
  load_balancer.content_caching

  # Enable content_caching
  load_balancer.content_caching = True

.. code-block:: ruby

  # To check whether or not content caching is enabled
  @balancer.content_caching

  # To enable content caching
  @balancer.enable_content_caching
  @balancer.wait_for { ready? }

  # To disable caching
  @balancer.disable_content_caching
  @balancer.wait_for { ready? }

.. code-block:: sh

  curl -X PUT $ENDPOINT/loadbalancers/{loadBalancerId}/contentcaching \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d \
      '{
          "contentCaching": {
              "enabled": true
          }
      }'
