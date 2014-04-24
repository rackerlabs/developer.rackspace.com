.. code-block:: php

    /** @param $user OpenCloud\Database\Resource\User */
    $user = $instance->enableRootUser();

    // Store root password to your local filesystem
    file_put_contents('~/.root_mysql_pwd', $user->password);

.. code-block:: java

// Get the instance API.
InstanceApi instanceApi = troveApi.getInstanceApiForZone("IAD");

// Enable the root user on a specific instance. Returns the root password.
String password = instanceApi.enableRoot(myInstanceId);