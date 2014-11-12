.. code-block:: csharp

.. code-block:: go

  err := roles.DeleteUserRole(client, "{userId}", "{roleId}").ExtractErr()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $user->removeRole('{roleId}');

.. code-block:: python

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}/roles/OS-KSADM/{roleId}" \
    -X DELETE -H "X-Auth-Token: $TOKEN"
