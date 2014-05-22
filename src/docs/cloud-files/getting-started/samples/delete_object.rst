.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

  client.removeFile('gallery', 'somefile.txt', function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $object->delete();

.. code-block:: python

  obj.delete()

.. code-block:: ruby

  file.destroy

.. code-block:: sh

  curl -i -X DELETE $ENDPOINT/{containerName}/{objectName} \
    -H "X-Auth-Token: $TOKEN"

  # NOTE: {containerName} and {objectName} are placeholders: Replace them
  # with actual values and do not enclose them with {}.
