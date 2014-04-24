.. code-block:: php

    $dbService = $client->databaseService();

    // Create the empty object:
    $instance = $dbService->instance();

    // Create your volume object, which in this case sets the size as 20GB:
    $volume = (object) array('size' => 20);

    // Execute the create API operation
    $instance->create(array(
        'name'   => 'development',
        'volume' => $volume,
        'flavor' => $flavor
    ));

.. code-block:: java

// We need to get a Flavor (hardware type) to run the Instance on.
flavorApi = troveApi.getFlavorApiForZone("IAD");
Flavor flavor = Iterables.getFirst(flavorApi.list(), null);

// Makes it easier to create database Instances
TroveUtils utils = new TroveUtils(troveApi);

// This call will take a while - it ensures a working instance is created.
Instance instance = utils.getWorkingInstance("IAD", "myFirstCloudDb", "" + flavor.getId(), 1);