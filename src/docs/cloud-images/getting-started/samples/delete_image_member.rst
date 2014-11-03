.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    $image->deleteMember('{tenantId}');

.. code-block:: python

  # Use the Rackspace account number of the user to remove as {memberId}.
  imgs.delete_image_member("{imageId}", "{memberId}")

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -i -X DELETE $ENDPOINT/images/{imageId}/members/{memberId} \
      -H "X-Auth-Token: $TOKEN"
