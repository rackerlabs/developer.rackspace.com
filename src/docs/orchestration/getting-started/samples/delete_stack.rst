.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  err := stacks.Delete(serviceClient, "a_redis_stack", aRedisStack.ID).ExtractErr()

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.deleteStack('a_redis_stack', function(err) {
    if (err) {
      // TODO handle as appropriate
      return;
    }
  });

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @stack.delete

.. code-block:: sh

  $ curl -X DELETE $ENDPOINT/stacks/{stack_name}/{stack_id} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json"
