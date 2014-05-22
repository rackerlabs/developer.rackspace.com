.. code-block:: csharp

.. code-block:: java

  // Create a user by specifying a username and password, and give them access
  // to one database.

  UserApi userApi = troveApi.getUserApiForZoneAndInstance("{region}", "{instanceId}");

  boolean result = userApi.create("{dbUsername}", "{dbPassword}", "{dbName}");

.. code-block:: javascript

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
