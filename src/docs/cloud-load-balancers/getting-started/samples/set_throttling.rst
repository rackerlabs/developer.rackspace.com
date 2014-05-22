.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

  $throttle = $loadBalancer->connectionThrottle();

  // Allow 5,000 simultaneous connections
  $throttle->create(array(
      'maxConnections' => 5000
  ));

  // Or, allow 10,000 every 5 seconds
  $throttle->create(array(
      'maxConnectionRate' => 10000,
      'rateInterval'      => 5
  ));

.. code-block:: python

  #  maxConnectionRate: Maximum connections from a single IP within a given
  #                       rate_interval. 0 means unlimited; otherwise, between 1
  #                       and 100000.
  #  maxConnections: Maximum simultaneous connections to allow from a single IP
  #                   within the rate interval. 0 means unlimited; otherwise,
  #                   between 1 and 100000.
  #  minConnections: Allow at least this many connections per IP before throttling.
  #                   0 means unlimited; otherwise, between 1 and 1000.
  #  rateInterval: Frequency, in seconds, at which max_connection_rate is assessed.
  #                 Between 1 and 3600.
  load_balancer.add_connection_throttle(maxConnectionRate=10000,
                                        maxConnections=5000,
                                        minConnections=2,
                                        rateInterval=5)

.. code-block:: ruby

  # Arguments, in order:
  #
  #  max_connections: Maximum simultaneous connections to allow from a single IP
  #                   within the rate interval. 0 means unlimited; otherwise,
  #                   between 1 and 100000.
  #  min_connections: Allow at least this many connections per IP before throttling.
  #                   0 means unlimited; otherwise, between 1 and 1000.
  #  max_connection_rate: Maximum connections from a single IP within a given
  #                       rate_interval. 0 means unlimited; otherwise, between 1
  #                       and 100000.
  #  rate_interval: Frequency, in seconds, at which max_connection_rate is assessed.
  #                 Between 1 and 3600.
  #
  @balancer.enable_connection_throttling(5000, 2, 10000, 5)
  @balancer.wait_for { ready? }

.. code-block:: sh
