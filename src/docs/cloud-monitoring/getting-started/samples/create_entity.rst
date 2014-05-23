.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

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