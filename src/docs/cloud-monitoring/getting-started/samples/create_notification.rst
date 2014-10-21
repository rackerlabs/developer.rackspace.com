.. code-block:: csharp

  NotificationTypeId notificationTypeId = NotificationTypeId.Webhook;
  NotificationDetails notificationDetails = new WebhookNotificationDetails(
	new Uri("http://example.com"));
  NewNotificationConfiguration configuration = 
	new NewNotificationConfiguration(
		"{notification_label}", 
		notificationTypeId, 
		notificationDetails);
  NotificationId notificationId = 
	await cloudMonitoringProvider.CreateNotificationAsync(
		configuration, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $notification = $service->getNotification();

  $params = array(
      'label' => 'My webhook #1',
      'type'  => 'webhook',
      'details' => array(
          'url' => 'http://example.com'
      )
  );

  // Test these params
  $response = $notification->testParams($params);

  $status = $response->status; // Was it successful?

  // Now create the notification
  $notification->create($params);

.. code-block:: python

  warning = cm.create_notification("email",
                                   label="Hey OPS, check this out.",
                                   details={"address": "{emailAddress}"})

  critical = cm.create_notification("email",
                                    label="This is critical",
                                    details={"address": "{emailAddress}"})

  plan = cm.create_notification_plan(label="default",
                                     warning_state=warning,
                                     critical_state=critical)

.. code-block:: ruby

  # :label is a String that's a friendly name for the notification.
  # :type is one of 'webhook', 'email', or 'pagerduty'.
  # :details is a :type-specific Hash of configuration options.
  notification = @client.notifications.create(
    :label => 'tell the world',
    :type => 'email',
    :details => { :address => '{emailAddress}' }
  )

.. code-block:: sh

  $ curl -X POST -d \
    '{
      "label": "{webhookNotificationLabel}",
      "type": "webhook",
      "details": {
        "url": "{webhookUrl}"
      }
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/notifications | python -m json.tool
