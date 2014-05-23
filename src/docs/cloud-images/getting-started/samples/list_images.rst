.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $service = $client->imageService('cloudImages', '{regionId}');
    $images  = $service->listImages();

    foreach ($images as $image) {
       /** @param $image OpenCloud\Image\Resource\Image */
    }

.. code-block:: python

  # After authenticating, get a reference to the client
  imgs = pyrax.images
  all_images = imgs.list()

.. code-block:: ruby

  # N/A

.. code-block:: sh

  curl -s $ENDPOINT/images -H "X-Auth-Token: $token" | python -m json.tool
