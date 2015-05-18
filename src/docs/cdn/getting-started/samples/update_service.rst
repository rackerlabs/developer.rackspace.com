.. code-block:: csharp

  // Get the existing service, and use it's values
  // to set the updated version, except for the values
  // you wish to change; in this example, the name.

  CancellationToken cn = new CancellationToken();
  var task = contentDeliverClient.GetServiceAsync({serviceId}, cn);
  task.Wait();
  var result = task.Result;
  ServiceData before = result;
  ServiceData after =
    new ServiceData("{updated_name}", before.FlavorId, before.Domains, before.Origins,
    before.CachingRules, before.Restrictions);
  CancellationToken cn2 = new CancellationToken();
  await contentDeliveryClient.UpdateServiceAsync({serviceId}, after, cn2);

.. code-block:: go

  updateOpts := osServices.UpdateOpts{
    osServices.Append{
      Value: osServices.Origin{
        Origin: "44.33.22.11",
        Port: 80,
        SSL: false,
      },
    },
  }
  location, err := services.Update(client, serviceIdOrURL, updateOpts).Extract()

.. code-block:: java

  ServiceApi serviceApi = cdnApi.getServiceApi();
  Service serviceToUpdate = serviceApi.get("{serviceId}");
  UpdateService updated = serviceToUpdate.toUpdatableService().name("updated_name").build();
  serviceApi.update("{serviceId}", serviceToUpdate, updated);

.. code-block:: javascript

  service.origins = [
    {
      origin: '44.33.22.11',
      port: 80,
      ssl: false
    }
  ];

  client.updateService(service, function(err, service) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your updated service here
  });

.. code-block:: php

    $service->update(array(
        'origins' => array(
            array(
                'origin' => '44.33.22.11',
                'port'   => 80,
                'ssl'    => false
            )
        )
    ));

.. code-block:: python

  service.patch([{"op":"replace", "path":"/name", "value":"newServiceName"}])

.. code-block:: ruby

  @service.name = "newServiceName"
  @service.save

.. code-block:: sh

  $ curl -X PATCH -d \
    '[
        {
            "op": "replace",
            "path": "/name",
            "value": "newServiceName"
        },
        {
            "op": "add",
            "path": "/domains/-",
            "value": {
                "domain": "newDomain.com",
                "protocol": "http"
            }
        },
        {
            "op": "remove",
            "path": "/origins/0"
        }
    ]' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/services/{serviceId} | python -m json.tool
