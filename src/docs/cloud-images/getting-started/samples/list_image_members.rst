.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $members = $image->listMembers();

    foreach ($members as $member) {
        /** @param $member OpenCloud\Image\Resource\Member */
    }

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  members = imgs.list_image_members("{imageId}")

.. code-block:: ruby

  # N/A

.. code-block:: sh

    curl -s $ENDPOINT/images/{imageId}/members \
      -H "X-Auth-Token: $TOKEN" | python -m json.tool

    # NOTE: {imageId} is a placeholder: replace it with
    # an actual value and do not enclose it with {}.
