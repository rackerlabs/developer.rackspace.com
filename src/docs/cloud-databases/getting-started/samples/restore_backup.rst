.. code-block:: csharp

  // To restore a database, you must first use an existing backup to
  // create a Restore Point, then use that Restore Point to create
  // a database instance.
  RestorePoint restorePoint = new RestorePoint(backup.Id);
  FlavorRef flavorRef = new FlavorRef(databaseFlavor.Id);

  // Create a 20 GB volume.
  DatabaseVolumeConfiguration databaseVolumeConfiguration = new DatabaseVolumeConfiguration(20);
  DatabaseInstanceConfiguration databaseInstanceConfiguration = new DatabaseInstanceConfiguration(
    flavorRef,
    databaseVolumeConfiguration,
    "Restored",
    restorePoint
  );

  DatabaseInstance databaseInstance = await cloudDatabasesProvider.CreateDatabaseInstanceAsync(
    databaseInstanceConfiguration,
    AsyncCompletionOption.RequestCompleted,
    CancellationToken.None,
    null
  );

  // This operation is currently not supported through the .NET SDK.

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # When restoring from a backup, you must supply a backup (either the backup
  # object or its ID), a name for the new instance to be created from the
  # backup, as well as a flavor and size (in GB) for the instance.
  new_inst = cdb.restore_backup(backup, "Restored", flavor=flavor,
                                volume=20)

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s -X POST $ENDPOINT/instances \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d "{
      \"instance\": {
        \"name\": \"Restored\",
        \"flavorRef\": \"$FLAVOR_REF\",
        \"volume\": { \"size\": 20 },
        \"restorePoint\": {
          \"backupRef\": \"$BACKUP_ID\"
        }
      }
    }" | python -m json.tool
