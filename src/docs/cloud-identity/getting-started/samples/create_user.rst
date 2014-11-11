.. code-block:: csharp

.. code-block:: go

  opts := users.CreateOpts{
    Username: "{username}",
    Email: "{email}",
    Enabled: osUsers.Enabled,
  }

  user, err := users.Create(client, opts).Extract()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: sh
