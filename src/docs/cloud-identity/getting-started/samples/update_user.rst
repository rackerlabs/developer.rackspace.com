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

.. code-block:: sh
