.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    use OpenCloud\Images\Enum\MemberStatus;

    /** @param $member OpenCloud\Image\Resource\Member */
    $member = $image->getMember('{tenantId}');
    $member->updateStatus(MemberStatus::ACCEPTED);

.. code-block:: python

  # This needs to be called by the member with whom the image is being shared.
  # Valid values for `status` are:
  #    pending
  #    accepted
  #    rejected
  # Any other value will result in an InvalidImageMemberStatus exception
  # being raised.

  imgs.update_image_member("{imageId}", "accepted")

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s -X PUT $ENDPOINT/images/{imageId}/members/{memberId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d '{ "status": "accepted" }' | python -m json.tool
