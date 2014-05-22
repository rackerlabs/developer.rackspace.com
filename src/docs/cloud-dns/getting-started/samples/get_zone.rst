.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

  rackspace.getZones({ name: 'domain.com' }, function (err, zones) {
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
      'name' => 'domain.com'
  ));

  foreach ($domains as $domain) {
      $domainId = $domain->id;
      break;
  }

  // Now, to get the full domain object:
  $domain = $dnsService->domain($domainId);

.. code-block:: python

  domain = pyrax.cloud_dns.find(name="domain.com")

.. code-block:: ruby

  zone = service.zones.all.find { |z| z.name == 'domain.com' }

.. code-block:: sh

  curl -X GET -d \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains/{domainId} | python -m json.tool
