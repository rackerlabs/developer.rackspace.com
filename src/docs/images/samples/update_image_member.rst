.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    # To update an image member:
    
.. code-block:: python

.. code-block:: ruby

.. code-block:: curl
    # To update an image member:
    $ curl -s $publicurl/images/{imageId}/members/{memberId} -X PUT \
        -d '{"status":"accepted"}' -H "X-Auth-Token: $token" | python -m json.tool
    # NOTE: {imageId} and {memberId} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.
