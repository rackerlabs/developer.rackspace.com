.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
      APIKey = "{apikey}",
      Username = "{username}"
  };
  CloudServersProvider cloudServersProvider = new CloudServersProvider(cloudIdentity);
			
.. code-block:: go

  ao := gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  }
  provider, err := rackspace.AuthenticatedClient(ao)

  client, err := rackspace.NewComputeV2(provider, gophercloud.EndpointOpts{
    Region: "{region}",
  })

.. code-block:: java

  // Authentication in jclouds is lazy and happens on the first call to the cloud.
  NovaApi novaApi = ContextBuilder.newBuilder("rackspace-cloudservers-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(NovaApi.class);

.. code-block:: javascript

  pkgcloud = require('pkgcloud');

  // Each client is bound to a specific service and provider.
  var client = pkgcloud.compute.createClient({
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

  $service = $client->computeService(null, '{region}');

.. code-block:: python

  import pyrax

  pyrax.set_setting("identity_type", "rackspace")
  pyrax.set_default_region('{region}')
  pyrax.set_credentials('{username}', '{apiKey}')

  cs = pyrax.cloudservers

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

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X POST \
     -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
     -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set three environment variables: TENANT, TOKEN and ENDPOINT.

  export TENANT="{tenantId}"
  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For the compute service
