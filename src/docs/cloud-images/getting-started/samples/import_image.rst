.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  # N/A

.. code-block:: sh

    # To create a task to import an image, specify the source
    # file for the image and the directory in your Cloud Files
    # account where you want to export the image to:

    curl -s $ENDPOINT/tasks -X POST \
        -d '{"type": "import","input":{"image_properties": {"name": "My image"},"import_from": "exports/my-image.vhd"}}' \
        -H "X-Auth-Token: $TOKEN" | python -m json.tool
