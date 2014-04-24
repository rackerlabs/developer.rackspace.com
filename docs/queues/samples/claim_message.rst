.. code-block:: python

  # Claims require the following parameters:
  #   ttl: Defines how long the claim lasts (in seconds) before it is released.
  #   grace: Defines how long (in seconds) to extend the life of a message to
  #          match the life of the claim.
  #   count: Optional. The number of messages to claim (up to 20 max).
             Default = 10.
  queue = pyrax.queues.get("sample_queue")
  claim = queue.claim_messages(ttl=900, grace=120, count=4)
