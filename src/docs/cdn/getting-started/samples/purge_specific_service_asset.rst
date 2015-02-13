.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  deleteOpts := osServiceAssets.DeleteOpts{
    URL: "{relativeUrlOfAsset}",
  }
  err := serviceassets.Delete(client, serviceIDOrURL, deleteOpts).ExtractErr()

.. code-block:: java

  ServiceApi serviceApi = poppyApi.getServiceApi();

  serviceApi.deleteAsset("{relativeUrlOfAsset}");

.. code-block:: javascript

  client.deleteServiceCachedAssets(service, '{relativeUrlOfAsset}', function(err) {
    if (err) {
      // TODO handle err as appropriate
    }
  });

.. code-block:: php

  $service->purgeAssets('{relativeUrlOfAsset}');

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @service.destroy_assets(url: "{relativeUrlOfAsset}")

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/services/{serviceId}/assets?url={relativeUrlOfAsset} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
