.. code-block:: csharp

.. code-block:: java

  // This operation is currently not supported through the jclouds SDK.

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

  # When restoring from a backup, you must supply a backup (either the backup
  # object or its ID), a name for the new instance to be created from the
  # backup, as well as a flavor and size (in GB) for the instance.
  new_inst = cdb.restore_backup(backup, "new_name", flavor=flavor,
                                volume=20)

.. code-block:: ruby

  # This operation is currently not supported through the fog SDK.

.. code-block:: sh
