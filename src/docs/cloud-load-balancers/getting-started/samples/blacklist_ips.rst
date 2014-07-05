.. code-block:: csharp

  LoadBalancerId loadBalancerId = new LoadBalancerId("{load_balancer_id}");
  NetworkItem networkItem = new NetworkItem("206.160.165.0/24", AccessType.Deny);
  await cloudLoadBalancerProvider.CreateAccessListAsync(loadBalancerId, networkItem, AsyncCompletionOption.RequestCompleted, CancellationToken.None, null);

.. code-block:: java

  AccessRule rule1 = AccessRule.deny("206.160.165.0/24");
  AccessRule rule2 = AccessRule.allow("206.160.165.0/2");
  AccessRule blacklisted = AccessRule.deny("0.0.0.0/0");

  List<AccessRule> accessList = ImmutableList.<AccessRule> of(rule1, rule2, blacklisted);

  AccessRuleApi accessRuleApi = clbApi.getAccessRuleApiForZoneAndLoadBalancer("{region}", loadBalancer.getId());
  accessRuleApi.create(accessList);

.. code-block:: javascript

  lb.addAccessList([
    { type: 'DENY', address: '206.160.165.0/24'},
    { type: 'ALLOW', address: '206.160.166.0/24'},
    { type: 'DENY', address: '0.0.0.0/0'},
  ], function (err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

    // Example 1: Blacklist a specific IP
    $loadBalancer->createAccessList(array(
        (object) array(
            'type'    => 'DENY',
            'address' => '206.160.165.0/24'
        )
    ));

    // Example 2: Only allow access to 1 IP, and blacklist everything else
    $loadBalancer->createAccessList(array(
        (object) array(
            'type'    => 'ALLOW',
            'address' => '206.160.165.1/24'
        ),
        (object) array(
            'type'    => 'DENY',
            'address' => '0.0.0.0/0'
        )
    ));

.. code-block:: python

  load_balancer.add_access_list([
      {"type": 'DENY', "address": "206.160.165.0/24"},
      {"type": 'ALLOW', "address": "206.160.166.0/24"},
      {"type": 'DENY', "address": "0.0.0.0/0"}
  ])

.. code-block:: ruby

  # Example 1: Blacklist a specific IP

  @balancer.access_rules.create(
    :type => 'DENY',
    :address => '206.160.165.0/24'
  )
  @balancer.wait_for { ready? }

  # Example 2: Allow access to 1 IP, and blacklist everything else
  @balancer.access_rules.create(
    :type => 'ALLOW',
    :address => '206.160.166.0/24'
  )
  @balancer.wait_for { ready? }

  @balancer.access_rules.create(
    :type => 'DENY',
    :address => '0.0.0.0/0'
  )
  @balancer.wait_for { ready? }

.. code-block:: sh

  # Example 1: Blacklist a specific IP

  curl -X POST $ENDPOINT/{loadBalancerId}/accesslist \
    -H "X-Auth-Token: $TOKEN" \
    -H 'Content-Type: application/json' \
    -d \
      '{
          "accessList": [
              {
                  "address" : "206.160.165.0/24",
                  "type": "DENY"
              }
          ]
      }'

  # Example 2: Allow access to 1 IP, and blacklist everything else
  curl -X POST $ENDPOINT/loadbalancers/{loadBalancerId}/accesslist \
    -H "X-Auth-Token: $TOKEN" \
    -H 'Content-Type: application/json' \
    -d \
      '{
          "accessList": [
              {
                  "address" : "206.160.166.0/24",
                  "type": "ALLOW"
              },
              {
                  "address" : "0.0.0.0/0",
                  "type": "DENY"
              }
          ]
      }'
