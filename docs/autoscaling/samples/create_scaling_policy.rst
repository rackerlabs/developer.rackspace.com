.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

  my_policy = my_group.policies.create :name => 'Scale by one server', 
    :cooldown => 360, 
    :type => 'webhook', 
    :change => 1