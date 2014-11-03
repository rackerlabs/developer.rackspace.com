.. code-block:: csharp

  Server server1 = cloudServersProvider.GetDetails("{serverId1}");
  Server server2 = cloudServersProvider.GetDetails("{serverId2}");

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  NovaApi novaApi = ContextBuilder.newBuilder("rackspace-cloudservers-us")
          .credentials("{username}, "{apiKey}")
          .buildApi(NovaApi.class);
  ServerApi serverApi = novaApi.getServerApiForZone("{region}");

  Server server1 = serverApi.get("{serverId}");
  Server server2 = serverApi.get("{serverId}");

.. code-block:: javascript

  // We're using async, a flow control library to make parallel calls easy
  var async = require('async');

  // We also need a compute client to retrieve details of two servers
  var compute = pkgcloud.compute.createClient({
    provider: 'rackspace',
    username: '{username}',
    apiKey: '{apiKey}',
    region: '{region}'
  });

  var serverIds = ['{serverId1}','{serverId2}'],
    servers = [];

  async.forEach(serverIds, function(serverId, next) {
    compute.getServer(serverId, function(err, server) {
      // If you had an error, callback with err
      if (err) {
        next(err);
        return;
      }

      // save our server into our array
      servers.push(server);
      next();
    });
  }, function(err) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO next we'll add the nodes
  });

.. code-block:: php

  $computeService = $client->computeService('cloudServersOpenStack', 'DFW');

  // Retrieve servers using their UUIDs
  $serverOne = $computeService->server('{serverId1}');
  $serverTwo = $computeService->server('{serverId2}');

.. code-block:: python

  pyrax.connect_to_cloudservers("{region}")
  server_one = cs.servers.get('{serverId1}')
  server_two = cs.servers.get('{serverId2}')


.. code-block:: ruby

  compute = Fog::Compute.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

  @server_one = compute.servers.get('{serverId1}')
  @server_two = compute.servers.get('{serverId2}')

.. code-block:: sh

  # Retrieve servers using their UUIDs.
  curl -s -X GET $COMPUTE_ENDPOINT/servers/{serverId1} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool

  curl -s -X GET $COMPUTE_ENDPOINT/servers/{serverId1} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool

  # Make a note of server.addresses.private.addr for each server.
