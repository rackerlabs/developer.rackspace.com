.. code-block:: java

  Map<String, String> metadata = ImmutableMap.of("metakey1", "metaValue1", "metaKey2", "metaValue2");
  cloudFilesApi.getObjectApiForRegionAndContainer("{region}", "{container}").updateMetadata("myObject", metadata));

.. code-block:: javascript

.. code-block:: php

  // Update object metadata.
  $object->saveMetadata(array(
      'Content-Type' => 'image/jpeg'
  ));

.. code-block:: python

  obj.set_metadata({"some-key": "another-value"})
