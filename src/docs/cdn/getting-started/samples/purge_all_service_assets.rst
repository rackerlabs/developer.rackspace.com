.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  deleteOpts := osServiceAssets.DeleteOpts{
    All: true,
  }
  err := serviceassets.Delete(client, serviceIDOrURL, deleteOpts).ExtractErr()

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.deleteServiceCachedAssets(service, function(err) {
    if (err) {
      // TODO handle err as appropriate
    }
  });

.. code-block:: php

  $service->purgeAssets();

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @service.destroy_assets(url: "/")

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/services/{serviceId}/assets?all=true \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
