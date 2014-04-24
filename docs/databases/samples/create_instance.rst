.. code-block:: csharp


.. code-block:: java


.. code-block:: javascript


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

.. code-block:: python

    # For this example we'll create a 20GB instance using the flavor with the
    # least RAM.
    flavors = cdb.list_flavors()
    flavors.sort(key=lambda flav: flav.ram)
    flavor = flavors[0]
    inst = cdb.create("sample_instance", volume=20, flavor=flavor)

.. code-block:: ruby
