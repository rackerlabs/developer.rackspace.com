.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
      APIKey = "{apiKey}",
      Username = "{username}"
  };
  CloudAutoScaleProvider cloudAutoScaleProvider = 
	new CloudAutoScaleProvider(cloudIdentity, "{region}", null);

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
  AutoscaleApi autoscaleApi = ContextBuilder.newBuilder("rackspace-autoscale-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(AutoscaleApi.class);

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

  au = pyrax.autoscale

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::AutoScale.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
    -H "Content-Type: application/json" \
    -d '{
      "auth": {
        "RAX-KSKEY:apiKeyCredentials": {
          "username": "{username}",
          "apiKey": "{apiKey}"
        }
      }
    }' | python -m json.tool

  # From the resulting json, set two environment variables: TOKEN and ENDPOINT.

  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For Auto Scaling service(s)
