.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $policy = $group->getPolicy();
    $policy->create(array(
        (object) array(
            'name'     => 'My policy',
            'change'   => 1,
            'type'     => 'webhook',
            'cooldown' => 60
        )
    ));

.. code-block:: python

    # After authenticating
    au = pyrax.autoscale
    au.add_policy("{scalingGroupId}", "My Policy", cooldown=60, change=2,
            is_percent=False) 
    # Parameter explanations:
    #   scalingGroupId: ID of the scaling group to which you are
    #       adding the policy
    #   policy_name: Name for the new policy
    #   cooldown: period to wait between applications of scaling actions
    #   change: Scaling increment
    #   is_percent: Determines whether the value of the `change` parameter
    #       is a percentage or an absolute number

.. code-block:: ruby

  my_policy = my_group.policies.create :name => 'Scale by one server', 
    :cooldown => 360, 
    :type => 'webhook', 
    :change => 1
