.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  err := networks.List(client, osNetworks.ListOpts{}).EachPage(func(page pagination.Page) (bool, error) {
    networkList, err := osNetworks.ExtractNetworks(page)

    for _, n := range networkList {

    }

    return true, nil
  })

.. code-block:: java

  List<Network> networks = networkApi.list().concat().toList();

.. code-block:: javascript

  client.getNetworks(function(err, networks) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your networks here
  });

.. code-block:: php

  $networks = $networkingService->listNetworks();

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @client.networks

.. code-block:: sh

  curl -X "GET" "https://dfw.networks.api.rackspacecloud.com/v2.0/networks" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-type: application/json" \
    -H "Accept: application/json" | python -m json.tool
