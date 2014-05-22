.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    // Write a callback which outputs the name, status and progress of a LB
    $callback = function($lb) {
        if (!empty($lb->error)) {
            var_dump($lb->error);
            exit;
        } else {
            printf(
                "Waiting on %s/%-12s %4s%%",
                $lb->name(),
                $lb->status(),
                isset($lb->progress) ? $lb->progress : 0
            );
        }
    };

    // Poll the resource until it reaches an ACTIVE state, with a 600s (5 min) timeout
    // Please be aware this is a blocking operation
    $server->waitFor('ACTIVE', 300, $callback);

.. code-block:: python

  pyrax.utils.wait_until(load_balancer, "status", "ACTIVE", interval=1,
                         attempts=30)

.. code-block:: ruby

  @balancer.wait_for { ready? }

.. code-block:: sh
