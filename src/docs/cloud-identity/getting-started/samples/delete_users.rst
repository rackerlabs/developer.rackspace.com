.. code-block:: csharp

.. code-block:: go

  err := users.Delete(client, "{userId}").ExtractErr()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $user->delete();

.. code-block:: python

.. code-block:: ruby

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}" -X DELETE -H "X-Auth-Token: $TOKEN"
