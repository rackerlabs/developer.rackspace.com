.. code-block:: csharp

  // update the webhook name
  string updatedName = "Updated Webhook";
  await cloudAutoScaleProvider.UpdateWebhookAsync(
	{group_id}, 
	{policy_id}, 
	{webhook_id}, 
	new UpdateWebhookConfiguration(updatedName), 
	CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  WebhookApi webhookApi =
    autoscaleApi.getWebhookApiForZoneAndGroupAndPolicy("{region}", "{groupId}", "{policyId}");

  webhookApi.update("{webhookId}", "New name", ImmutableMap.<String, Object>of());

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $webhook->update(array(
      'metadata' => array(
          'someKey' => 'someValue'
      )
  ));

.. code-block:: python

  au.update_webhook("{groupId}", "{policyId}", "{webhookId}",
          name="My Webhook", metadata={"someKey": "someValue"})

.. code-block:: ruby

  my_webhook.name = 'webhook1'
  my_webhook.metadata = {
      :owner => 'webteam'
  }
  my_webhook.save

.. code-block:: sh

  curl -X PUT $ENDPOINT/groups/{groupId}/policies/{policyId}/webhooks/{webhookId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
      "name": "webhook1",
      "metadata": { "someKey": "someValue" }
    }' | python -m json.tool
