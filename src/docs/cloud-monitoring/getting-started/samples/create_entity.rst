.. code-block:: csharp

  NewEntityConfiguration entityConfiguration = new NewEntityConfiguration(
	"{entity_label}", 
	null, 
	null, 
	null);
  EntityId entityId = await cloudMonitoringProvider.CreateEntityAsync(
	entityConfiguration, 
	CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $service = $client->cloudMonitoringService('cloudMonitoring', '{region}');

  $service->createEntity(array(
      'label' => '{entityLabel}',
      'ip_addresses' => array(
          'default' => '127.0.0.4',
          'b'       => '127.0.0.5',
          'c'       => '127.0.0.6',
          'test'    => '127.0.0.7'
      ),
      'metadata' => array(
          'foo'  => 'bar'
      )
  ));

.. code-block:: python

  entity = cm.create_entity(label="bestbox",
                            ip_addresses={"bestbox": "192.168.1.32"})

.. code-block:: ruby

  entity = @client.entities.create(:label => 'somehost.domain.com')

.. code-block:: sh

  $ curl -X POST -d \
    '{
      "label": "{entityLabel}",
      "ip_addresses": {
          "entity_ip_addresses_hash_key": "{ipAddress1}",
          "b": "{ipAddress2}",
          "c": "{ipAddress3}",
          "test": "{ipAddress4}"
      }
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/entities | python -m json.tool
