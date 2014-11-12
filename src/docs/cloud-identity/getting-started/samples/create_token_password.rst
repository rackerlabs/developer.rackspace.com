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

  # {username}, {password} below are placeholders, do not enclose '{}' when you
  # replace them with actual credentials.

  curl -s $BASE_URL"tokens" -X POST \
     -d '{
          "auth":{"passwordCredentials": {
            "username":"{username}",
            "password":"{password}"
          }}
        }' \
     -H "Content-Type: application/json" | python -m json.tool

  # From the resulting JSON, set this environment variable:
  export TOKEN="{tokenId}"
