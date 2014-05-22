.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $volumes = $volumeService->volumeList();

.. code-block:: python

  volumes = cbs.list()

.. code-block:: ruby

  volumes = @client.volumes.all

.. code-block:: sh

  $ curl -X GET $ENDPOINT/volumes \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" | python -m json.tool
