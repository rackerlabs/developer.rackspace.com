.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  opts := volumes.UpdateOpts{Name: "new_name"}
  vol, err := volumes.Update{client, "{volumeId}", opts).Extract()

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // assuming we've already loaded the details of a volume into a
  // local variable named volume

  volume.name = 'New Volume Name';

  client.updateVolume(volume, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

  $volume->rename(array(
      'display_name' => 'New Volume Name'
  ));

.. code-block:: python

  vol.update(display_name='New Volume Name')

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  curl -X PUT $ENDPOINT/volumes/{volumeId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "snapshot": {
        "display_name": "New Volume Name"
      }
    }' | python -m json.tool
