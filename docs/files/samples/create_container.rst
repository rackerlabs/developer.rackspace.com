.. code-block:: java

  ContainerApi containerApi = cloudFilesApi.getContainerApiForRegion("{region}");

  // Create a container with no options
  containerApi.createIfAbsent("{container}", CreateContainerOptions.NONE);

  // Create a container with some metadata
  Map<String, String> metadata = ImmutableMap.of("metakey1", "metaValue1", "metaKey2", "metaValue2");
  containerApi.createIfAbsent("{container}", CreateContainerOptions.metadata(metadata));

.. code-block:: javascript

  client.createContainer({
    name: 'sample-container-test'
  }, function (err, container) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your container
  });

.. code-block:: php

  // Obtain an Object Store service object from the client.
  $region = 'DFW';
  $objectStoreService = $client->objectStoreService(null, $region);

  // Create a container for your objects (also referred to as files).
  $container = $objectStoreService->createContainer('gallery');

.. code-block:: python

  container = pyrax.cloudfiles.create_container("gallery")

.. code-block:: ruby

  # Fog calls containers "directories."

  directory = @client.directories.create(:key => 'sample-container-test')
