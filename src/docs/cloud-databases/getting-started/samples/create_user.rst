.. code-block:: csharp

   DatabaseInstanceId databaseInstanceId = new DatabaseInstanceId("{database_instance_id}");
   DatabaseName databaseName = new DatabaseName("{databaseName}");
   UserName userName = new UserName("{username}");
   UserConfiguration userConfiguration = 
	new UserConfiguration(userName, "{password}", new DatabaseName[] { databaseName });
   await cloudDatabasesProvider.CreateUserAsync(
	databaseInstanceId, 
	userConfiguration, 
	CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Create a user with username and password and give them access to one database.
  UserApi userApi = troveApi.getUserApiForZoneAndInstance("{region}", "{instanceId}");

  userApi.create("{dbUsername}", "{dbPassword}", "{dbName}");

.. code-block:: javascript

  client.createUser({
    instance: instance,
    username: {dbUsername},
    password: {dbPassword},
    databases: [ {dbName1}, {dbName2} ]
  }, function(err, user) {
    if (err) {
      // TODO handle err as appropriate
    }

    // TODO use your newly created user here
  });

.. code-block:: php

  $user = $instance->user();

  // Create a user by specifying a username and password, and give them access
  // to two databases.
  $user->create(array(
      'name'      => '{dbUsername}',
      'password'  => '{dbPassword}',
      'databases' => array('{dbName1}', '{dbName2}')
  ));

.. code-block:: python

  # Create a user by specifying the username and password, and give them
  # access to two databases.
  inst.create_user("{dbUsername}", "{dbPassword}", ["{dbName1}", "{dbName2}"])

.. code-block:: ruby

  # Create a user by specifying the username and password, and give them
  # access to two databases.

  instance.users.create(
    :name => '{dbUsername}',
    :password => '{dbPassword}',
    :databases => ['{dbName1}', '{dbName2}']
  )

.. code-block:: sh

  curl -s -X POST $ENDPOINT/instances/{instanceId}/users \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
      "users": [
        {
          "databases": [
            { "name": "{dbName1}" },
            { "name": "{dbName2}" }
          ],
          "name": "{dbUsername}",
          "password": "{dbPassword}"
        }
      ]
    }' | python -m json.tool
