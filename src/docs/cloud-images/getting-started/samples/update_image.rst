.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

    $image->update(array(
        'someAttribute'    => 'someValue',
        'anotherAttribute' => 'anotherValue'
    ));

.. code-block:: python

  # `valueDict` is a dictionary of key/value pairs, where the key is the
  # attribute to be updated, and the value is its new value.
  imgs.update("{imageId}", {"someAttribute": "someValue",
        "anotherAttribute": "anotherValue"})

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -s $ENDPOINT/images/{imageId} -X PATCH \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/openstack-images-v2.1-json-patch" \
    -H "Accent: application/json" \
    -d '[
      {
        "op": "add",
        "path": "/someAttribute",
        "value": "someValue"
      },
      {
        "op": "add",
        "path": "/anotherAttribute",
        "value": "anotherValue"
      }
    ]' | python -m json.tool
