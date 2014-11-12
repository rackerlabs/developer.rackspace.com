.. code-block:: csharp

.. code-block:: go

  opts := gophercloud.AuthOptions{
    Username: "{username}",
    APIKey: "{apiKey}",
  }

  token, err := tokens.Create(client, opts).ExtractToken()

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  // Generating tokens from API keys is handled by default in the main
  // OpenCloud\Rackspace client. See "Setup" section.

.. code-block:: python

.. code-block:: sh
