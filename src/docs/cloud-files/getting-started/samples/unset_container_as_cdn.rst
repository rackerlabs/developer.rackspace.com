.. code-block:: csharp

  cloudFilesProvider.DisableCDNOnContainer("{container_name}");

.. code-block:: java

  cloudFilesApi.getCDNApiForRegion("{region}").disable("{containerName}");

.. code-block:: javascript

  container.disableCdn(function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $container->disableCdn();

.. code-block:: python

  container.make_private()

.. code-block:: ruby

  directory.public = false
  directory.save

.. code-block:: sh

  curl -i -X POST $ENDPOINT/{containerName} /
    -H "X-Auth-Token: $TOKEN" \
    -H "X-CDN-Enabled: False"

