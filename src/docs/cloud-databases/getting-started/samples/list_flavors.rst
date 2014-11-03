.. code-block:: csharp

  ReadOnlyCollection<DatabaseFlavor> databaseFlavors = await cloudDatabaseProvider
    .ListFlavorsAsync(CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // List your flavors and get the first.
  FlavorApi flavorApi = troveApi.getFlavorApiForZone("{region}");

  FluentIterable<Flavor> flavors = flavorApi.list();

.. code-block:: javascript

  client.getFlavors(function(err, flavors) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your flavors array here
  });


.. code-block:: php

  $flavors = $service->flavorList();

.. code-block:: python

  flavors = cdb.list_flavors()

.. code-block:: ruby

  flavors = @client.flavors.all

.. code-block:: sh

  curl -s $ENDPOINT/flavors \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool

  # Choose the flavor you prefer and make a note of its "href" element.
  export FLAVOR_REF="{flavorRef}"

  # Remember the same "href" of another flavor for resizing, later.
  export NEW_FLAVOR_REF="{newFlavorRef}"
