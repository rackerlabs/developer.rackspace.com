.. code-block:: csharp

  // Not currently supported by this SDK

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Not currently supported by this SDK

.. code-block:: javascript

  // Not currently supported by this SDK

.. code-block:: php

  // Not currently supported by this SDK

.. code-block:: python

  // Not currently supported by this SDK

.. code-block:: ruby

  @subnet = @client.subnets.new({
    :name       => "MyNewsubnet",
    :cidr       => "192.168.101.1/24",
    :network_id => @network.id,
    :ip_version => "4"
  }).save

.. code-block:: sh

  // Not currently supported by this SDK

  $ curl -X GET $ENDPOINT/entities/{entityId}/checks \
    -H "X-Auth-Token: $TOKEN" \
    -H "Accept: application/json" | python -m json.tool
