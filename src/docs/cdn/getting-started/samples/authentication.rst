.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  import (
    "github.com/rackspace/gophercloud"
    "github.com/rackspace/gophercloud/rackspace"
    "github.com/rackspace/gophercloud/rackspace/cdn/v1/base"
    "github.com/rackspace/gophercloud/rackspace/cdn/v1/services"
    "github.com/rackspace/gophercloud/rackspace/cdn/v1/serviceassets"
    "github.com/rackspace/gophercloud/rackspace/cdn/v1/flavors"

    osServices "github.com/rackspace/gophercloud/openstack/cdn/v1/services"
    osServiceAssets "github.com/rackspace/gophercloud/openstack/cdn/v1/serviceassets"
    osFlavors "github.com/rackspace/gophercloud/openstack/cdn/v1/flavors"
  )

  ao := gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  }
  provider, err := rackspace.AuthenticatedClient(ao)

  serviceClient, err := rackspace.NewCDNV1(provider, gophercloud.EndpointOpts{})

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  pkgcloud = require('pkgcloud');

  var rackspace = pkgcloud.cdn.createClient({
    provider: 'rackspace',
    username: '{username}',
    apiKey: '{apiKey}'
  });

.. code-block:: php

  require 'vendor/autoload.php';

  use OpenCloud\Rackspace;

  // Instantiate a Rackspace client.
  $client = new Rackspace(Rackspace::US_IDENTITY_ENDPOINT, array(
      'username' => '{username}',
      'apiKey'   => '{apiKey}'
  ));

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  require 'fog'

  @client = Fog::Compute.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders, do not enclose '{}' when you replace them with actual credentials.

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
     -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
     -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set three environment variables: tenant, TOKEN and endpoint

  export TENANT="{tenantId}"
  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For CDN service
