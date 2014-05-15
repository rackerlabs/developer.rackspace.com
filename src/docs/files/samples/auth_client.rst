.. code-block:: csharp

.. code-block:: java

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
  pyrax.set_default_region({region})
  pyrax.set_credentials({username}, {apiKey})

.. code-block:: ruby

  require 'fog'

  # ":rackspace_region" determines which geographic region your data will be
  # stored in. Valid choices include :dfw (Dallas), :ord (Chicago), :iad
  # (Virginia), :syd (Sydney), and :hkg (Hong Kong).

  @client = Fog::Storage.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

  # If you're a UK customer, specify a :rackspace_auth_url of "Fog::Rackspace::UK_AUTH_ENDPOINT" as well, and use the
  # :lon (London) region.
  
.. code-block:: curl
    # Export your publicURL to the publicUrl variable:
    $ export publicUrl="https://identity.api.rackspacecloud.com/v2.0/"
    # To authenticate, use your Rackspace Cloud Account user name and API key:
    $ curl -s $publicUrl/tokens -X 'POST' \
      -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
      -H "Content-Type: application/json" | python -m json.tool
    # NOTE: {username} and {apiKey} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.
