.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  # Create the export task
  task = imgs.export_task("{imageId}", "receivingSwiftContainer")
  # Wait for the task to complete
  pyrax.utils.utils.wait_for_build(task, verbose=True,
        desired=["success", "failure"])
  # The task's `status` attribute will be either "success" or "failure".
  # In the case of a failure, its `message` attribute will explain why.

.. code-block:: ruby

  # N/A

.. code-block:: sh

    $ curl -s $ENDPOINT/tasks -X POST \
        -d '{"type": "export","input":{"image_uuid": "{imageId}","receiving_swift_container": "exports"}}' \
        -H "X-Auth-Token: $TOKEN" | python -m json.tool

    # NOTE: {imageId} is a placeholder: Replace it with
    # an actual value and do not enclose it with {}.
