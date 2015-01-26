.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

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
