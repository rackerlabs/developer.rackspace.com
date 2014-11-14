.. code-block:: csharp

.. code-block:: go

  err := users.List(client).EachPage(func(page pagination.Page) (bool, error) {
    userList, err := users.ExtractUsers(page)

    for _, user := range userList {
      fmt.Printf("ID: %s, Name: %s", user.ID, user.Username)
    }

    return true, nil
  })

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $users = $service->getUsers();

  foreach ($users as $user) {
    printf("ID: %s, Name: %s", $user->getId(), $user->getName());
  }

.. code-block:: python

.. code-block:: ruby

  @client.users.all

.. code-block:: sh

  curl -s $BASE_URL"users" -X GET -H "X-Auth-Token: $TOKEN" | python -m json.tool
