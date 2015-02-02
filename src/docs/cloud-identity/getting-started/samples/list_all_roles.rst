.. code-block:: csharp

.. code-block:: go

  err := roles.List(client).EachPage(func(page pagination.Page) (bool, error) {
    roleList, err := osRoles.ExtractRoles(page)

    for _, role := range roleList {
      fmt.Printf("ID: %s, Name: %s", role.ID, role.Name)
    }

    return true, nil
  })

.. code-block:: java

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $roles = $service->getRoles();

  foreach ($roles as $role) {
    printf("ID: %s, Name: %s", $role->getId(), $role->getName());
  }

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @client.roles.all

.. code-block:: sh

  curl -s $BASE_URL"OS-KSADM/roles" -X GET -H "X-Auth-Token: $TOKEN" \
    | python -m json.tool
