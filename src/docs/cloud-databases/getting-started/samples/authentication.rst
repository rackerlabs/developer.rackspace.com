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
