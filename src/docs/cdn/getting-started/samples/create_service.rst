.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  client.createService({
    name: 'example_site',
    domains: [
      {
        domain: 'www.example.com'
      }
    ],
    origins: [
      {
        origin: 'example.com'
      }
    ],
    flavorId: '{flavorId}'
  }, function(err, service) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your newly created service here
  });

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  # Not currently supported by this SDK

.. code-block:: ruby

  # Not currently supported by this SDK

.. code-block:: sh

  $ curl -X POST -d \
    '{
    "name": "mywebsite.com",
    "domains": [
        {
            "domain": "www.mywebsite.com"
        },
        {
            "domain": "blog.mywebsite.com"
        }
    ],
    "origins": [
        {
            "origin": "mywebsite.com",
            "port": 80,
            "ssl": false,
            "rules": []
        }
    },
    "flavor_id": "cdn"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/services | python -m json.tool
