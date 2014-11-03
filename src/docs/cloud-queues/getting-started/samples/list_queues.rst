.. code-block:: csharp

  ReadOnlyCollectionPage<CloudQueue> queueList = 
	await cloudQueuesProvider.ListQueuesAsync(null, null, true, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient("{region}", "{clientId}");

  List<Queue> queues = queueApi.list(true).concat().toList();

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $queues = $queuesService->listQueues();

.. code-block:: python

  pyrax.queues.list()

.. code-block:: ruby

  @client.queues.all

.. code-block:: sh

  curl -X GET $ENDPOINT/queues \
    -H "Content-type: application/json" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
