.. code-block:: csharp

.. code-block:: go

  opts := users.UpdateOpts{Username: "{newUsername}"}
  user, err := users.Update(client, "{userId}", opts).Extract()

.. code-block:: java

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $user->update(array(
    'username' => '{newUsername}'
  ));

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  // Retrieve user by name
  user = @client.users.get_by_name("{username}")

  // ... or by ID
  user = @client.users.get("{userId}")

  user.username = "{newUsername}"
  user.save

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}" -X POST -H "X-Auth-Token: $TOKEN" \
    -d '{"user": {"username": "{newUsername}" }}' \
    -H "Content-Type: application/json" | python -m json.tool
