.. code-block:: csharp

  ReadOnlyCollectionPage<MonitoringZone, MonitoringZoneId> monitoringZones 
	= await cloudMonitoringProvider.ListMonitoringZonesAsync(
		null, 
		null, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $zones = $service->getMonitoringZones();

.. code-block:: python

  zones = cm.list_monitoring_zones()

.. code-block:: ruby

  @client.list_monitoring_zones.body['values']

.. code-block:: sh

  $ curl -X GET $ENDPOINT/monitoring_zones \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
