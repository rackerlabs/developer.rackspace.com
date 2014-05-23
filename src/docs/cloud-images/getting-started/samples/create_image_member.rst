.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    /** @param $response Guzzle\Http\Message\Response */
    $response = $image->createMember('{tenantId}');

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  # Call the `add_image_member()` method, passing in the ID of the image
  # and the project ID of the member to add.
  member = imgs.add_image_member("{imageId}", "{projectId}")

.. code-block:: ruby

  # N/A

.. code-block:: sh

  # To create an image member for an image, specify the image ID
  # for the image you want to share and the user name (member ID)
  # for the user that you want to share the image with:

  curl -s $ENDPOINT/images/{imageId}/members -X POST \
    -d '{"member":"{memberId}"}' \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool

  # NOTE: {imageId} and {memberId} are placeholders: replace these with
  # actual values and do not enclose these values with {}.
