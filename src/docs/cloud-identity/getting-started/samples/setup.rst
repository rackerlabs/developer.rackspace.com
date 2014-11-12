.. code-block:: csharp

.. code-block:: go

  import (
    "github.com/rackspace/gophercloud"
    osUsers "github.com/rackspace/gophercloud/openstack/identity/v2/users"
    osRoles "github.com/rackspace/gophercloud/openstack/identity/v2/roles"
    "github.com/rackspace/gophercloud/rackspace"
    "github.com/rackspace/gophercloud/rackspace/identity/v2/roles"
    "github.com/rackspace/gophercloud/rackspace/identity/v2/tokens"
    "github.com/rackspace/gophercloud/rackspace/identity/v2/users"
  )

  provider, err := rackspace.AuthenticatedClient(gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  })

  client, err := rackspace.NewIdentityV2(provider)

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  require 'vendor/autoload.php';

  use OpenCloud\Rackspace;

  $client = new Rackspace(Rackspace::US_IDENTITY_ENDPOINT, array(
      'username' => '{username}',
      'apiKey'   => '{apiKey}'
  ));

  $service = $client->identityService();

.. code-block:: python

.. code-block:: ruby

  @client = Fog::Identity.new(
    :provider => "Rackspace",
    :rackspace_username => "{username}",
    :rackspace_api_key => "{apiKey}"
  )

.. code-block:: sh

  # For most API operations (ORD, IAD, DFW, SYD, HKG)
  export BASE_URL=https://identity.api.rackspacecloud.com/v2.0/

  # For LON API operations
  export BASE_URL=https://lon.identity.api.rackspacecloud.com/v2.0/
