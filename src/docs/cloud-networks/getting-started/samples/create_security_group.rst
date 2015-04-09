.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  opts := osGroups.CreateOpts{
    Name: "MyNewSecurityGroup",
    Description: "MyRules",
  }

  secgroup, err := groups.Create(client, opts).Extract()

.. code-block:: java

  SecurityGroupApi securityGroupApi = neutronApi.getSecurityGroupApi("{region}").get();
  SecurityGroup securityGroup = securityGroupApi.create(SecurityGroup.createBuilder()
      .name("MyNewSecurityGroup")
      .description("MyRules")
      .tenantId("{tenantID}")
      .build());

.. code-block:: javascript

  client.createSecurityGroup({
    name: 'MyNewSecurityGroup',
    description: 'MyRules',
    tenantId: '{tenantID}'
  }, function(err, securityGroup) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your security group here
  });

.. code-block:: php

  $securityGroup = $networkingService->createSecurityGroup(array(
      'name' => 'MySecurityGroup',
      'description' => 'MyRules',
      'tenantId' => '{tenantID}'
  ));

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @security_group = @client.security_groups.new({
    :name        => "MySecurityGroup",
    :description => "MyRules",
    :tenant_id   => "{tenantID}"
  }).save

.. code-block:: sh

  curl -X "POST" "https://dfw.networks.api.rackspacecloud.com/v2.0/security-groups" \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-type: application/json" \
    -d $'{
    "security_group": {
      "name": "MyNewSecurityGroup",
      "description": "MyRules",
      "tenant_id": "$TENANT_ID"
    }
  }' \
  -H "Accept: application/json" | python -m json.tool
