.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  // Obtain an Object Store service object from the client.
  $region = 'DFW';
  $queuesService = $client->queuesService(null, $region);

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
