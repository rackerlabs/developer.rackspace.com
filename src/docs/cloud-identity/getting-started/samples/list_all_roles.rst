.. code-block:: csharp

.. code-block:: go

  err := roles.List(client).EachPage(func(page pagination.Page) (bool, error) {
    roleList, err := osRoles.ExtractRoles(page)

    for _, role := range roleList {
      if role.Name == "identity:user-admin" {
        roleID := role.ID
      }
    }

    return true, nil
  })

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $roles = $service->getRoles();

  foreach ($roles as $role) {
    if ($role->getName() == 'identity:user-admin') {
      echo $role->getId();
    }
  }

.. code-block:: python

.. code-block:: sh

  curl -s $BASE_URL"OS-KSADM/roles" -X GET -H "X-Auth-Token: $TOKEN" \
    | python -m json.tool
