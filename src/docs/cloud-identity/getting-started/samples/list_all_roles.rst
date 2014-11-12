.. code-block:: csharp

.. code-block:: go

  err := roles.List(client).EachPage(func(page pagination.Page) (bool, error) {
    roleList, err := osRoles.ExtractRoles(page)

    for _, role := range roleList {

    }

    return true, nil
  })

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: sh
