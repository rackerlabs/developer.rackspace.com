.. code-block:: csharp

  NewWebhookConfiguration webhookConfiguration = new NewWebhookConfiguration("Test Webhook");
  Webhook webhook = 
	await cloudAutoScaleProvider.CreateWebhookAsync(
		{scaling_group}.Id, 
		{scaling_group}.ScalingPolicies[0].Id, 
		webhookConfiguration, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  WebhookApi webhookApi =
      autoscaleApi.getWebhookApiForZoneAndGroupAndPolicy("{region}", "{groupId}", "{policyId}");

  FluentIterable<Webhook> result = webhookApi.create("{name}", ImmutableMap.<String, Object>of());

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $webook = $policy->getWebhook();
  $webhook->create(array(
      (object) array(
          'name'     => 'My webhook',
          'metadata' => array(
              'firstKey'  => 'foo',
              'secondKey' => 'bar'
          )
      )
  ));

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  # Note: the `metadata` parameter is optional.
  webhook = au.add_webhook("{scalingGroupId}", "{policyId}",
          name="My Webhook", metadata={"someKey": "someValue"})

.. code-block:: ruby

  my_webhook = my_policy.webhooks.create :name => 'my-webhook'

.. code-block:: sh

  curl -X POST $ENDPOINT/groups/{groupId}/policies/{policyId}/webhooks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[
      {
        "metadata": {
           "someKey": "someValue"
        },
        "name": "My Webhook"
      }
    ]' | python -m json.tool
