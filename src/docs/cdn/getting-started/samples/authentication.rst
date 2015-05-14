.. code-block:: csharp

  using OpenStack.Net;
  using OpenStack.Services.Identity.V2;
  using Rackspace.Security.Authentication;

  IIdentityService idservice =
    new IdentityClient(new Uri("https://identity.api.rackspacecloud.com"));
  PasswordCredentials pwdCred =
    new PasswordCredentials("{username}", "{password}");
  AuthenticationData authData = new AuthenticationData(pwdCred);
  AuthenticationRequest request = new AuthenticationRequest(authData);
  RackspaceAuthenticationService ras =
    new RackspaceAuthenticationService(idservice, request);

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

  // Authentication in jclouds is lazy and happens on the first call to the cloud.
  PoppyApi poppyApi = ContextBuilder.newBuilder("rackspace-cdn-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(PoppyApi.class);

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

  import pyrax

  pyrax.set_setting("identity_type", "rackspace")
  pyrax.set_credentials('{username}', '{apiKey}')

  cdn = pyrax.cloud_cdn

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
