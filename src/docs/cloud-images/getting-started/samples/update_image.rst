.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $image->update(array(
        'name'        => 'foo',
        'newProperty' => 'bar'
    ));

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  # `valueDict` is a dictionary of key/value pairs, where the key is the
  # attribute to be updated, and the value is its new value.
  imgs.update("{imageId}", {"someAttribute": "someValue",
        "anotherAttribute": "anotherValue"})

.. code-block:: ruby

  # N/A

.. code-block:: sh

  curl -s $ENDPOINT/images/{imageId} -X PATCH \
    -d '[{"op": "replace", "path": "/name", "value": "My Favorite Ubuntu"}]' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/openstack-images-v2.1-json-patch" | python -m json.tool

  # NOTE: {imageId} is a placeholder: Replace it with
  # an actual value and do not enclose it with {}.
