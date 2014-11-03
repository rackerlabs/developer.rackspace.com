.. code-block:: csharp

  ScalingGroup scalingGroup = 
	await cloudAutoScaleProvider.GetGroupAsync({group_id},CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  GroupApi groupApi = autoscaleApi.getGroupApiForZone("{region}");

  Group group = groupApi.get("{groupId}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $group = $service->group('{groupId}');

.. code-block:: python

  # After authenticating
  au = pyrax.autoscale
  scaling_group = au.get("{scalingGroupId}")

.. code-block:: ruby

  my_group = service.groups.get('{groupId}')

.. code-block:: sh

  curl -X GET $ENDPOINT/groups/{groupId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
