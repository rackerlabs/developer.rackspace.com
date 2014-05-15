.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    # To update an image:
    
.. code-block:: python

.. code-block:: ruby

.. code-block:: curl
    # To update an image:
    $ curl -s $publicUrl/images/{imageId} -X PATCH \
        -d'[{"op": "replace", "path": "/name", "value": "My Favorite Ubuntu"}]' \
        -H "X-Auth-Token: $token" \
        -H "Accept: application/openstack-images-v2.1-json-patch" | python -m json.tool
    # NOTE: {imageId} is a placeholder: Replace it with 
    # an actual value and do not enclose it with {}.