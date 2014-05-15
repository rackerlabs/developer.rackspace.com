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
    
.. code-block:: curl
    # Export the publicURL for Identity to the publicUrl variable:
    $ export publicUrl="https://identity.api.rackspacecloud.com/v2.0/"
    # To authenticate, use your Rackspace Cloud Account user name and API key:
    $ curl -s $publicUrl/tokens -X 'POST' \
        -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
        -H "Content-Type: application/json" | python -m json.tool
    # NOTE: {username} and {apiKey} are placeholders: 
    # Replace them with actual values and do not enclose the values with {}.
    #
    # Export the publicURL for cloudImages to the publicUrl variable,
    # and your authentication token to the token variable.
    $ export publicUrl="https://iad.images.api.rackspacecloud.com/v2/123456"
    $ export token="101010101010101010101010"
 