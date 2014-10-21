.. code-block:: csharp

  QueueName queueName = new QueueName("{queue_name}");
  MessageId messageId = new MessageId("message_id");
  await cloudQueuesProvider.DeleteMessageAsync(queueName, messageId, null, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  MessageApi messageApi =
      marconiApi.getMessageApiForZoneAndClientAndQueue("{region}", "{clientId}", "{queueName}");

  List<String> messageIds = ImmutableList.of("{messageId}");

  messageApi.delete(messageIds);

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Delete claimed messages.
  foreach ($claimedMessages as $claimedMessage) {
     $claimId = $claimedMessage->getClaimIdFromHref();
     $claimedMessage->delete($claimId);
  }

.. code-block:: python

  queue = pyrax.queues.get("sample_queue")

  # If the message has not been claimed:
  queue.delete_message(message_id)

  # If the message has been claimed, you need to include the claim_id:
  queue.delete_message(message_id, claim_id="01234356789abcdef")

.. code-block:: ruby

  message.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/queues/{queueName}/messages/{messageId} \
    -H "Content-type: application/json" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Client-ID: {clientId}" \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
