.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  # N/A

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