.. code-block:: csharp

  bool? isRooted = await cloudDatabasesProvider.CheckRootEnabledStatusAsync(
    databaseInstance.Id,
    CancellationToken.None
  );

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  InstanceApi instanceApi = troveApi.getInstanceApiForZone("{region}");

  instanceApi.isRooted("{instanceId}");

.. code-block:: javascript

  client.rootEnabled(instance, function(err, rootEnabled) {
    if (err) {
      // TODO handle err as appropriate
    }

    // TODO check status of rootEnabled
  });

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
