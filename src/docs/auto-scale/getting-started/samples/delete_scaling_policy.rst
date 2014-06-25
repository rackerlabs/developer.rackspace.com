.. code-block:: csharp

.. code-block:: java

  PolicyApi policyApi = autoscaleApi.getPolicyApiForZoneAndGroup("{region}", "{scalingGroupId}");
  policyApi.delete("{policyId}");

.. code-block:: javascript

.. code-block:: php

    $policy->delete();

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  au.delete_policy("{scalingGroupId}", "{policyId}")

.. code-block:: ruby

  my_policy.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/groups/{groupId}/policies/{policyId} \
    -H "X-Auth-Token: $TOKEN"
