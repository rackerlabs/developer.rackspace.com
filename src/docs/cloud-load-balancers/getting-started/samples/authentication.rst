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

	// Identity v2
	/* AuthOptions fills out an identity.AuthOptions structure with the settings
	 found on the various OpenStack OS_* environment variables.
 	 The following variables provide sources of truth:
 	 OS_AUTH_URL
 	 OS_USERNAME
 	 OS_PASSWORD
 	 OS_TENANT_ID
 	 OS_TENANT_NAME

 	 Of these, OS_USERNAME, OS_PASSWORD, and OS_AUTH_URL must
 	 have settings, or an error will result.
 	 OS_TENANT_ID and OS_TENANT_NAME are optional.
	*/
	ao, err := utils.AuthOptions()
	providerClient, err := openstack.AuthenticatedClient(ao)

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
