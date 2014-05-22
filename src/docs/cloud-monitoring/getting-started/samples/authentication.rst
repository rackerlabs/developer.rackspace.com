.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::Monitoring.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apikey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  # {username}, {apiKey} below are placeholders, do not enclose '{}' when you replace them with actual credentials.

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
     -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
     -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set three environment variables: tenant, TOKEN and endpoint

  export TENANT="{tenantId}"
  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For Monitoring service