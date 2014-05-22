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

  # N/A

.. code-block:: sh

  # To authenticate, use your Rackspace Cloud Account user name and API key:
  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X POST \
      -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
      -H "Content-Type: application/json" | python -m json.tool

  # NOTE: {username} and {apiKey} are placeholders:
  # Replace them with actual values and do not enclose the values with {}.

  # Export the publicURL for cloudImages to the ENDPOINT variable,
  # and your authentication token to the TOKEN variable.
  export ENDPOINT="{publicUrl}" # For the Cloud Images service.
  export TOKEN="{token}"
