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

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

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

  @client = Fog::Rackspace::Monitoring.new(
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
