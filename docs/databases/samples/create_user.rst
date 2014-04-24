.. code-block:: php

    $user = $instance->user();

    // Create user and assign them to some DBs
    $user->create(array(
        'name'      => 'jane_doe',
        'password'  => '6hUH!$Hu-77Ca=reVacH',
        'databases' => array('wordpress', 'other_db')
    ));

.. code-block:: java

// Get the instance API
InstanceApi instanceApi = troveApi.getInstanceApiForZone("IAD");

// Use the instance API to get the user API for a specific instance by providing the instance ID.
UserApi userApi = troveApi.getUserApiForZoneAndInstance("IAD", myInstanceId);

// Create the user by providing the user name, user password, and the database the user is allowed to access.
boolean result = userApi.create("myusername", "mypasswordwhichcouldbebetter", "myDatabase");