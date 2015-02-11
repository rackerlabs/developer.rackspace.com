.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  var stack = new pkgcloud.providers.openstack.orchestration.Stack(client, {
    id: '{stackId}',
    name: '{stackName}'
  });

  client.getStack(stack, function (err, stack) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your stack here
  });

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @client.stacks.get(@stack.name, @stack.id)

.. code-block:: sh

  $ curl -X GET $ENDPOINT/stacks/{stack_name}/{stack_id} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
