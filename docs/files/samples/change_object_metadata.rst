.. code-block:: java

  Map<String, String> metadata = ImmutableMap.of("metaKey1", "metaValue1", "metaKey2", "metaValue2");

  ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer("{region}", "{container}");
  objectApi.updateMetadata("{object}", metadata));

.. code-block:: javascript

    file.metadata = {
      'some-key': 'some-value'
    };

    file.updateMetadata(function(err) {
      if (err) {
        // TODO handle as appropriate
      }
    });

.. code-block:: python

  obj.change_content_type("application/json")
  obj.set_metadata({"some-key": "some-value"})

.. code-block:: ruby

  file.content_type = 'application/json'
  file.save

  # Generic metadata can be set with:
  file.metadata['some-key'] = 'some-value'
  file.save
