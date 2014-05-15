.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $imageId = '{imageId}';
    $image = $service->image($imageId);
    
.. code-block:: python

.. code-block:: ruby

.. code-block:: curl
    # To get image details:
    $ curl -s $publicUrl/images/{imageId} \
        -H "X-Auth-Token: $token" | python -m json.tool
    # NOTE: {imageId} is a placeholder: Replace it with 
    # an actual value and do not enclose it with {}.
