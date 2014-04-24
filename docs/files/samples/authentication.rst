.. code-block:: java

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
    :rackspace_api_key => '{apikey}',
    :rackspace_region => :'{region}'
  )

  # If you're a UK customer, specify a :rackspace_auth_url as well, and use the
  # :lon (London) region.

  @client = Fog::Storage.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => :lon,
    :rackspace_auth_url => Fog::Rackspace::UK_AUTH_ENDPOINT
  )

