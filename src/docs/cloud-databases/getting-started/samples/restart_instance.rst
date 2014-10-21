.. code-block:: csharp

  await cloudDatabasesProvider.RestartDatabaseInstanceAsync(
    databaseInstance.Id,
    AsyncCompletionOption.RequestCompleted,
    CancellationToken.None,
    null
  );

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.restartInstance(instance, function(err) {
    if (err) {
      // TODO handle err as appropriate
    }
  });

.. code-block:: php

  $instance->restart();

.. code-block:: python

  inst.restart()

.. code-block:: ruby

  instance.restart
  instance.wait_for { ready? }

.. code-block:: sh

  curl -s -X POST $ENDPOINT/instances/{instanceId}/action \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d '{ "restart": {} }' | python -m json.tool
