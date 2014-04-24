.. code-block:: java

  ObjectApi objectApi = cloudFilesApi.getContainerApiForRegion("{region}");
  objectApi.delete("{object}");

.. code-block:: javascript

  client.removeFile('sample-container-test', 'somefile.txt', function(err) {
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
