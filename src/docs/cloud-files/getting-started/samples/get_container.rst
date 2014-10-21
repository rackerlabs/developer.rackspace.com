.. code-block:: csharp

  Dictionary<string,string> container = 
        cloudFilesProvider.GetContainerHeader("{container_name}", "{region}");
          
.. code-block:: go

	_, err := Get(serviceClient, "{containerName}").ExtractMetadata()

.. code-block:: java

  ContainerApi containerApi = cloudFilesApi.getContainerApiForRegion("{region}");

  Container container = containerApi.get("{containerName}");

.. code-block:: javascript

  client.getContainer({containerName}, function(err, container) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your container
  });

.. code-block:: php

  $container = $objectStoreService->getContainer('{containerName}');

.. code-block:: python

  container = pyrax.cloudfiles.get_container("gallery")

.. code-block:: ruby

  directory = @client.directories.get('{containerName}')

.. code-block:: sh

  curl -i -X GET $ENDPOINT/{containerName} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json"
