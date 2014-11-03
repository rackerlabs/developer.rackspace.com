.. code-block:: csharp

   QueueName queueName = new QueueName("{queue_name}");
   TimeSpan ttl = TimeSpan.FromMinutes(900);
   Newtonsoft.Json.Linq.JObject message_body = 
	new Newtonsoft.Json.Linq.JObject("{\"play\": \"hockey\"}");
   Message message = new Message(ttl, message_body);
   Message[] messages = { message };
   await cloudQueuesProvider.PostMessagesAsync(queueName, CancellationToken.None, messages);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

    MessageApi messageApi =
        marconiApi.getMessageApiForZoneAndClientAndQueue("{region}", "{clientId}", "{queueName}");

    CreateMessage createMessage = CreateMessage.builder()
            .ttl(900)
            .body("{\"play\": \"hockey\"}")
            .build();
    List<CreateMessage> createMessages = ImmutableList.of(createMessage);

    MessagesCreated messagesCreated = messageApi.create(createMessages);

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Post a message to the queue with a life of 900 seconds.
  $queue->createMessage(array(
      'body' => 'Message body',
      'ttl'  => 900
  ));

.. code-block:: python

  #assign generated client ID (see authentication section)
  pyrax.queues.client_id = my_client_id

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

  curl -X POST $ENDPOINT/queues/{queueName}/messages -d \
    '[{"ttl": 300,"body": {"event": "BackupStarted"}},{"ttl": 60,"body": {"play": "hockey"}}]' \
    -H "Content-type: application/json" \
    -H "Client-ID: {clientId}" \
    -H "X-Auth-TOKEN: $TOKEN" \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
