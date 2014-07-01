.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  await cloudLoadBalancerProvider.SetErrorPageAsync(loadBalancerId, "{custom_error_page}", AsyncCompletionOption.RequestCompleted, CancellationToken.None, null);

.. code-block:: java

  ErrorPageApi errorPageApi = clbApi.getErrorPageApiForZoneAndLoadBalancer("{region}", loadBalancer.getId());

  String content = "<html><body>Something went wrong...</body></html>";
  errorPageApi.create(content);

.. code-block:: javascript

  lb.setErrorPage('<html><body>Something went wrong...</body></html>', function (err, page) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  // To use a custom error page, specify the markup:
  $errorPage = $loadBalancer->errorPage();
  $errorPage->update(array(
      'content' => '<html><body>Something went wrong...</body></html>'
  ));

  // To delete your custom error page:
  $errorPage->delete();

.. code-block:: python

  # Set a custom error page, specify the markup, up to a maximum of 65536 bytes:
  error_html = "<html><body>Something went wrong...</body></html>"
  load_balancer.set_error_page(error_html)

  # Delete the custom error page
  load_balancer.clear_error_page()

.. code-block:: ruby

  # To use a custom error page, specify the markup, up to a maximum of 65536 bytes:
  @balancer.error_page = '<html><body>Something went wrong...</body></html>'

  # To delete your custom error page:
  @balancer.reset_error_page

.. code-block:: sh

  # To use a custom error page, specify the markup, up to a maximum of 65536 bytes:
  curl -X PUT $ENDPOINT/loadbalancers/{loadBalancerId}/errorpage \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d \
      '{
          "errorpage": {
              "content": "&lt;html&gt;&lt;body&gt;Something went wrong...&lt;/body&gt;&lt;/html&gt;"
          }
      }'

  # To delete your custom error page:
  curl -X DELETE $ENDPOINT/loadbalancers/{loadBalancerId}/errorpage
