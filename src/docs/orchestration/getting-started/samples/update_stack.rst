.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @stack.stack_name = "New Stack Name"
  @stack.save

.. code-block:: sh

  $ curl -X PUT -d \
    '{
    "template": "{updatedJSONOrchestrationTemplate}",
    "parameters": {
        "param_name-1": "{updatedParamValue1}",
        "param_name-2": "{updatedParamValue2}"
      },
    "timeout_mins": "{timeoutMins}"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/stacks/{stack_name}/{stack_id} | python -m json.tool
