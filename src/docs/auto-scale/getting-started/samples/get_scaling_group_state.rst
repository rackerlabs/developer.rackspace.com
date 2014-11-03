.. code-block:: csharp

  GroupState groupState = 
	await cloudAutoScaleProvider.GetGroupStateAsync({group_id}, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  GroupApi groupApi = autoscaleApi.getGroupApiForZone("{region}");

  GroupState groupState = groupApi.getState("{scalingGroupId}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $state = $group->getState();

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  state = au.get_state("{scalingGroupId}")

.. code-block:: ruby

  my_group.state

.. code-block:: sh

  curl -X GET $ENDPOINT/groups/{groupId}/state \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
