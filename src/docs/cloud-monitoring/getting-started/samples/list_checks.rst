.. code-block:: csharp

  ReadOnlyCollectionPage<Check, CheckId> checks = 
	await cloudMonitoringProvider.ListChecksAsync(
		{entity_id}, 
		null, 
		null, 
		CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $checks = $entity->getChecks();

.. code-block:: python

  entity.list_checks()

.. code-block:: ruby

  entity.checks.all

.. code-block:: sh

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
