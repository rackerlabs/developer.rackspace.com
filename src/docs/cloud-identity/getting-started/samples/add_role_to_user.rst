.. code-block:: csharp

.. code-block:: go

  err := roles.AddUserRole(client, "{userId}", "{roleId}").ExtractErr()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $user->addRole('{roleId}');

.. code-block:: python

.. code-block:: ruby

  // This operation is not currently supported.

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}/roles/OS-KSADM/{roleId}" \
     -X PUT -H "X-Auth-Token: $TOKEN"
