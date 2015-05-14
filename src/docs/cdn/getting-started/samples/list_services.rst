.. code-block:: csharp

  CancellationToken cn = new CancellationToken();
  ReadOnlyCollectionPage<Service> x =
    await contentDeliveryClient.ListServicesAsync(cn);

.. code-block:: go

  err := services.List(client, nil).EachPage(func(page pagination.Page) (bool, error) {
    serviceList, err := osServices.ExtractServices(page)
    for _, service := range serviceList {
            // ...
    }
    return true, nil
  })

.. code-block:: java

  ServiceApi serviceApi = cdnApi.getServiceApi();
  List<Service> serviceList = serviceApi.list().concat().toList();

.. code-block:: javascript

  client.getServices(function(err, services) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your services array here
  });

.. code-block:: php

  $services = $cdnService->listServices();

.. code-block:: python

  services = cdn.list_services()

.. code-block:: ruby

  @client.services

.. code-block:: sh

  $ curl -X GET $ENDPOINT/services \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
