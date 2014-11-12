.. code-block:: csharp

.. code-block:: go

  opts := gophercloud.AuthOptions{
    Username: "{username}",
    Password: "{password}",
  }

  token, err := tokens.Create(client, opts).ExtractToken()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  // Password-based authentication is only available for OpenCloud\OpenStack
  // clients

  use OpenCloud\OpenStack;

  $client = new OpenStack('http://my-openstack.com:35357/v2.0/', array(
      'username'   => '{username}',
      'password'   => '{password}',
      'tenantName' => '{tenantName}'
  ));

.. code-block:: python

.. code-block:: sh
