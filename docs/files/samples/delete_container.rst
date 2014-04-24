.. code-block:: java

  ContainerApi containerApi = cloudFilesApi.getContainerApiForRegion("{region}");
  ContainerApi.deleteIfEmpty("{container}");

.. code-block:: javascript

  client.destroyContainer(container, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  // Delete an empty container.
  $container->delete();

  // Delete all the objects in the container and delete the container.
  $container->delete(true);

.. code-block:: python

  container.delete()

  # Delete all the objects in the container and delete the container
  container_deleted = pyrax.cloudfiles.delete_container("gallery",
                                                        del_objects=True)

.. code-block:: ruby

  directory.destroy
