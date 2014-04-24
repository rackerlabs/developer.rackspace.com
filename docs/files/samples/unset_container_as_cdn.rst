.. code-block:: java

  cloudFilesApi.getCDNApiForRegion("{region}").disable("{container}");

.. code-block:: javascript

  container.disableCdn(function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  // Disable CDN for the container.
  $container->disableCdn();

.. code-block:: python

  container.make_private()
