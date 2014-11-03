.. code-block:: csharp

  DomainId domainId = new DomainId("{domainId}");
  DnsDomainRecordConfiguration[] recordConfigurations =
          {
              new DnsDomainRecordConfiguration(
                  type: DnsRecordType.A,
                  name: string.Format("www.{0}", {domainName}),
                  data: "{data}",
                  timeToLive: null,
                  comment: "{comment}",
                  priority: null)
          };
  DnsJob<DnsRecordsList> recordsResponse = 
	await cloudDNSProvider.AddRecordsAsync(
		domainId, 
		recordConfigurations, 
		AsyncCompletionOption.RequestCompleted, 
		CancellationToken.None, null);
  DnsRecord[] records = recordsResponse.Response.Records.ToArray();
  
.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  RecordApi recordApi = cloudDNSApi.getRecordApiForDomain({domainId});

  Record createARecord = Record.builder()
          .type("A")
          .name("app.domain.com")
          .data("192.168.1.1")
          .ttl(3600)
          .build();

  List<Record> createRecords = ImmutableList.of(createARecord);

  Set<RecordDetail> records = awaitComplete(cloudDNSApi, recordApi.create(createRecords));

.. code-block:: javascript

  var recDetails = {
    name: 'app.example.com',
    data: '192.168.1.1',
    type: 'A'
  };

  rackspace.createRecord(myZone, recDetails, function (err, rec) {
    if (err) {
      console.dir(err);
      return;
    }
    console.log('Record ' + rec.name + ' was successfully created.');
  });

.. code-block:: php

  // Add an A record
  $record = $domain->record(array(
      'type' => 'A',
      'name' => 'app.example.com',
      'ttl'  => 3600,
      'data' => '192.168.1.1'
  ));

  $record->create();

.. code-block:: python

  domain.add_record({'type': 'A',
                     'name': 'app.example.com',
                     'ttl': 3600,
                     'data': '192.168.1.1'})

.. code-block:: ruby

  record = zone.records.create(
    :type => 'A',
    :name => 'app.example.com',
    :ttl => 3600,
    :value => '192.168.1.1'
  )

.. code-block:: sh

  curl -s -X POST $ENDPOINT/domains/{domainId}/records \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
        "records": [
            {
                "name" : "app.example.com",
                "type" : "A",
                "data" : "192.168.1.1",
                "ttl" : 3600
            }
        ]
    }' | python -m json.tool
