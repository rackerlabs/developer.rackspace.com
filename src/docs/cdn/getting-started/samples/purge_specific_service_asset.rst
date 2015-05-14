.. code-block:: csharp

  CancellationToken cn = new CancellationToken();
  await cdc.RemoveAssetAsync({serviceId}, cn, {urlOfAsset});

.. code-block:: go

  deleteOpts := osServiceAssets.DeleteOpts{
    URL: "{relativeUrlOfAsset}",
  }
  err := serviceassets.Delete(client, serviceIDOrURL, deleteOpts).ExtractErr()

.. code-block:: java

  // Only supported in SNAPSHOT versions of this SDK
  ServiceApi serviceApi = cdnApi.getServiceApi();
  serviceApi.deleteAsset("{serviceId}", "image/1.jpg"));

.. code-block:: javascript

  client.deleteServiceCachedAssets(service, '{relativeUrlOfAsset}', function(err) {
    if (err) {
      // TODO handle err as appropriate
    }
  });

.. code-block:: php

  $service->purgeAssets('{relativeUrlOfAsset}');

.. code-block:: python

  service.delete_assets(url="{relativeUrlOfAsset}")

.. code-block:: ruby

  @service.destroy_assets(url: "{relativeUrlOfAsset}")

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/services/{serviceId}/assets?url={relativeUrlOfAsset} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
