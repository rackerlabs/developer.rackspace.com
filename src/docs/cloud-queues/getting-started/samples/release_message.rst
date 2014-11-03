.. code-block:: csharp

  QueueName queueName = new QueueName("{queue_name}");
  TimeSpan ttl = TimeSpan.FromMinutes(900);
  TimeSpan grace = TimeSpan.FromMinutes(60);
  Claim claim = await cloudQueuesProvider.ClaimMessageAsync(
	queueName, 
	null, 
	ttl, 
	grace, 
	CancellationToken.None);
  await cloudQueuesProvider.ReleaseClaimAsync(queueName, claim, CancellationToken.None);

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  ClaimApi claimApi =
      marconiApi.getClaimApiForZoneAndClientAndQueue("{region}", "{clientId}", "{queueName}");

  claimApi.release("{claimId}");

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  foreach ($claimedMessages as $claimedMessage) {
     $claimId = $claimedMessage->getClaimIdFromHref();
     $claim   = $queue->getClaim($claimId);
     $claim->delete();
  }

.. code-block:: python

  queue = pyrax.queues.get("sample_queue")
  queue.release_claim(claim_id)

.. code-block:: ruby

  claim.destroy

.. code-block:: sh

  curl -X DELETE $ENDPOINT/queues/{queueName}/claims/{claimId} \
    -H "Content-type: application/json" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Client-ID: {clientId}"  \
    -H "Accept: application/json" \
    -H "X-Project-Id: {projectId}"
