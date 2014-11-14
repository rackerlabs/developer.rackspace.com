.. code-block:: csharp

.. code-block:: go

  err := users.Delete(client, "{userId}").ExtractErr()

.. code-block:: java

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $user->delete();

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  user.destroy

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}" -X DELETE -H "X-Auth-Token: $TOKEN"
