.. code-block:: csharp


.. code-block:: java


.. code-block:: javascript


.. code-block:: php

    /** @param $user OpenCloud\Database\Resource\User */
    $user = $instance->enableRootUser();

    // Store root password to your local filesystem
    file_put_contents('~/.root_mysql_pwd', $user->password);

.. code-block:: python

    inst.enable_root_user()

.. code-block:: ruby
