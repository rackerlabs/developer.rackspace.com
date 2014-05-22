.. code-block:: csharp

.. code-block:: java

  // We need to get a Flavor (hardware type) to run the Instance on.
  FlavorApi flavorApi = troveApi.getFlavorApiForZone("{region}");
  Flavor flavor = Iterables.getFirst(flavorApi.list(), null);

  TroveUtils utils = new TroveUtils(troveApi);

  Instance instance = utils.getWorkingInstance("{region}", "sample_instance", "" + flavor.getId(), 1);

.. code-block:: javascript

.. code-block:: php

  $dbService = $client->databaseService();

  // Create the empty object:
  $instance = $dbService->instance();

  // Create your volume object, which in this case sets the size as 20GB:
  $volume = (object) array('size' => 20);

  // Execute the create API operation
  $instance->create(array(
      'name'   => 'sample_instance',
      'volume' => $volume,
      'flavor' => $flavor
  ));

.. code-block:: python

  # For this example, we'll create a 20GB instance using the flavor with the
  # least RAM.

  flavors = cdb.list_flavors()
  flavors.sort(key=lambda flav: flav.ram)
  flavor = flavors[0]
  inst = cdb.create("sample_instance", volume=20, flavor=flavor)

.. code-block:: ruby

  # For this example, we'll create a 20GB instance using the flavor with the
  # least RAM.

  flavor = @client.flavors.sort_by(&:ram)[0]
  instance = @client.instances.create(
    :name => 'sample_instance',
    :volume_size => 20,
    :flavor_id => flavor.id
  )
  instance.wait_for { ready? }

.. code-block:: sh
