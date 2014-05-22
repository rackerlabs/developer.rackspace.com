.. code-block:: csharp

.. code-block:: java

  CreateServerOptions options = CreateServerOptions.Builder.keyPairName("my-keypair");
  ServerApi serverApi = novaApi.getServerApiForZone("{region}");
  ServerCreated serverCreated = serverApi.create("My new server", "{imageId}", "{flavorId}", options);

.. code-block:: javascript

  client.createServer({
    name: 'My new server',
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

  $server = $compute->server();

  $response = $server->create(array(
      'name'     => 'My new server',
      'imageId'  => $imageId,
      'flavorId' => $flavorId
      'keypair'  => 'my-keypair'
  ));

.. code-block:: python

.. code-block:: ruby

  server = @client.servers.create(
    :name => 'My new server',
    :image_id => image.id,
    :flavor_id => flavor.id,
    :key_name => 'my-keypair'
  )

.. code-block:: sh

  curl -X POST $ENDPOINT -d \
    '{
      "server" : {
        "name" : "My new server",
        "imageRef" : ${IMAGE_ID},
        "flavorRef" : ${FLAVOR_ID},
        "key_name" : "my-keypair"
      }
    }' -H "X-Auth-Token: $TOKEN" | python -m json.tool
