.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  service, err := services.Get(client, serviceIdOrURL).Extract()

.. code-block:: java

  ServiceApi serviceApi = poppyApi.getServiceApi();

  Service service = serviceApi.get("{serviceId}");

.. code-block:: javascript

  client.getService({serviceId}, function(err, service) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your service here
  });

.. code-block:: php

  $service = $cdnService->getService('{serviceId}');

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @service = @client.services.get({serviceId})

.. code-block:: sh

  $ curl -X GET $ENDPOINT/services/{serviceId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
