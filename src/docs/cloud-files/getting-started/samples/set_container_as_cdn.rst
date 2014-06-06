.. code-block:: csharp

.. code-block:: java

  URI cdnUri = cloudFilesApi.getCDNApiForRegion("{region}").enable("{containerName}");

.. code-block:: javascript

  container.enableCdn(function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $container->enableCdn();

.. code-block:: python

  container.make_public()

.. code-block:: ruby

  directory.public = true
  directory.save

.. code-block:: sh

  curl -i -X PUT $ENDPOINT/{containerName} /
    -H "X-Auth-Token: $TOKEN" \
    -H "X-CDN-Enabled: True" \
    -H "X-TTL: 604800"