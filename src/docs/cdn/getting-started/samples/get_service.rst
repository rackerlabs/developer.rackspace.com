.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

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

  @service = @client.services.get({service_id})

.. code-block:: sh

  $ curl -X GET $ENDPOINT/services/{serviceId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
