.. code-block:: csharp

  // This is not supported through the .NET SDK at this time

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  ServerApi serverApi = novaApi.getServerApiForZone("{region}");

  CreateServerOptions options = CreateServerOptions.Builder.keyPairName("my-keypair");
  ServerCreated serverCreated = serverApi.create("My server", "{imageId}", "{flavorId}", options);

.. code-block:: javascript

  client.createServer({
    name: 'My server',
    image: imageId,
    flavor: flavorId,
    keyname: 'my-keypair'
  }, function(err, server) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your server here
  });

.. code-block:: php

  $server = $service->server();

  $response = $server->create(array(
      'name'     => 'My server',
      'imageId'  => $imageId,
      'flavorId' => $flavorId
      'keypair'  => 'my-keypair'
  ));

.. code-block:: python

  server = cs.servers.create('My server', image.id, flavor.id,
                             key_name=keypair.name)

.. code-block:: ruby

  server = @client.servers.create(
    :name => 'My server',
    :image_id => image.id,
    :flavor_id => flavor.id,
    :key_name => 'my-keypair'
  )

.. code-block:: sh

  curl -X POST $ENDPOINT -d \
    '{
      "server" : {
        "name" : "My server",
        "imageRef" : ${IMAGE_ID},
        "flavorRef" : ${FLAVOR_ID},
        "key_name" : "my-keypair"
      }
    }' -H "X-Auth-Token: $TOKEN" | python -m json.tool
