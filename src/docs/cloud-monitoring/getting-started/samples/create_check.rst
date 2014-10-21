.. code-block:: csharp

  CheckTypeId checkTypeId = CheckTypeId.RemoteHttp;
  CheckDetails details = new HttpCheckDetails( 
          url: new Uri("http://docs.rackspace.com", UriKind.Absolute),
          authUser: default(string),
          authPassword: default(string),
          body: default(string),
          bodyMatches: default(IDictionary<string, string>),
          followRedirects: default(bool?),
          headers: default(IDictionary<string, string>),
          method: default(HttpMethod?),
          payload: default(string));

  IEnumerable<MonitoringZoneId> monitoringZonesPoll = new[] { "{zone_id}" };
  TimeSpan? timeout = null;
  TimeSpan? period = null;
  string targetAlias = null;
  string targetHostname = "www.example.com";
  TargetResolverType resolverType = TargetResolverType.IPv4;
  IDictionary<string, string> metadata = null;
  NewCheckConfiguration checkConfiguration = 
	new NewCheckConfiguration(
		"{check_label}", 
		checkTypeId, 
		details, 
		monitoringZonesPoll, 
		timeout, 
		period, 
		targetAlias, 
		targetHostname, 
		resolverType, 
		metadata);
  Check check = await cloudMonitoringProvider.CreateCheckAsync(
	{entity_id}, 
	{check_configuration}, 
	CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

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

  $body      = $testResponse[0];
  $timestamp = $body->timestamp; // When was it executed?
  $available = $body->available; // Was it available?
  $status    = $body->status;    // Status code

  // Now create it
  $entity->createCheck($params);

.. code-block:: python

  check = cm.create_check(entity, label="sample_check", check_type="remote.ping",
                          details={}, monitoring_zones_poll=["mzdfw", "mzlon", "mzsyd"],
                          target_hostname="sample.hostname.com")

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
