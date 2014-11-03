.. code-block:: csharp

  PolicyConfiguration policyConfiguration = 
	PolicyConfiguration.Capacity(
		"Capacity 0 Policy", 
		0, 
		TimeSpan.FromSeconds(60));
  Policy policy = 
	await cloudAutoScaleProvider.CreatePolicyAsync(
		{group_id}, 
		policyConfiguration, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup("{region}", "{scalingGroupId}");

  CreateScalingPolicy scalingPolicy = CreateScalingPolicy.builder()
            .cooldown(360)
            .type(ScalingPolicyType.WEBHOOK)
            .name("{policyName}")
            .targetType(ScalingPolicyTargetType.INCREMENTAL)
            .target("1")
            .build();

  FluentIterable<ScalingPolicy> policies = policyApi.create(ImmutableList.of(scalingPolicy));

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $policy = $group->getScalingPolicy();
  $policy->create(array(
      array(
      (object) array(
          'name'     => 'My policy',
          'change'   => 1,
          'type'     => 'webhook',
          'cooldown' => 360
      )

));

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  au.add_policy("{scalingGroupId}", "My Policy", cooldown=360, change=1,
                is_percent=False)

.. code-block:: ruby

  my_policy = my_group.policies.create :name => 'Scale by one server',
    :cooldown => 360,
    :type => 'webhook',
    :change => 1

.. code-block:: sh

  curl -X POST $ENDPOINT/groups/{groupId}/policies \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '[
       {
          "name":"{policyName}",
          "change":1,
          "cooldown":360,
          "type":"webhook"
       }
    ]' | python -m json.tool
