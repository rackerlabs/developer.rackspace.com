.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
      APIKey = "{apikey}",
      Username = "{username}"
  };
  CloudMonitoringProvider cloudMonitoringProvider = new CloudMonitoringProvider(
  cloudIdentity,
  "{region}",
  null);

.. code-block:: go

  import (
    "github.com/rackspace/gophercloud"
    "github.com/rackspace/gophercloud/rackspace"
    osNetworks "github.com/rackspace/gophercloud/openstack/networking/v2/networks"
    "github.com/rackspace/gophercloud/rackspace/networking/v2/networks"
    osSubnets "github.com/rackspace/gophercloud/openstack/networking/v2/subnets"
    "github.com/rackspace/gophercloud/rackspace/networking/v2/subnets"
    osPorts "github.com/rackspace/gophercloud/openstack/networking/v2/ports"
    "github.com/rackspace/gophercloud/rackspace/networking/v2/ports"
    osRules "github.com/rackspace/gophercloud/openstack/networking/v2/security/rules"
    "github.com/rackspace/gophercloud/rackspace/networking/v2/security/rules"
    osGroups "github.com/rackspace/gophercloud/openstack/networking/v2/security/groups"
    "github.com/rackspace/gophercloud/rackspace/networking/v2/security/groups"
  )

  ao := gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  }

  provider, err := rackspace.AuthenticatedClient(ao)

  client, err := rackspace.NewNetworkV2(provider, gophercloud.EndpointOpts{
    Region: "{region}",
  })

.. code-block:: java

  // Authentication in jclouds is lazy and happens on the first call to the cloud.
  NeutronApi neutronApi = ContextBuilder.newBuilder("rackspace-cloudnetworks-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(NeutronApi.class);

.. code-block:: javascript

  pkgcloud = require('pkgcloud');

  // Each client is bound to a specific service and provider.
  var client = pkgcloud.network.createClient({
    provider: 'rackspace',
    username: '{username}',
    apiKey: '{apiKey}',
    region: '{region}'
  });

.. code-block:: php

  require 'vendor/autoload.php';

  use OpenCloud\Rackspace;

  $client = new Rackspace(Rackspace::US_IDENTITY_ENDPOINT, array(
    'username' => '{username}',
    'apiKey'   => '{apiKey}'
  ));

.. code-block:: python

  import pyrax

  pyrax.set_setting("identity_type", "rackspace")
  pyrax.set_default_region('{region}')
  pyrax.set_credentials('{username}', '{apiKey}')
  cm = pyrax.cloud_monitoring

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::NetworkingV2.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apikey}',
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
  export ENDPOINT="{publicUrl}" # For Monitoring service
