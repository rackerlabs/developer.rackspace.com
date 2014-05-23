.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $webhook->update(array(
        'metadata' => array(
            'someKey' => 'someValue'
        )
    ));

.. code-block:: python

    # After authenticating
    au = pyrax.autoscale
    # The `scalingGroupId`, `policyId`, and `webhookId` parameters are
    # required. You may include one or more of the remaining parameters.
    au.update_webhook("{scalingGroupId}", "{policyId}", "{webhookId}",
            name="My Webhook", metadata={"someKey": "someValue"})

.. code-block:: ruby

  my_webhook.name = 'webhook1'
  my_webhook.metadata = {
      :owner => 'webteam'
  }
  my_webhook.save
