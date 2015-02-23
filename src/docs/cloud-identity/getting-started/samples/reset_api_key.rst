.. code-block:: csharp

.. code-block:: go

  newKey, err := users.ResetAPIKey(client, "{userId}").Extract()

.. code-block:: java

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  $user->resetApiKey();

  echo $user->getApiKey();

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  // Not currently supported

.. code-block:: sh

  curl -s $BASE_URL"users/{userId}/OS-KSADM/credentials/RAX-KSKEY:apiKeyCredentials/RAX-AUTH/reset" \
    -X POST -H "Content-Type: application/json" \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
