.. code-block:: csharp

  await cloudAutoScaleProvider.DeletePolicyAsync(scalingGroup.Id, policy.Id, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup("{region}", "{scalingGroupId}");

  policyApi.delete("{policyId}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    $policy->delete();

.. code-block:: python

  au.delete_policy("{groupId}", "{policyId}")

.. code-block:: ruby

  my_policy.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/groups/{groupId}/policies/{policyId} \
    -H "X-Auth-Token: $TOKEN"
