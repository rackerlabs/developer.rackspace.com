.. code-block:: java

  CDNApi cdnApi = cloudFilesApi.getCDNApiForRegion("{region}");
  URI cdnUri = cdnApi.enable("{container}");

.. code-block:: javascript

  container.enableCdn(function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  // Enable CDN for the container.
  $container->enableCdn();

.. code-block:: python

  container.make_public()
