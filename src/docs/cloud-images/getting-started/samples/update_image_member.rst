.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  # N/A

.. code-block:: sh

    curl -s $ENDPOINT/images/{imageId}/members/{memberId} -X PUT \
      -d '{"status":"accepted"}' -H "X-Auth-Token: $TOKEN" | python -m json.tool

    # NOTE: {imageId} and {memberId} are placeholders:
    # Replace them with actual values and do not enclose the values with {}.
