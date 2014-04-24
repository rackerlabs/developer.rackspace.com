.. code-block:: php

    $flavorId = '{flavorId}';
    $flavor = $service->flavor($flavorId);

.. code-block:: java

// List your flavors and get the first.
flavorApi = troveApi.getFlavorApiForZone("IAD");
Flavor flavor = Iterables.getFirst(flavorApi.list(), null);