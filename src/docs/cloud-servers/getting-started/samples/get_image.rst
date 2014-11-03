.. code-block:: csharp

  ServerImage serverImage = cloudServersProvider.GetImage("{image_id}");

.. code-block:: go

  // The Region is specified when creating the Service Client
  serviceClient := openstack.NewComputeV2(providerClient, gophercloud.EndpointOpts{
    Region: "{region}",
  })
  image, err := images.Get(serviceClient, "{imageId}").Extract()

.. code-block:: java

  ImageApi imageApi = novaApi.getImageApiForZone("{region}");

  Image image = imageApi.get("{imageId}");

.. code-block:: javascript

  client.getImage(imageId, function(err, image) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your image here
  });

.. code-block:: php

  $image = $service->image('{imageId}');

.. code-block:: python

  image = pyrax.images.get('{imageId}')

.. code-block:: ruby

  image = @client.images.get('{imageId}')

.. code-block:: sh

  curl -X GET $ENDPOINT/images/{imageId} \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
