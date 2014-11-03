.. code-block:: csharp

  cloudAutoScaleProvider.ExecutePolicyAsync(scalingGroup.Id, policy.Id, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  WebhhookApi webhookApi =
      autoscaleApi.getWebhookApiForZoneAndGroupAndPolicy("{region}", "{groupId}", "{policyId}");

  Webhook webhook = webhookApi.get("{webhookId}");
  AutoscaleUtils.execute(webhook.getAnonymousExecutionURI().get());

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    $policy->execute();

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  au.execute_policy("{scalingGroupId}", "{policyId}")

.. code-block:: ruby

  my_policy.execute

.. code-block:: sh

  curl -X POST $ENDPOINT/groups/{groupId}/policies/{policyId}/execute \
    -H "X-Auth-Token: $TOKEN"
