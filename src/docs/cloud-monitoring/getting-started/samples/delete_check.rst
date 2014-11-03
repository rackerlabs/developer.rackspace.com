.. code-block:: csharp

  await cloudMonitoringProvider.RemoveCheckAsync({entity_id}, {check_id}, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $check->delete();

.. code-block:: python

  check.delete()

.. code-block:: ruby

  check.destroy

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/entities/{entityId}/checks/{checkId} \
    -H "X-Auth-Token: $TOKEN"
