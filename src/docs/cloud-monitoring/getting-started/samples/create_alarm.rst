.. code-block:: csharp

  string alarmName = "{alarm_name}";
  string criteria = null;
  bool? enabled = null;
  IDictionary<string, string> alarmMetadata = null;
  NewAlarmConfiguration alarmConfiguration = 
	new NewAlarmConfiguration(
		{check_id}, 
		{notification_plan_id}, 
		criteria, 
		enabled, 
		alarmName, 
		alarmMetadata);
  AlarmId alarmId = 
	await cloudMonitoringProvider.CreateAlarmAsync(
		{entity_id}, 
		alarmConfiguration, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $alarm = $entity->getAlarm();
  $alarm->create(array(
      'check_id' => '{checkId}',
      'criteria' => 'if (metric["duration"] >= 2) { return new AlarmStatus(OK); } return new AlarmStatus(CRITICAL);',
      'notification_plan_id' => '{notificationPlanId}'
  ));

.. code-block:: python

  alarm = cm.create_alarm(entity, check, plan,
                          ("if (rate(metric['average']) > 10) {"
                           " return new AlarmStatus(WARNING); "
                           "} return new AlarmStatus(OK);"))

.. code-block:: ruby

  # To list known notification plans:

  @client.list_notification_plans.body['values']

  alarm = entity.alarms.create(
    :check => check,
    :notification_plan_id => '{notificationPlanId}'
  )

.. code-block:: sh

  $ curl -X POST -d \
    '{
      "check_id": "{checkId}",
      "criteria": "{alarmCriteria}",
      "notification_plan_id": "{notificationPlanId}"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/entities/{entityId}/alarms | python -m json.tool
