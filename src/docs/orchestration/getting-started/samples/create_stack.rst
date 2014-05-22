.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  # N/A

.. code-block:: sh

  $ curl -X POST -d \
    '{
    "stack_name": "{stackName}",
    "template": "{jsonOrchestrationTemplate}",
    "parameters": {
        "param_name-1": "{paramValue1}",
        "param_name-2": "{paramValue2}"
      },
    "timeout_mins": "{timeoutMins}"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/stacks | python -m json.tool