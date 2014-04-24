.. code-block:: node

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

.. code-block:: ruby

  require 'fog'

  service = Fog::DNS.new :provider => :rackspace,
    rackspace_username => RACKSPACE_USERNAME,
    rackspace_api_key => RACKSPACE_API_KEY,
    rackspace_region => RACKSPACE_REGION

.. code-block:: java

TroveApi troveApi;

// The TroveApi will provide access to all database features, such as Instances or Users.
troveApi = ContextBuilder.newBuilder("rackspace-clouddatabases-us")
            .credentials(username, apiKey) // Your username and api key are provided here.
            .buildApi(TroveApi.class);