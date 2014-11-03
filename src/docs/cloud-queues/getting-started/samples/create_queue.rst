.. code-block:: csharp

  QueueName queueName = new QueueName("{queue_name}");
  bool created = await cloudQueuesProvider.CreateQueueAsync(queueName, CancellationToken.None);
  await cloudQueuesProvider.DeleteQueueAsync(queueName, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient("{region}", "{clientId}");

  queueApi.create("{queueName}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Obtain an Object Store service object from the client.
  $queuesService = $client->queuesService(null, '{regionId}');

  // You must set a unique client ID for every script that accesses the API
  // This enforces responsibility when consuming and processing messages. If
  // you do not want to set your own UUID, leave the arg empty and the SDK will
  // create a default one:
  $queuesService->setClientId();

  $queue = $queuesService->createQueue('sample_queue');

.. code-block:: python

  queue = pyrax.queues.create("sample_queue")

.. code-block:: ruby

  queue = @client.queues.create(:name => 'sample_queue')

.. code-block:: sh

  curl -X PUT $ENDPOINT/queues/{queueName} \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
