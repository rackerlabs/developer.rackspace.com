.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  err := flavors.List(client).EachPage(func(page pagination.Page) (bool, error) {
    flavorList, err := osFlavors.ExtractFlavors(page)
    for _, flavor := range flavorList {
            // ...
    }
    return true, nil
  })

.. code-block:: java

  FlavorApi flavorApi = poppyApi.getFlavorApi();

  List<Flavor> flavors = flavorApi.list();

.. code-block:: javascript

  client.getFlavors(function(err, flavors) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your flavors array here
  });

.. code-block:: php

  // Obtain a CDN service object from the client.
  $cdnService = $client->cdnService();

  // List flavors
  $flavors = $cdnService->listFlavors();

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @client.flavors

.. code-block:: sh

  $ curl -X GET $ENDPOINT/flavors \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
