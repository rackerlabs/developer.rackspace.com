.. code-block:: csharp

.. code-block:: java

  MessageApi messageApi = marconiApi.getMessageApiForZoneAndClientAndQueue("{region}", "{clientId}", "sample_queue");
  List<String> messageIds = ImmutableList.of("{messageId}");

  messageApi.delete(messageIds);

.. code-block:: javascript

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
