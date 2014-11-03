.. code-block:: csharp

  CloudFilesProvider cloudFilesProvider = new CloudFilesProvider(cloudIdentity);
  cloudFilesProvider.DeleteObject("{container_name}", "{object_name}");

.. code-block:: go

  err := objects.Delete(serviceClient, "{containerName}", "{objectName}", nil).ExtractErr()

.. code-block:: java

  ObjectApi objectApi =
      cloudFilesApi.getObjectApiForRegionAndContainer("{region}", "{containerName}");

  objectApi.delete("{objectName}");

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

