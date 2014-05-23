.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  # Create the import task. This is the most basic format for the call.
  task = imgs.import_task("My Image", "exportSwiftContainer")
  # You can optionally specify the format (default=VHD), and give the imported
  # image a new name
  task = imgs.import_task("My Image", "exportSwiftContainer",
        img_format="someOddFormat", img_name = "My New Image"))
  # Wait for the task to complete
  pyrax.utils.utils.wait_for_build(task, verbose=True,
        desired=["success", "failure"])
  # The task's `status` attribute will be either "success" or "failure".
  # In the case of a failure, its `message` attribute will explain why.

.. code-block:: ruby

  # N/A

.. code-block:: sh

    # To create a task to import an image, specify the source
    # file for the image and the directory in your Cloud Files
    # account where you want to export the image to:

    curl -s $ENDPOINT/tasks -X POST \
        -d '{"type": "import","input":{"image_properties": {"name": "My image"},"import_from": "exports/my-image.vhd"}}' \
        -H "X-Auth-Token: $TOKEN" | python -m json.tool
