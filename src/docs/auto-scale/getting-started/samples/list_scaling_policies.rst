.. code-block:: csharp

  ReadOnlyCollectionPage<Policy> policies = 
	await cloudAutoScaleProvider.ListPoliciesAsync({group_id}, null, null, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup("{region}", "{scalingGroupId}");

  FluentIterable<Policy> policies = policyApi.list();

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $policies = $group->getScalingPolicies();

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  policies = au.list_policies("{scalingGroupId}")

.. code-block:: ruby

  my_group.policies

.. code-block:: sh

  curl -X GET $ENDPOINT/groups/{groupId}/policies \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
