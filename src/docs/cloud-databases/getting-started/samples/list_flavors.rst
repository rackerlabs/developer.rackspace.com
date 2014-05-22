.. code-block:: csharp

.. code-block:: java

  // List your flavors and get the first.
  FlavorApi flavorApi = troveApi.getFlavorApiForZone("{region}");
  Flavor flavor = Iterables.getFirst(flavorApi.list(), null);

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

  flavors = cdb.list_flavors()

.. code-block:: ruby

  flavors = @client.flavors.all

.. code-block:: sh
