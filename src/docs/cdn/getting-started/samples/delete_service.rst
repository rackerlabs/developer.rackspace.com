.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  err := services.Delete(client, serviceIdOrURL).ExtractErr()

.. code-block:: java

  ServiceApi serviceApi = cdnApi.getServiceApi();
  serviceApi.delete("{serviceId}");

.. code-block:: javascript

  client.deleteService(service, function(err) {
    if (err) {
      // TODO handle err as appropriate
    }
  });

.. code-block:: php

  $service->delete();

.. code-block:: python

  service.delete()

.. code-block:: ruby

  @service.destroy

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/services/{serviceId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
