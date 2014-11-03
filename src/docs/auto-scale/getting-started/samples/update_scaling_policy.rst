.. code-block:: csharp

  TimeSpan cooldown = TimeSpan.FromSeconds(60);
  int desiredCapacity = 1;
  PolicyConfiguration policyConfiguration = 
	PolicyConfiguration.Capacity("Capacity 1 Policy", desiredCapacity, cooldown);
  await cloudAutoScaleProvider.SetPolicyAsync(
	{group_id}, 
	{policy_id), 
	policyConfiguration, 
	CancellationToken.None);
 
.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup("{region}", "{scalingGroupId}");

  CreateScalingPolicy scalingPolicy = CreateScalingPolicy.builder()
            .cooldown(3)
            .type(ScalingPolicyType.WEBHOOK)
            .name("New name")
            .targetType(ScalingPolicyTargetType.INCREMENTAL)
            .target("1")
            .build();

  policyApi.update("{policyId}", scalingPolicy);

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $policy->update(array(
      'name'     => 'New name',
      'cooldown' => 120
  ));

.. code-block:: python

  au = pyrax.autoscale
  au.update_policy("{scalingGroupId}", "{policyId}", name="My Policy",
          policy_type="webhook", cooldown=120, change=10, is_percent=True,
          desired_capacity=7)

.. code-block:: ruby

  my_policy.cooldown = 60
  my_policy.change = 3
  my_policy.save

.. code-block:: sh

  curl -X PUT $ENDPOINT/groups/{scalingGroupId}/policies/{policyId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
      "change": 10,
      "cooldown": 120,
      "name": "My policy",
      "type": "webhook"
    }' | python -m json.tool
