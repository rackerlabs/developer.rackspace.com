.. code-block:: csharp

.. code-block:: java

  ImageApi imageApi = novaApi.getImageApiForZone("{region}");
  ImmutableList<? extends Image> images = imageApi.listInDetail().concat().toList();

.. code-block:: javascript

  client.getImages(function(err, images) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO decide on an image to use
  });

.. code-block:: php

  // To retrieve all images:
  $images = $service->imageList();

  // Choose the first image in the collection
  foreach ($images as $image) {
      $imageId = $image->id;
      break;
  }

.. code-block:: python

  # Get a list of the images available to your account
  image_list = pyrax.images.list()

.. code-block:: ruby

  @client.images.all

.. code-block:: sh

  curl -X GET $ENDPOINT/images \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool

  # Choose an image from the list and note its id.
  export IMAGE_ID="{imageId}"
