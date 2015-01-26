.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

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

  @service.destroy_assets(url: "/path/to/asset")

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/services/{serviceId}/assets?url={relativeUrlOfAsset} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
