.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  FlavorApi flavorApi = poppyApi.getFlavorApi();

  List<Flavor> flavors = flavorApi.list();

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  $ curl -X GET $ENDPOINT/flavors \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
