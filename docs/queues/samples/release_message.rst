.. code-block:: python

  queue = pyrax.queues.get("sample_queue")
  queue.release_claim(claim_id)
