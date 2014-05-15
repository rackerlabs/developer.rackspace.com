.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    # To import an image:
    
.. code-block:: python

.. code-block:: ruby

.. code-block:: curl
    # To create a task to import an image, specify the source
    # file for the image and the directory in your Cloud Files
    # account where you want to export the image to:
    $ curl -s $publicUrl/tasks -X POST \
        -d '{"type": "import","input":{"image_properties": {"name": "My image"},"import_from": "exports/my-image.vhd"}}' \
        -H "X-Auth-Token: $token" | python -m json.tool