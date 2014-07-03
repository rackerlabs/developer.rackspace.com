.. code-block:: csharp

  Task<Tuple<ReadOnlyCollectionPage<DnsDomain>,int?>> domainsList =  cloudDNSProvider.ListDomainsAsync("domain.com", null, null, CancellationToken.None);
  Tuple<ReadOnlyCollectionPage<DnsDomain>,int?> x = await domainsList;

.. code-block:: java

  DomainApi domainApi = cloudDNSApi.getDomainApi();
  Domain domain = domainApi.get({domainId});

.. code-block:: javascript

  rackspace.getZones({ name: 'example.com' }, function (err, zones) {
    if (err) {
      console.dir(err);
      return;
    }

    // TODO use the zones.
  });

.. code-block:: php

  $dnsService = $client->dnsService();

  // First define your domain's ID
  $domainId = '{domainId}';

  // Alternatively, if you do not know your domain ID:
  $domains = $dnsService->domainList(array(
      'name' => 'example.com'
  ));

  foreach ($domains as $domain) {
      $domainId = $domain->id;
      break;
  }

  // Now, to get the full domain object:
  $domain = $dnsService->domain($domainId);

.. code-block:: python

  domain = pyrax.cloud_dns.find(name="example.com")

.. code-block:: ruby

  zone = service.zones.all.find { |z| z.name == 'example.com' }

.. code-block:: sh

  curl -X GET -d \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains/{domainId} | python -m json.tool
