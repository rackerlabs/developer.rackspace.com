.. code-block:: csharp

  CloudIdentity cloudIdentity = new CloudIdentity()
  {
      APIKey = "{apiKey}",
      Username = "{username}"
  };
  CloudIdentityProvider cloudIdentityProvider = new CloudIdentityProvider(cloudIdentity);
  UserAccess userAccess = cloudIdentityProvider.Authenticate(cloudIdentity);

.. code-block:: java

  // Authentication in jclouds is lazy and happens on the first call to the cloud.
  TroveApi troveApi = ContextBuilder.newBuilder("rackspace-clouddatabases-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(TroveApi.class);

.. code-block:: javascript

  pkgcloud = require('pkgcloud');
  var rackspace = pkgcloud.dns.createClient({
    provider: 'rackspace',
    username: '{username}',
    apiKey: '{apiKey}'
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

  pyrax.set_credentials('{username}', '{apiKey}', region='{region}')
  cdb = pyrax.cloud_databases

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::Databases.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders. Do not enclose '{}' when you replace them with actual credentials.

  $ curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
    -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
    -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set three environment variables: tenant, TOKEN and endpoint

  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For the Cloud Databases service
