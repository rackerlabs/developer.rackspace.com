.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  opts := osRules.CreateOpts{
    Direction: osRules.DirEgress,
    EtherType: osRules.Ether4,
    SecGroupID: secgroup.ID,
  }

  secrule, err := rules.Create(client, opts).Extract()

.. code-block:: java

  Rule rule = securityGroupApi.create(Rule.createBuilder(RuleDirection.INGRESS, securityGroup.getId())
      .ethertype(RuleEthertype.IPV4)
      .build());

.. code-block:: javascript

  client.createSecurityGroupRule({
    direction: 'ingress',
    ethertype: 'IPv4',
    securityGroupId: securityGroup.id
  }, function(err, securityGroupRule) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your security group rule here
  });

.. code-block:: php

  $securityGroupRule = $networkingService->createSecurityGroupRule(array(
      'direction'       => 'ingress',
      'ethertype'       => 'IPv4',
      'securityGroupId' => $securityGroup->getId()
  ));

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @rule = @client.security_group_rules.new({
    :direction         => "ingress",
    :ethertype         => "IPv4",
    :security_group_id => @security_group.id
  }).save

.. code-block:: sh

  // Not currently supported by this SDK

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
