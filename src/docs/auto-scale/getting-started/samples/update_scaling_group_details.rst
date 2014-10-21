.. code-block:: csharp

  TimeSpan cooldown = TimeSpan.FromSeconds(60);
  await cloudAutoScaleProvider.SetGroupConfigurationAsync(
	{group_id}, 
	new GroupConfiguration(
		"group_name", 
		cooldown, 
		0, 
		0, 
		new JObject()), 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  GroupApi groupApi = autoscaleApi.getGroupApiForZone("{region}");

  GroupConfiguration groupConfiguration = GroupConfiguration.builder()
          .maxEntities(25)
          .cooldown(60)
          .name("New name")
          .minEntities(5)
          .metadata(ImmutableMap.of("notes", "This is an autoscale group for examples"))
          .build();

  groupApi.updateGroupConfiguration("{groupId}", groupConfiguration);

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  au = pyrax.autoscale
  au.update("{scalingGroupId}", name="My Group", cooldown=120,
          min_entities=1, max_entities=25, metadata={"someKey": "someValue"})

.. code-block:: ruby

  group_config = my_group.group_config

  group_config.cooldown = 120
  group_config.max_entities = 16
  group_config.save

.. code-block:: sh

  $ curl -X PUT $ENDPOINT/groups/{scalingGroupId}/config \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d '{
       "name": "My Group",
       "cooldown": 120,
       "minEntities": 1,
       "maxEntities": 25
     }' | python -m json.tool
