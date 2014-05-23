.. code-block:: csharp

.. code-block:: java

  QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient("{region}", "{clientId}");
  List<Queue> queues = queueApi.list(true).concat().toList();

.. code-block:: javascript

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
