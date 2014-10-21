.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
     APIKey = "{apikey}",
     Username = "{username}"
  };
  CloudFilesProvider cloudFilesProvider = new CloudFilesProvider(cloudIdentity);

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
  CloudFilesApi cloudFilesApi = ContextBuilder.newBuilder("rackspace-cloudfiles-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(CloudFilesApi.class);

.. code-block:: javascript

  pkgcloud = require('pkgcloud');

  // each client is bound to a specific service and provider
  var client = pkgcloud.storage.createClient({
    provider: 'rackspace',
    username: '{username}',
    apiKey: '{apiKey}',
    region: '{region}'
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
  pyrax.set_default_region('{region}')
  pyrax.set_credentials('{username}', '{apiKey}')

.. code-block:: ruby

  require 'fog'

  @client = Fog::Storage.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders, do not enclose '{}' when you replace them with actual credentials.

  curl -s -X POST https://identity.api.rackspacecloud.com/v2.0/tokens \
    -H "Content-Type: application/json" \
    -d '{
      "auth": {
        "RAX-KSKEY:apiKeyCredentials": {
          "username": "{username}",
          "apiKey": "{apiKey}"
        }
      }
    }' | python -m json.tool

  # From the resulting json, set three environment variables: TOKEN, ENDPOINT, and CDN_ENDPOINT.

  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For the Cloud Files service
  export CDN_ENDPOINT="{cdnEndpoint}" # Also from the cloud files service
