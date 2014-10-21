.. code-block:: csharp

  await cloudMonitoringProvider.RemoveEntityAsync({entity_id}, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $entity->delete();

.. code-block:: python

  entity.delete()

.. code-block:: ruby

  entity.destroy

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/entities/{entityId} \
    -H "X-Auth-Token: $TOKEN"
