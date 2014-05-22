.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  // Update object metadata.
  $object->saveMetadata(array(
      'Content-Type' => 'image/jpeg'
  ));

.. code-block:: python

  obj.set_metadata({"some-key": "another-value"})

.. code-block:: ruby

  file.metadata['some-key'] = 'another-value'
  file.save

.. code-block:: sh

    curl -i -X POST $ENDPOINT/{containerName}/{objectName} \
      -H "X-Auth-Token: $TOKEN" \
      -H "X-Object-Meta-Some-Key: another-value"

    # NOTE: {containerName} and {objectName} are placeholders: Replace them
    # with actual values and do not enclose them with {}.
