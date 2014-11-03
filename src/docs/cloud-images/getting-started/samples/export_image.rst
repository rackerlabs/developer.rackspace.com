.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported

.. code-block:: python

  # Create the export task
  task = imgs.export_task("{imageId}", "receivingSwiftContainer")

  # Wait for the task to complete
  pyrax.utils.wait_for_build(task, verbose=True,
        desired=["success", "failure"])

  # The task's `status` attribute will be either "success" or "failure".
  # In the case of a failure, its `message` attribute will explain why.

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s -X POST $ENDPOINT/tasks \
    -H "X-Auth-Token: $TOKEN" \
    -d '{
      "type": "export",
      "input": {
        "image_uuid": "{imageId}",
        "receiving_swift_container": "receivingSwiftContainer"
      }
    }' | python -m json.tool
