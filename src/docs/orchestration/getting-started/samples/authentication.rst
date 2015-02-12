.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  import (
    "github.com/rackspace/gophercloud"
    osStacks "github.com/rackspace/gophercloud/openstack/orchestration/v1/stacks"
    "github.com/rackspace/gophercloud/pagination"
    "github.com/rackspace/gophercloud/rackspace/orchestration/v1/stacks"
  )

  ao := gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  }
  provider, err := rackspace.AuthenticatedClient(ao)

  serviceClient, err := rackspace.NewOrchestrationV1(provider, gophercloud.EndpointOpts{
    Region: "{region}",
  })

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  pkgcloud = require('pkgcloud');

  // Each client is bound to a specific service and provider.
  var client = pkgcloud.orchestration.createClient({
    provider: 'rackspace',
    username: '{username}',
    apiKey: '{apiKey}',
    region: '{region}'
  });

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  require 'fog'

  @client = Fog::Compute.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders, do not enclose '{}' when you replace them with actual credentials.

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
     -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
     -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set three environment variables: tenant, TOKEN and endpoint

  export TENANT="{tenantId}"
  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For Orchestration service
