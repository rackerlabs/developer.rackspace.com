.. code-block:: csharp

.. code-block:: java

  // Authentication in jclouds is lazy and happens on the first call to the cloud.
  AutoscaleApi autoscaleApi = ContextBuilder.newBuilder("rackspace-autoscale-us")
      .credentials("{username}", "{apiKey}")
      .buildApi(AutoscaleApi.class);

.. code-block:: javascript

.. code-block:: php

  require 'vendor/autoload.php';

  use OpenCloud\Rackspace;

  $client = new Rackspace(Rackspace::US_IDENTITY_ENDPOINT, array(
      'username' => '{username}',
      'apiKey'   => '{apiKey}'
  ));

.. code-block:: python

  import pyrax

  pyrax.set_setting("identity_type", "rackspace")
  pyrax.set_default_region('{region}')
  pyrax.set_credentials('{username}', '{apiKey}')

.. code-block:: ruby

  require 'fog'

  @client = Fog::Rackspace::AutoScale.new(
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}'
  )

.. code-block:: sh

  curl -s https://identity.api.rackspacecloud.com/v2.0/tokens -X 'POST' \
    -d '{"auth":{"RAX-KSKEY:apiKeyCredentials":{"username":"{username}", "apiKey":"{apiKey}"}}}' \
    -H "Content-Type: application/json" | python -m json.tool

  # From the resulting json, set two environment variables: TOKEN and ENDPOINT.

  export TOKEN="{tokenId}"
  export ENDPOINT="{publicUrl}" # For Auto Scaling service(s)
