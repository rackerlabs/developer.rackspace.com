.. code-block:: csharp

  using OpenStack.Services.ContentDelivery.V1;
  using System.Collections.Immutable;

  ContentDeliveryClient cdc = GetContentDeliveryClient();

  ServiceProtocol serviceProtocol = ServiceProtocol.Http;
  ServiceDomain sd = new ServiceDomain("www.example.com", serviceProtocol);
  ImmutableArray<ServiceDomain>.Builder sdbuilder
    = ImmutableArray.CreateBuilder<ServiceDomain>();
  sdbuilder.Add(sd);
  ImmutableArray<ServiceDomain> domains = sdbuilder.ToImmutable();

  ServiceOriginRule sor =
    new ServiceOriginRule("example.com", "www.example.com");
  ImmutableArray<ServiceOriginRule>.Builder sorbuilder
    = ImmutableArray.CreateBuilder<ServiceOriginRule>();
  sorbuilder.Add(sor);
  ImmutableArray<ServiceOriginRule> rules = sorbuilder.ToImmutable();

  ServiceOrigin so = new ServiceOrigin("www.example.com", 80, false, rules);
  ImmutableArray<ServiceOrigin>.Builder sobuilder
    = ImmutableArray.CreateBuilder<ServiceOrigin>();
  sobuilder.Add(so);
  ImmutableArray<ServiceOrigin> origins = sobuilder.ToImmutable();

  ServiceCacheRule scr = new ServiceCacheRule("default", "www.example.com");
  ImmutableArray<ServiceCacheRule>.Builder scrbuilder
    = ImmutableArray.CreateBuilder<ServiceCacheRule>();
  scrbuilder.Add(scr);
  ImmutableArray<ServiceCacheRule> scrules = scrbuilder.ToImmutable();

  ImmutableArray<ServiceCache>.Builder scbuilder
    = ImmutableArray.CreateBuilder<ServiceCache>();
  ServiceCache sc =
    new ServiceCache("default", new TimeSpan(0, 0, 3600), scrules);
  scbuilder.Add(sc);
  ImmutableArray<ServiceCache> caching = scbuilder.ToImmutable();
  caching = new ImmutableArray<ServiceCache>();

  ImmutableArray<ServiceRestrictionRule>.Builder srrbuilder
    = ImmutableArray.CreateBuilder<ServiceRestrictionRule>();
  ServiceRestrictionRule srr
    = new ServiceRestrictionRule("example.com", "www.example.com");
  srrbuilder.Add(srr);
  ImmutableArray<ServiceRestrictionRule> srrules = srrbuilder.ToImmutable();
  srrules = new ImmutableArray<ServiceRestrictionRule>();

  ImmutableArray<ServiceRestriction>.Builder rbuilder
    = ImmutableArray.CreateBuilder<ServiceRestriction>();
  ServiceRestriction sr = new ServiceRestriction("website only", srrules);
  rbuilder.Add(sr);
  ImmutableArray<ServiceRestriction> restrictions =
    rbuilder.ToImmutable();
  restrictions = new ImmutableArray<ServiceRestriction>();

  ServiceData serviceData =
    new ServiceData(serviceName, flavorId, domains, origins, caching, restrictions);
  CancellationToken cn = new CancellationToken();

  ServiceId x = await cdc.AddServiceAsync(serviceData, cn);

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

  ServiceApi serviceApi = cdnApi.getServiceApi();
  URI serviceURI = serviceApi.create(
                 org.jclouds.openstack.poppy.v1.domain.CreateService.builder()
                       .name("jclouds_test_service")
                       .domains(
                             ImmutableList.of(
                                   Domain.builder().domain("www.example.com").build()))
                       .origins(ImmutableList.of(
                             Origin.builder()
                                   .origin("example.com")
                                   .build()))
                       .caching(ImmutableList.<Caching>of())
                       .restrictions(ImmutableList.<Restriction>of())
                       .flavorId("{flavorId}")
                       .build()
         );

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

  service = cdn.create_service("example_site", "{flavorId}",
                               [ { "domain": "www.example.com" } ],
                               [ { "origin": "example.com" } ])

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
