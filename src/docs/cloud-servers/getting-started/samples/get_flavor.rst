.. code-block:: csharp

.. code-block:: java

  FlavorApi flavorApi = novaApi.getFlavorApiForZone("{region}");
  Flavor flavor = flavorApi.get("{flavorId}");

.. code-block:: javascript

  client.getFlavor(flavorId, function(err, flavor) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your flavor here
  });

.. code-block:: php

  $flavor = $service->flavor('{flavorId}');

.. code-block:: python

  # Get a list of flavors available in your account
  flavors = pyrax.cloudservers.list_flavors()
  flavor = [flavor for flavor in flavors
    if flavor.id == "{flavorId}"][0]

.. code-block:: ruby

  flavor = @client.flavor.get('{flavorId}')

.. code-block:: sh

  curl -X GET $ENDPOINT/flavors/{flavorId} \
        -H "X-Auth-Token: $TOKEN" | python -m json.tool
