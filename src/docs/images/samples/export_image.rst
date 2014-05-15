.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    # To export an image:
    
.. code-block:: python

.. code-block:: ruby

.. code-block:: curl
    # To create a task to export an image, specify the image ID,
    # and source container in your Cloud Files account:
    $ curl -s $publicUrl/tasks -X POST \
        -d '{"type": "export","input":{"image_uuid": "{imageId}","receiving_swift_container": "exports"}}' \
        -H "X-Auth-Token: $token" | python -m json.tool
    # NOTE: {imageId} is a placeholder: Replace it with 
    # an actual value and do not enclose it with {}.