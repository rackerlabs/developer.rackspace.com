.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $alarm = $check->getAlarm();
    $alarm->create(array(
        'check_id' => '{checkId}',
        'criteria' => 'if (metric["duration"] >= 2) { return new AlarmStatus(OK); } return new AlarmStatus(CRITICAL);',
        'notification_plan_id' => '{notificationPlanId}'
    ));

.. code-block:: python

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