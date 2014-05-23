.. code-block:: csharp

.. code-block:: java

    MessageApi messageApi = marconiApi.getMessageApiForZoneAndClientAndQueue("{region}", "{clientId}", "sample_queue");
    CreateMessage createMessage = CreateMessage.builder().ttl(900).body("{\"play\": \"hockey\"}").build();
    List<CreateMessage> createMessages = ImmutableList.of(createMessage);

    MessagesCreated messagesCreated = messageApi.create(createMessages);

.. code-block:: javascript

.. code-block:: php

  // Post a message to the queue with a life of 900 seconds.
  $queue->createMessage(array(
      'body' => 'Message body',
      'ttl'  => 900
  ));

.. code-block:: python

  queue = pyrax.queues.get("sample_queue")

  # The 'body' parameter can be a simple value, or a chunk of XML, or pretty
  #   much anything you need.
  # The 'ttl' parameter sets the life of the message (in seconds).

  queue.post_message("Message body", ttl=900)

.. code-block:: ruby

  # The 'body' parameter can be a String, a Hash, or an Array.
  # The 'ttl' parameter sets the life of the message (in seconds).

  queue.enqueue("Message body", 900)

.. code-block:: sh

  curl -X POST POST $ENDPOINT/queues/{queueName}/messages -d \
    '[{"ttl": 300,"body": {"event": "BackupStarted"}},{"ttl": 60,"body": {"play": "hockey"}}]' \
    -H "Content-type: application/json" \
    -H "Client-ID: {clientId}" \
    -H "X-Auth-TOKEN: $TOKEN" \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
