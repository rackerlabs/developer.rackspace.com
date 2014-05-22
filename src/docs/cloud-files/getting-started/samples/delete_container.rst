.. code-block:: csharp

.. code-block:: java

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

.. code-block:: sh

  curl -i -X DELETE $ENDPOINT/{containerName} -H "X-Auth-Token: $TOKEN"

  # NOTE: {containerName} is a placeholder: Replace it with
  # an actual value and do not enclose it with {}.
