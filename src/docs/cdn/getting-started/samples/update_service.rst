.. code-block:: csharp

  // Not currently supported by this SDK

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

  // Not currently supported by this SDK

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
