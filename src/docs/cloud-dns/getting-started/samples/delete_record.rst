.. code-block:: csharp

  DomainId domainId = new DomainId("{domain_id}");
  await cloudDNSProvider.RemoveRecordsAsync(domainId, new[] { {records}[0].Id }, AsyncCompletionOption.RequestCompleted, CancellationToken.None, null);
			
.. code-block:: java

  RecordApi recordApi = cloudDNSApi.getRecordApiForDomain({domainId});

  awaitComplete(cloudDNSApi, recordApi.delete({recordIds}));

.. code-block:: javascript

  rackspace.deleteRecord(myZone, myRec, function (err) {
    if (err) {
      console.dir(err);
      return;
    }

    // The DNS record was successfully deleted.
  });

.. code-block:: php

  $record->delete();

.. code-block:: python

  record.delete()

.. code-block:: ruby

  record.destroy

.. code-block:: sh

  curl -X DELETE -d \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains/{domainId}/records/{recordId} | python -m json.tool
