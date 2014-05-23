.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $policy->update(array(
        'name'     => 'New name',
        'cooldown' => 120
    ));

.. code-block:: python

    # After authenticating
    au = pyrax.autoscale
    # The `scalingGroupId` and `policyId` parameters are required. You may
    # include one or more of the remaining paramters.
    au.update_policy("{scalingGroupId}", "{policyId}", name="My Policy",
            policy_type="webhook", cooldown=120, change=10, is_percent=True,
            desired_capacity=7)

.. code-block:: ruby

  my_policy.cooldown = 60
  my_policy.change = 3
  my_policy.save
