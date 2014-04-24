.. code-block:: php

    use Guzzle\Http\Exception\BadResponseException;

    $server = $compute->server();

    try {
        $response = $server->create(array(
            'name'     => 'My new server',
            'imageId'  => $imageId,
            'flavorId' => $flavorId
        ));
    } catch (BadResponseException $e) {
        // No! Something failed. Let's find out:
        printf("Request: %s\n\nResponse: %s", (string) $e->getRequest(), (string) $e->getResponse());
    }

.. code-block:: python

  import os
  import pyrax

  cs = pyrax.cloudservers
  server_name = "f20-webserver"
  image_name = [img for img in cs.images.list()
      if "Fedora 20" in img.name][0]
  flavor_1GB_perf = [flavor for flavor in cs.flavors.list()
      if flavor.name == "1 GB Performance"][0]

  # Creates the server with the selected image and flavor
  server = cs.servers.create(server_name, image_name.id, flavor_1GB_perf)

  # Shows you the launched server information so you can use SSH to connect
  pyrax.utils.wait_for_build(server, verbose=True)
  print("ID:", server.id)
  print("Admin Password:", server.adminPass)
  print("Networks:", server.networks)