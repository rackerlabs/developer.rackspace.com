.. code-block:: csharp

  IEnumerable<Flavor> flavorList = cloudServersProvider.ListFlavors();

.. code-block:: go

	flavors.List(serviceClient).EachPage(func (page pagination.Page) (bool, error) {
  	flavors, _ := ExtractFlavors(page)
  	// Use the page of []flavors.Flavors
  	return true, nil
	}

.. code-block:: java

  FlavorApi flavorApi = novaApi.getFlavorApiForZone("{region}");

  ImmutableList<? extends Flavor> flavors = flavorApi.listInDetail().concat().toList();

.. code-block:: javascript

  client.getFlavors(function(err, flavors) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO figure out which flavors to use
    // just grab the first flavor id
    var flavorId = flavors[0].id;
  });

.. code-block:: php

  $flavors = $service->flavorList();

.. code-block:: python

  flavor_list = cs.list_flavors()

.. code-block:: ruby

  @client.flavors.all

.. code-block:: sh

  curl -X GET $ENDPOINT/flavors \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool

  # Choose a flavor from the list and note its id.
  export FLAVOR_ID="{flavorId}"
