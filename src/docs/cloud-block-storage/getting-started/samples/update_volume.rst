.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $volume->rename(array(
      'display_name' => 'new_volume_name',
      'display_description' => 'This is the new volume description'
  ));

.. code-block:: python

  vol.update(display_name='new_volume_name',
             display_description='This is the new volume description')

.. code-block:: ruby

  # N/A

.. code-block:: sh

 $ curl -X PUT -d \
   '{
    "snapshot":{
        "display_name":"{name}",
        "display_description":"{description}"
        }
    }'\
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/volumes/{volumeId} | python -m json.tool 
