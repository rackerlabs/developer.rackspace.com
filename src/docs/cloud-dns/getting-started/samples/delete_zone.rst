.. code-block:: csharp

   DomainId[] domainIds = new DomainId[1];
   DnsJob deleteResponse = await cloudDNSProvider.RemoveDomainsAsync(domainIds, false, AsyncCompletionOption.RequestCompleted, CancellationToken.None, null);

.. code-block:: java

  DomainApi domainApi = cloudDNSApi.getDomainApi();

  awaitComplete(cloudDNSApi, domainApi.delete({domainIds}, true));

.. code-block:: javascript

  rackspace.deleteZone(myZone, function (err) {
    if (err) {
      console.dir(err);
      return;
    }
    console.log('Zone successfully deleted.');
  });

.. code-block:: php

	$domain->delete();

.. code-block:: python

  domain.delete()

.. code-block:: ruby

  zone.destroy

.. code-block:: sh

  $ curl -X DELETE -d \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains/{domainId} | python -m json.tool