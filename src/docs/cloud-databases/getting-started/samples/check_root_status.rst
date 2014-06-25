.. code-block:: csharp

  CloudDatabasesProvider cloudDatabasesProvider = new CloudDatabasesProvider(cloudIdentity, "{region}", null);
  DatabaseInstanceId databaseInstanceId = new DatabaseInstanceId("database_instance_id);
  bool? isRooted = await cloudDatabasesProvider.CheckRootEnabledStatusAsync(databaseInstanceId, CancellationToken.None);

.. code-block:: java

  InstanceApi instanceApi = troveApi.getInstanceApiForZone("{region}");
  instanceApi.isRooted("{instanceId}");

.. code-block:: javascript

.. code-block:: php

  $instance->isRootEnabled();

.. code-block:: python

  root_enabled = cdb.root_user_status()

.. code-block:: ruby

  root_enabled = instance.root_user_enabled?

.. code-block:: sh

  curl -s -X GET $ENDPOINT/instances/{instanceId}/root \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
