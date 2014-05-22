.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $healthMonitor = $loadBalancer->healthMonitor();
  $healthMonitor->update(array(
      'delay' => 20
  ));

.. code-block:: python

  load_balancer.add_health_monitor(type="CONNECT", delay=10, timeout=10,
                                   attemptsBeforeDeactivation=3)

.. code-block:: ruby

  # Arguments, in order:
  #
  #  type: Type of the health monitor. Must be 'CONNECT' to monitor connections.
  #  delay: Minimum number of seconds to wait before executing the monitor, between
  #         1 and 3600.
  #  timeout: Maximum number of seconds to wait for a connection to be established
  #           before timing out, between 1 and 300.
  #  attempts_before_deactivation: Number of monitor failures to tolerate before
  #                                removing a node from rotation. Between 1 and 10.
  @balancer.enable_health_monitor('CONNECT', 10, 10, 3)
  @balancer.wait_for { ready? }

.. code-block:: sh
