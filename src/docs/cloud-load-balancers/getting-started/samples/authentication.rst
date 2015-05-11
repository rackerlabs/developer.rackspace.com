Authenticate to gain access to the service
==========================================
To use this service you have to authenticate first. To do this, you will need your Rackspace username, and one of the following:

* your Rackspace account password
* your Rackspace API key

Your username and password are the ones you use to login to the Cloud Control Panel at http://mycloud.rackspace.com/.

To find your API key, first navigate to the Cloud Control Panel, then click on your username at the top right corner, and then finally click on Account Settings. You will be taken to a page that shows your settings. Under Login Details, you can show or reset your API key.

Once you have these pieces of information, you can pass them into the SDK:

.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
      APIKey = "{apikey}",
      Username = "{username}"
  };
  CloudLoadBalancerProvider cloudLoadBalancerProvider =
	new CloudLoadBalancerProvider(
	  cloudIdentity,
	  "{region}",
	  null);

.. code-block:: go

  import (
    "github.com/rackspace/gophercloud"
    "github.com/rackspace/gophercloud/rackspace"
    "github.com/rackspace/gophercloud/rackspace/compute/v1/servers"
    "github.com/rackspace/gophercloud/rackspace/lb/v1/acl"
    "github.com/rackspace/gophercloud/rackspace/lb/v1/lbs"
    "github.com/rackspace/gophercloud/rackspace/lb/v1/monitors"
    "github.com/rackspace/gophercloud/rackspace/lb/v1/nodes"
    "github.com/rackspace/gophercloud/rackspace/lb/v1/throttle"
    "github.com/rackspace/gophercloud/rackspace/lb/v1/vips"
  )

  provider, err := rackspace.AuthenticatedClient(gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  })

  client, err := rackspace.NewLBV1(provider, gophercloud.EndpointOpts{
    Region: "{region}",
  })

.. code-block:: java

  // Authentication in jclouds is lazy and happens on the first call to the cloud.
  CloudLoadBalancersApi clbApi = ContextBuilder.newBuilder("rackspace-cloudloadbalancers-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(CloudLoadBalancersApi.class);

.. code-block:: javascript

  var pkgcloud = require('pkgcloud');

  var rackspace = pkgcloud.loadbalancer.createClient({
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

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::LoadBalancers.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders, do not enclose '{}' when you replace them with actual credentials.

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X POST \
     -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
     -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set environment variables with values you'll need later.

  export TENANT="{tenantId}"
  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For the cloud load balancers service
  export COMPUTE_ENDPOINT="{publicComputeUrl}" # For the compute service, used to find servers.
