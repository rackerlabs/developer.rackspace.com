.. code-block:: csharp

  DomainId domainId = new DomainId("{domain_id}");
  DnsDomainRecordUpdateConfiguration recordUpdateConfiguration = new DnsDomainRecordUpdateConfiguration({records}[0], {records}[0].Name, comment: "{comment}");
  await cloudDNSProvider.UpdateRecordsAsync(domainId, new[] { recordUpdateConfiguration }, AsyncCompletionOption.RequestCompleted, CancellationToken.None, null);
  DnsRecord updatedRecord = await cloudDNSProvider.ListRecordDetailsAsync(domainId, {records}[0].Id, CancellationToken.None);

.. code-block:: java

.. code-block:: javascript

  myRec.data = '192.168.1.2';
  rackspace.updateRecord(myZone, myRec, function (err){
    if (err) {
      console.dir(err);
      return;
    }

    // The record was successfully modified.
  });

.. code-block:: php

  // Let's decrease its TTL by an hour:
  $record->ttl -= 60;

  // And change its data value:
  $record->data = '192.168.1.2';

  $record->update();

.. code-block:: python

  record.update(data="192.168.1.2")

.. code-block:: ruby

  # Let's decrease its TTL by an hour:
  record.ttl -= 60

  # And change its data value:
  record.value = '192.168.1.2'

  record.save

.. code-block:: sh

  curl -X PUT -d \
    '{
        "type" : "A",
        "data" : "192.168.1.2",
        "ttl" : 3600
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains/{domainId}/records/{recordId} | python -m json.tool
