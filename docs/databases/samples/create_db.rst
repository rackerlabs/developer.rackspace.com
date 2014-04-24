.. code-block:: php

    $db = $instance->database();

    $db->create(array(
        'name' => 'wordpress'
    ));

.. code-block:: java

// Get the instance API
InstanceApi instanceApi = troveApi.getInstanceApiForZone("IAD");

// Using the instance API, get the database API for that instance by using the instance ID and instance Zone (or location)
DatabaseApi databaseApi = troveApi.getDatabaseApiForZoneAndInstance("IAD", myInstanceId);

// Create a new database by name.
boolean result = databaseApi.create("helloIaMaDatabase");