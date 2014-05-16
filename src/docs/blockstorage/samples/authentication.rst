.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    require 'vendor/autoload.php';

    use OpenCloud\Rackspace;

    // Instantiate a Rackspace client.
    $client = new Rackspace(Rackspace::US_IDENTITY_ENDPOINT, array(
        'username' => '{username}',
        'apiKey'   => '{apiKey}'
    ));

    $volumeService = $client->volumeService();

.. code-block:: python

  import pyrax
  pyrax.set_credentials({username}, {api_key}, region={REGION})
  cbs = pyrax.cloud_blockstorage

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::BlockStorage.new(
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
    # To perform Cloud Block Storage operations, export the publicURL for 
    # cloudBlockStorage to the publicUrl variable:
    $ export publicUrl="https://syd.blockstorage.api.rackspacecloud.com/v1/{account}"
    #
    # NOTE: {username}, {apiKey}, {token}, and {account} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.
