.. code-block:: csharp

  RootUser rootUser = await cloudDatabasesProvider.EnableRootUserAsync(
    databaseInstance.Id,
    CancellationToken.None
  );

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  InstanceApi instanceApi = troveApi.getInstanceApiForZone("{region}");

  String password = instanceApi.enableRoot("{instanceId}");

.. code-block:: javascript

  client.enableRoot(instance, function(err) {
    if (err) {
      // TODO handle err as appropriate
    }
  });

.. code-block:: php

  /** @param $user OpenCloud\Database\Resource\User */
  $user = $instance->enableRootUser();

  // Store the root password to your local filesystem.
  file_put_contents('~/.root_mysql_pwd', $user->password);

.. code-block:: python

  inst.enable_root_user()

.. code-block:: ruby

  instance.enable_root_user

  # Store the root password to your local filesystem.
  File.write('~/.root_mysql_pwd', instance.root_password)

.. code-block:: sh

  curl -s -X POST $ENDPOINT/instances/{instanceId}/root \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
