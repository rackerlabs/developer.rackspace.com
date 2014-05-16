.. code-block:: csharp

.. code-block:: java

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

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::LoadBalancers.new(
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
    # To perform Cloud Load Balancers operations, export the publicURL for cloudLoadBalancers 
    # to the publicUrlFiles variable:
    $ export publicUrlFiles="https://syd.loadbalancers.api.rackspacecloud.com/v1.0/{account}"
    #
    # NOTE: {username}, {apiKey}, {token}, and {account} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.