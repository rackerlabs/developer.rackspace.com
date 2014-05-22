.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  # N/A

.. code-block:: sh

    $ curl -s $ENDPOINT/tasks -X POST \
        -d '{"type": "export","input":{"image_uuid": "{imageId}","receiving_swift_container": "exports"}}' \
        -H "X-Auth-Token: $TOKEN" | python -m json.tool

    # NOTE: {imageId} is a placeholder: Replace it with
    # an actual value and do not enclose it with {}.
