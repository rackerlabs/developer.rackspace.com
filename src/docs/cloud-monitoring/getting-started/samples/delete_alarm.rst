.. code-block:: csharp

  await cloudMonitoringProvider.RemoveAlarmAsync({entity_id}, {alarm_id}, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $alarm->delete();

.. code-block:: python

  alarm.delete()

.. code-block:: ruby

  alarm.destroy

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/entities/{entityId}/alarms/{alarmId} \
    -H "X-Auth-Token: $TOKEN"
