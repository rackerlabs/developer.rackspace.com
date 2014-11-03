.. code-block:: csharp

  ReadOnlyCollectionPage<ScalingGroup> scalingGroups = 
	await cloudAutoScaleProvider.ListScalingGroupsAsync(
		null, 
		null, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  GroupApi groupApi = autoscaleApi.getGroupApiForZone("{region}");

  FluentIterable<Group> groups = groupApi.list();

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    $groups = $service->groupList();

.. code-block:: python

    scaling_groups = au.list()

.. code-block:: ruby

  scaling_groups = service.groups

.. code-block:: sh

  curl -X GET $ENDPOINT/groups \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
