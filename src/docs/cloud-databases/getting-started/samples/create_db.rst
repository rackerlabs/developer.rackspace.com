.. code-block:: csharp

  CloudDatabasesProvider cloudDatabasesProvider = new CloudDatabasesProvider(cloudIdentity, "{region}", null);
  DatabaseInstanceId databaseInstanceId = new DatabaseInstanceId("database_instance_id");
  DatabaseName databaseName = new DatabaseName("{database_name}");
  DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(databaseName);
  await cloudDatabasesProvider.CreateDatabaseAsync(databaseInstanceId, databaseConfiguration, CancellationToken.None);

.. code-block:: java

  DatabaseApi databaseApi = troveApi.getDatabaseApiForZoneAndInstance("{region}", "{instanceId}");
  databaseApi.create("sample_db");

.. code-block:: javascript


.. code-block:: php

  $db = $instance->database();

  $db->create(array(
      'name' => 'sample_db'
  ));

.. code-block:: python

  inst.create_database('sample_db')

.. code-block:: ruby

  database = instance.databases.create(:name => 'sample_db')

.. code-block:: sh

  curl -s -X POST $ENDPOINT/instances/{instanceId}/databases \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
      "databases": [
        { "name": "sampledb" }
      ]
    }' | python -m json.tool
