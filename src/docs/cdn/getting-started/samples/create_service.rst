.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  createOpts := osServices.CreateOpts{
    Name: "example_site",
    Domains: []osServices.Domain{
            osServices.Domain{
                    Domain: "www.example.com",
            },
    },
    Origins: []osServices.Origin{
            osServices.Origin{
                    Origin: "example.com",
            },
    },
    FlavorID: "{flavorId}",
  }
  location, err := services.Create(client, createOpts).Extract()

.. code-block:: java

  // TODO: Remove the .caching(...) and .restrictions(...) calls below
  // when this bug is fixed: https://issues.apache.org/jira/browse/JCLOUDS-877
  ServiceApi serviceApi = poppyApi.getServiceApi();
  URI serviceURI = serviceApi.create(
    CreateService.builder()
      .name("example_site")
      .domains(
        ImmutableList.of(
          Domain.builder().domain("www.example.com").build()))
      .origins(
        ImmutableList.of(
          Origin.builder().origin("example.com").build()))
      .caching(Collections.<Caching>emptyList())
      .restrictions(Collections.<Restriction>emptyList())
      .flavorId("{flavorId}")
      .build());

.. code-block:: javascript

  var service = {
    name: 'example_site',
    domains: [
      {
        domain: 'www.example.com'
      }
    ],
    origins: [
      {
        origin: 'example.com'
      }
    ],
    flavorId: '{flavorId}'
  };

  client.createService(service, function(err, service) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your newly created service here
  });

.. code-block:: php

    $service = $cdnService->createService(array(
        'name'     => 'example_site',
        'domains'  => array(
            array(
                'domain' => 'www.example.com'
            )
        ),
        'origins'  => array(
            array(
                'origin' => 'example.com'
            )
        ),
        'flavorId' => '{flavorId}'
    ));

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  @service = @client.services.new
  @service.name = "www.example.com"
  @service.flavor_id = "cdn"
  @service.add_domain "www.example.com"
  @service.add_origin "example.com"
  @service.save

.. code-block:: sh

  $ curl -X POST -d \
    '{
    "name": "mywebsite.com",
    "domains": [
        {
            "domain": "www.mywebsite.com"
        },
        {
            "domain": "blog.mywebsite.com"
        }
    ],
    "origins": [
        {
            "origin": "mywebsite.com",
            "port": 80,
            "ssl": false,
            "rules": []
        }
    },
    "flavor_id": "cdn"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/services | python -m json.tool
