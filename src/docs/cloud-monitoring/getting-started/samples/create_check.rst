.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $params = array(
        'type'   => 'remote.http',
        'details' => array(
            'url'    => 'http://example.com',
            'method' => 'GET'
        ),
        'monitoring_zones_poll' => array('mzlon'),
        'period' => '100',
        'timeout' => '30',
        'target_alias' => 'default',
        'label'  => 'Website check 1'
    );

    // Test your params first
    $testResponse = $entity->testNewCheckParams($params);

    echo $response->timestamp; // When was it executed?
    echo $response->available; // Was it available?
    echo $response->status;    // Status code

    // Now create it
    $entity->createCheck($params);

.. code-block:: python

.. code-block:: ruby

  # List available check types. Note the "id" of the one you want to use.
  @client.check_types.all

  # :type is the String ID of the check type you wish to create.
  # :target_hostname is the host to be monitored. :target_alias can be specified instead, if you have ip addresses
  #   configured in the entity.
  # :monitoring_zones_poll is an Array listing the String ids of the monitoring zones from which this check should be
  #   performed.
  check = entity.checks.create(
    :type => 'remote.ping',
    :target_hostname => 'sample.hostname.com',
    :monitoring_zones_poll => ['{monitoringZoneId}']
  )

.. code-block:: sh

  $ curl -X POST -d \
    '{
      "label": "{checkLabel}",
      "type": "remote.http",
      "details": {
          "url": "{remoteUrl}",
          "method": "{httpMethod}"
      },
      "timeout": 30,
      "period": 100,
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/entities/{entityId}/checks | python -m json.tool
