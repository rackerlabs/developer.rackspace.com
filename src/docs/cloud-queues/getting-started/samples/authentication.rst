.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
      APIKey = "{apikey}",
      Username = "{username}"
  };
  // Create the provider as well; it'll be used in every method
  CloudQueuesProvider cloudQueuesProvider = 
	new CloudQueuesProvider(
		cloudIdentity, 
		"{region}", 
		Guid.NewGuid(), 
		false, 
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
  MarconiApi marconiApi = ContextBuilder.newBuilder("rackspace-cloudqueues-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(MarconiApi.class);

.. code-block:: javascript

  // Not currently supported by this SDK

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
  # for queues, we also need to generate a client ID
  import uuid
  my_client_id = str(uuid.uuid4())

  pyrax.set_setting("identity_type", "rackspace")
  pyrax.set_default_region('{region}')
  pyrax.set_credentials('{username}', '{apiKey}')


.. code-block:: ruby

  @client = Fog::Rackspace::Queues.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apikey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders, do not enclose '{}' when you replace them with actual credentials.

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
    -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
    -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set three environment variables: TENANT, TOKEN and ENDPOINT.

  export TENANT="{tenantId}"
  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For the cloud queues service
