.. code-block:: csharp

  await cloudAutoScaleProvider.DeleteWebhookAsync(
	{group_id}, 
	{policy_id}, 
	{webhook_id}, 
	CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  WebhookApi webhookApi =
      autoscaleApi.getWebhookApiForZoneAndGroupAndPolicy("{region}", "{groupId}", "{policyId}");

  webhookApi.delete("{webhookId}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    $webhook->delete();

.. code-block:: python

  au.delete_webhook("{groupId}", "{policyId}", "{webhookId}")

.. code-block:: ruby

  my_webhook.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/groups/{groupId}/policies/{policyId}/webhooks/{webhookId} \
    -H "X-Auth-Token: $TOKEN"
