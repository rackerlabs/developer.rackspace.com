.. code-block:: csharp

.. code-block:: go

  opts := users.UpdateOpts{Username: "new_username"}
  user, err := users.Update(client, "{userId}", opts).Extract()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $user->update(array(
    'username' => 'new_username'
  ));

.. code-block:: python

.. code-block:: ruby

  // Retrieve user by name
  user = @client.users.get_by_name("{username}")

  // ... or by ID
  user = @client.users.get("{userId}")

  user.username = "new_username"
  user.save

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}" -X POST -H "X-Auth-Token: $TOKEN" \
    -d '{"user": {"username": "new_username"}}' \
    -H "Content-Type: application/json" | python -m json.tool
