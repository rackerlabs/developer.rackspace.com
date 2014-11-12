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

.. code-block:: python

.. code-block:: sh
