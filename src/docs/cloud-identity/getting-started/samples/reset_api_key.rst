.. code-block:: csharp

.. code-block:: go

  newKey, err := users.ResetAPIKey(client, "{userId}").Extract()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $user->resetApiKey();

  echo $user->getApiKey();

.. code-block:: python

.. code-block:: ruby

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}/OS-KSADM/credentials/RAX-KSKEY:apiKeyCredentials/RAX-AUTH/reset" \
    -X POST -H "Content-Type: application/json" \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
