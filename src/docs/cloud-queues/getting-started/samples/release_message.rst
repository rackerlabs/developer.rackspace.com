.. code-block:: csharp

.. code-block:: java

  ClaimApi claimApi = marconiApi.getClaimApiForZoneAndClientAndQueue("{region}", "{clientId}", "sample_queue");
  claimApi.release("{claimId}");

.. code-block:: javascript

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
