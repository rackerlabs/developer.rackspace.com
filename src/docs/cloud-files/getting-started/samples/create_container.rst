.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

  client.createContainer({
    name: 'gallery'
  }, function (err, container) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your container
  });

.. code-block:: php

  // Obtain an Object Store service object from the client.
  $objectStoreService = $client->objectStoreService(null, '{region}');

  // Create a container for your objects (also referred to as files).
  $container = $objectStoreService->createContainer('gallery');

.. code-block:: python

  container = pyrax.cloudfiles.create_container("gallery")

.. code-block:: ruby

  # Fog calls containers "directories."

  directory = @client.directories.create(:key => 'gallery')

.. code-block:: sh

  curl -i -X PUT $ENDPOINT/{containerName} \
    -H "X-Auth-Token: $TOKEN"

  # NOTE: {containerName} is a placeholder: Replace it with
  # an actual value and do not enclose it with {}.
