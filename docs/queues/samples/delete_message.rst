.. code-block:: python

  queue = pyrax.queues.get("sample_queue")
  # If the message has not been claimed:
  queue.delete_message(message_id)
  # If the message has been claimed, you need to include the claim_id:
  queue.delete_message(message_id, claim_id="01234356789abcdef")
