.. code-block:: csharp

.. code-block:: go

  err := users.List(client).EachPage(func(page pagination.Page) (bool, error) {
    userList, err := users.ExtractUsers(page)

    for _, user := range userList {
      if user.Username == "fooPerson" {
        userID := user.ID
      }
    }

    return true, nil
  })

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: sh
