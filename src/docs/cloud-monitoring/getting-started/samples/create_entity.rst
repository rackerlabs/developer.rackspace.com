.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  entity = @client.entities.create(:label => 'somehost.domain.com')

.. code-block:: sh

  $ curl -X POST -d \
    '{
      "label": "{entityLabel}",
      "ip_addresses": {
          "entity_ip_addresses_hash_key": "{ipAddress1}",
          "b": "{ipAddress2}",
          "c": "{ipAddress3}",
          "test": "{ipAddress4}"
      }
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/entities | python -m json.tool