.. code-block:: csharp

.. code-block:: java

  ClaimApi claimApi = marconiApi.getClaimApiForZoneAndClientAndQueue("{region}", "{clientId}", "sample_queue");
  List<Message> messages = claimApi.claim(900, 120, 4);

.. code-block:: javascript

.. code-block:: php

  // Claim the 4 oldest messages in the queue. Keep the claim for 900 seconds (i.e. the claim TTL). Extend the life
  // of these messages to 120 seconds past the claim TTL.
  $claimedMessages = $queue->claimMessages(array(
      'limit' => 4,
      'ttl'   => 900,
      'grace' => 120
  ));

.. code-block:: python

  # Claims require the following parameters:
  #   ttl: Defines how long the claim lasts (in seconds) before it is released.
  #   grace: Defines how long (in seconds) to extend the life of a message to
  #          match the life of the claim.
  #   count: Optional. The number of messages to claim (up to 20 max).
  #          Default = 10.
  queue = pyrax.queues.get("sample_queue")
  claim = queue.claim_messages(ttl=900, grace=120, count=4)

.. code-block:: ruby

  # Claims require the following parameters:
  #   limit: The number of messages to claim.
  #   ttl:   Defines how long the claim lasts (in seconds) before it is released.
  #   grace: Defines how long (in seconds) to extend the life of a message to
  #          match the life of the claim.
  claim = queue.claims.create(
    :limit => 1,
    :ttl => 900,
    :grace => 600
  )

.. code-block:: sh

  curl -X POST $ENDPOINT/queues/{queueName}/claims -d \
    '{"ttl": 300,"grace":300}' \
    -H "Content-type: application/json" \
    -H "Client-ID: {clientId}" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
