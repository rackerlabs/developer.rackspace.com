.. code-block:: python

    # When restoring from a backup, you must supply a backup (either the backup
    # object or its ID), a name for the new instance to be created from the
    # backup, as well as a flavor and size (in GB) for the instance.
    new_inst = cdb.restore_backup(backup, "new_name", flavor=flavor,
            volume=20GB)
