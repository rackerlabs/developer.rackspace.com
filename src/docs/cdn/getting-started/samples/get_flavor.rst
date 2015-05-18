.. code-block:: csharp

  CancellationToken cn = new CancellationToken();
  Flavor flavor = await contentDeliverClient.GetFlavorAsync({flavorId}, cn);

.. code-block:: go

  flavor, err := flavors.Get(client, flavorId).Extract()

.. code-block:: java

  FlavorApi flavorApi = cdnApi.getFlavorApi();
  Flavor flavor = flavorApi.get("{flavorId}");

.. code-block:: javascript

  client.getFlavor({flavorId}, function(err, flavor) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your flavor here
  });

.. code-block:: php

  $flavor = $cdnService->getFlavor('{flavorId}');

.. code-block:: python

  flavor = cdn.get_flavor('{flavorId}')

.. code-block:: ruby

  @client.flavors.get({flavorId})

.. code-block:: sh

  $ curl -X GET $ENDPOINT/flavors/{flavorId} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
