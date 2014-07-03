.. code-block:: csharp

  DnsUpdateConfiguration dnsUpdateConfiguration = new DnsUpdateConfiguration(new DnsDomainUpdateConfiguration({domain}, comment: "domain updated"));
  await cloudDNSProvider.UpdateDomainsAsync(dnsUpdateConfiguration, AsyncCompletionOption.RequestCompleted, CancellationToken.None,null);

.. code-block:: java

  DomainApi domainApi = cloudDNSApi.getDomainApi();
  UpdateDomain updateDomain = UpdateDomain.builder()
          .email("changed@domain.com")
          .ttl(3600)
          .build();

  awaitComplete(cloudDNSApi, domainApi.update({domainId}, updateDomain));

.. code-block:: javascript

  myZone.emailAddress = 'changed@example.com';
  rackspace.updateZone(myZone, function (err) {
    if (err) {
      console.dir(err);
      return;
    }

    // TODO The zone was successfully modified.
  });

.. code-block:: php

  // Specify your changes
  $changes = array(
      'email' => 'changed@example.com',
      'ttl'   => 3600
  );

  $domain->update($changes);

.. code-block:: python

  domain.update(emailAddress="changed@example.com")

.. code-block:: ruby

  zone.email = 'changed@example.com'
  zone.ttl = 3600
  zone.save

.. code-block:: sh

.. code-block:: sh

  curl -X PUT -d \
    '{
        "comment" : "{updatedComment}",
        "ttl" : 300,
        "emailAddress" : "{updatedEMailAddress}"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains | python -m json.tool
