.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.getSubnets(function(err, subnets) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your subnets here
  });

.. code-block:: php

  $subnets = $networkingService->listSubnets();

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @client.subnets

.. code-block:: sh

  // Not currently supported by this SDK

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
