.. code-block:: csharp

  IEnumerable<SimpleServerImage> imageList = cloudServersProvider.ListImages();

.. code-block:: go

  images.List(serviceClient).EachPage(func (page pagination.Page) (bool, error) {
    images, _ := ExtractImages(page)
    // Use the page of []images.Image
    return true, nil
  }

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

  $images = $service->imageList();

.. code-block:: python

  images = pyrax.images.list()

.. code-block:: ruby

  @client.images.all

.. code-block:: sh

  curl -X GET $ENDPOINT/images \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool

  # Choose an image from the list and note its id.
  export IMAGE_ID="{imageId}"
