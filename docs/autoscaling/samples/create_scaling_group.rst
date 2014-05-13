.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

.. code-block:: ruby

    # A group builder is provided for you convenience.
    # For other options please refer to the fog docs

    require 'fog/rackspace/models/auto_scale/group_builder'

    INTERNET = '00000000-0000-0000-0000-000000000000'
    SERVICE_NET = '11111111-1111-1111-1111-111111111111'

    attributes = {
      :server_name => "testgroup",
      :image => my_image,
      :flavor => 3,
      :networks => [INTERNET, SERVICE_NET],
      :personality => [
        {
          "path" => "/root/.csivh",
          "contents" => "VGhpcyBpcyBhIHRlc3QgZmlsZS4="
        }
      ],
      :max_entities => 3,
      :min_entities => 2,
      :cooldown => 600,
      :name => "MyScalingGroup",
      :metadata => { "created_by" => "autoscale sample script" },
      :load_balancers => {
         :port =>  80,
         :loadBalancerId => 1234
       }
      :launch_config_type => :launch_server
    }

    my_group = Fog::Rackspace::AutoScale::GroupBuilder.build(service, attributes)
    
