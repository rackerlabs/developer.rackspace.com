.. code-block:: csharp

  FlavorRef flavorRef = new FlavorRef(databaseFlavor.Id);
  DatabaseVolumeConfiguration databaseVolumeConfiguration = new DatabaseVolumeConfiguration(
    {database_volume_configuration_id}
  );
  DatabaseInstanceConfiguration databaseInstanceConfiguration = new DatabaseInstanceConfiguration(
    flavorRef,
    databaseVolumeConfiguration,
    "sample_instance"
  );

  DatabaseInstance databaseInstance = await cloudDatabasesProvider.CreateDatabaseInstanceAsync(
    databaseInstanceConfiguration,
    AsyncCompletionOption.RequestCompleted,
    CancellationToken.None,
    null
  );

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  TroveUtils utils = new TroveUtils(troveApi);

  Instance instance = utils.getWorkingInstance("{region}", "{instanceName}", "{flavorId}", 1);

.. code-block:: javascript

  // For this example, we'll create a 20GB instance using the flavor with the
  // least RAM.

  flavors = flavors.sort(function(a, b) {
    return a.ram >= b.ram;
  });

  client.createInstance({
    flavor: flavors[0],
    name: 'sample_instance',
    size: 20
  }, function(err, instance) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your newly created instance here
  });

.. code-block:: php

  $dbService = $client->databaseService(null, '{region}');

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

  curl -s -X POST $ENDPOINT/instances \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d "{
      \"instance\": {
        \"name\": \"sample_instance\",
        \"flavorRef\": \"$FLAVOR_REF\",
        \"volume\": { \"size\": 20 }
      }
    }" | python -m json.tool
