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

.. code-block:: python

    import pyrax
    pyrax.set_setting("identity_type", "rackspace")
    pyrax.set_default_region({region})
    pyrax.set_credentials({username}, {apiKey})

.. code-block:: ruby

    @client = Fog::Rackspace::Queues.new(
      :rackspace_username => '{username}',
      :rackspace_api_key => '{apikey}',
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
    # To perform Cloud Queues operations, export the publicURL for cloudQueues 
    # to the publicUrlFiles variable:
    $ export publicUrlFiles="https://hkg.queues.api.rackspacecloud.com/v1/{account}"
    #
    # NOTE: {username}, {apiKey}, {token}, and {account} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.