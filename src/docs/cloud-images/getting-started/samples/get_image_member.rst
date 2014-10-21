.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  /** @param $member OpenCloud\Image\Resource\Member */
  $member = $image->getMember('{tenantId}');

.. code-block:: python

  # {projectId} is the account ID of the member.
  member = imgs.get_image_member("{imageId}", "{projectId}")

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s $ENDPOINT/images/{imageId}/members/{memberId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
