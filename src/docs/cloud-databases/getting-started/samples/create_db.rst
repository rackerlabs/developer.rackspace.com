.. code-block:: csharp

  DatabaseName databaseName = new DatabaseName("sample_db");
  DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(databaseName);

  await cloudDatabasesProvider.CreateDatabaseAsync(
    databaseInstance.Id,
    databaseConfiguration,
    CancellationToken.None
  );

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  DatabaseApi databaseApi = troveApi.getDatabaseApiForZoneAndInstance("{region}", "{instanceId}");

  databaseApi.create("{databaseName}");

.. code-block:: javascript

  client.createDatabase({
    name: 'sample_db',
    instance: instance
  }, function(err, db) {
    if (err) {
      // TODO handle err as appropriate
    }

    // TODO use your newly created DB here
  });

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
        { "name": "sample_db" }
      ]
    }' | python -m json.tool
