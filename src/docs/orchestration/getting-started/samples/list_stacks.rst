.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  err = stacks.List(serviceClient, nil).EachPage(func(page pagination.Page) (bool, error) {
    stackList, err := osStacks.ExtractStacks(page)
    if err != nil {
      // handle error...
    }
    return true, nil
  })

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.getStacks(function (err, stacks) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your stacks here
  });

.. code-block:: php

  $stacks = $orchestrationService->listStacks();

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @client.stacks

.. code-block:: sh

  $ curl -X GET $ENDPOINT/stacks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
