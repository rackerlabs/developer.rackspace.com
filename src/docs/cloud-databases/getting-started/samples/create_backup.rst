.. code-block:: csharp

  BackupConfiguration backupConfiguration = new BackupConfiguration(
    databaseInstance.Id,
    "backup_name",
    "friendly description"
  );

  Backup backup = await cloudDatabasesProvider.CreateBackupAsync(
    backupConfiguration,
    AsyncCompletionOption.RequestCompleted,
    CancellationToken.None,
    null
  );

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  backup = inst.create_backup("backup_name")

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s -X POST $ENDPOINT/backups \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
      "backup": {
        "instance": "{instanceId}",
        "name": "backup_name",
        "description": "friendly description"
      }
    }' | python -m json.tool

  # Note the ID of the created backup for later.
  export BACKUP_ID="{backupId}"
