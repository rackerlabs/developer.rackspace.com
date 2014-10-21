.. code-block:: csharp

  await cloudAutoScaleProvider.DeleteGroupAsync(scalingGroup.Id, true, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  GroupApi groupApi = autoscaleApi.getGroupApiForZone("{region}");

  groupApi.delete("{groupId}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $group->delete();

.. code-block:: python

  au.delete("{groupId}")

.. code-block:: ruby

  my_group.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/groups/{groupId} \
    -H "X-Auth-Token: $TOKEN"
