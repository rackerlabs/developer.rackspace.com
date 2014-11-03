.. code-block:: csharp

  CloudFilesProvider cloudFilesProvider = new CloudFilesProvider(cloudIdentity);
  Dictionary<string, string> metadata = new Dictionary<string,string>();
  metadata.Add("{key}","{value}");
  cloudFilesProvider.UpdateObjectMetadata("{container_name}", "{object_name}", metadata, "{region}");

.. code-block:: go

  var metadata = map[string]string{"some-key": "some-data"}
  _, err := objects.Update(serviceClient, "{containerName}", "{objectName}", objects.UpdateOpts{Metadata: metadata}).ExtractHeader()

.. code-block:: java

  ObjectApi objectApi =
      cloudFilesApi.getObjectApiForRegionAndContainer("{region}", "{containerName}");

  objectApi.updateMetadata("{objectName}", ImmutableMap.of("some-key", "some-value"));

.. code-block:: javascript

  file.metadata = {
    'some-key': 'some-value'
  };

  file.updateMetadata(function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  // Update object metadata.
  $object->saveMetadata(array(
      'some-key' => 'some-value'
  ));

.. code-block:: python

  obj.change_content_type("application/json")

  # Generic metadata can be set with:
  obj.set_metadata({"some-key": "some-value"})

.. code-block:: ruby

  file.content_type = 'application/json'
  file.save

  # Generic metadata can be set with:
  file.metadata['some-key'] = 'some-value'
  file.save

.. code-block:: sh

  curl -i -X POST $ENDPOINT/{containerName}/{objectName} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -H "X-Object-Meta-Some-Key: some-value"

