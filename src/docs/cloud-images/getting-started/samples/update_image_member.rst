.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    use OpenCloud\Images\Enum\MemberStatus;

    /** @param $member OpenCloud\Image\Resource\Member */
    $member = $image->getMember('{tenantId}');
    $member->updateStatus(MemberStatus::ACCEPTED);

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  # This needs to be called by the member with whom the image is being shared.
  # Valid values for `status` are:
  #    pending
  #    accepted
  #    rejected
  # Any other value will result in an InvalidImageMemberStatus exception
  # being raised.
  imgs.update_image_member("{imageId}", "{status}")

.. code-block:: ruby

  # N/A

.. code-block:: sh

    curl -s $ENDPOINT/images/{imageId}/members/{memberId} -X PUT \
      -d '{"status":"accepted"}' -H "X-Auth-Token: $TOKEN" | python -m json.tool

    # NOTE: {imageId} and {memberId} are placeholders:
    # Replace them with actual values and do not enclose the values with {}.
