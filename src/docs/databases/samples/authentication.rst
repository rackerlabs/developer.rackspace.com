.. code-block:: csharp


.. code-block:: java

  // The TroveApi will provide access to all database features, such as Instances or Users.
  TroveApi troveApi = ContextBuilder.newBuilder("rackspace-clouddatabases-us")
          .credentials("{username}", "{apiKey}"")
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
  pyrax.set_credentials({username}, {api_key}, region={region})
  cdb = pyrax.cloud_databases

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::Databases.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )
  
.. code-block:: curl
    # Export the publicURL for Identity to the auth variable:
    $ export auth="https://identity.api.rackspacecloud.com/v2.0/tokens"
    # 
    # To authenticate, you use your Rackspace Cloud Account user name and API key:
    $ curl -s $auth -X 'POST' \
        -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey"}}}' \
        -H "Content-Type: application/json" | python -m json.tool
    #
    # In the output, find your authentication token in the id field in the token element.
    # Export your token to the token environment variable:
    $ export token="{token}"
    #
    # To perform Autoscale operations, export the publicURL for 
    # autoscale to the publicUrl variable:
    $ export publicUrl="https://ord.autoscale.api.rackspacecloud.com/v1.0/{account}"
    #
    # NOTE: {username}, {apiKey}, {token}, and {account} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.
  
