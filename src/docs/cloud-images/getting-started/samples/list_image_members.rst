.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $members = $image->listMembers();

  foreach ($members as $member) {
      /** @param $member OpenCloud\Image\Resource\Member */
  }

.. code-block:: python

  members = imgs.list_image_members("{imageId}")

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s $ENDPOINT/images/{imageId}/members \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
