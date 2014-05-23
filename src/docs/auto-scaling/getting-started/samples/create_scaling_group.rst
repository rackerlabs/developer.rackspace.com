.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    $service = $client->autoscaleService();

    $object = (object) array(
       // Config which determines the autoscale group's behaviour
       'groupConfiguration' => (object) array(
          'name'        => 'New autoscale group',
          'minEntities' => 5,
          'maxEntities' => 25,
          'cooldown'    => 60
       ),
       // Specify what's going to launch - in this case a server
       'launchConfiguration' => (object) array(
          'type' => 'launch_server',
          'args' => (object) array(
             // Server properties
             'server' => (object) array(
                'flavorRef' => '{flavorId}',
                'name'      => 'My server name',
                'imageRef'  => '{imageId}'
             ),
             // LB properties
             'loadBalancer' => array(
                (object) array(
                   'loadBalancerId' => '{loadBalancerId}',
                   'port'           => 80
                )
             )
          )
       )
    );

    $group = $service->group();
    $group->create($object);

.. code-block:: python

    # After authenticating
    au = pyrax.autoscale
    networks = [pyrax.cloudnetworks.PUBLIC_NET_ID,
            pyrax.cloudnetworks.SERVICE_NET_ID]
    scaling_group = au.create("My Scaling Group", cooldown=60,
            min_entities=2, max_entities=24,
            launch_config_type="launch_server", server_name="My Server Name",
            image_id="{imageId}", flavor_id="{flavorId}", disk_config="MANUAL",
            metadata={"someKey": "someValue"}, personality=[{"contents":
            "SomeBase64EncodedString", "path": "/etc/SomeFileName.txt"}],
            networks=networks, load_balancers=("{loadBalancerId}", 80),
            key_name="MySSHKeyName")
    # Parameter explanations:
    #   sg_name: name of the scaling group
    #   cooldown: number of seconds to wait between applications of
            scaling actions
    #   min_entities: the lower limit for scaling down
    #   max_entities: the upper limit for scaling up
    #   launch_config_type: Only option currently is 'launch_server'
    #   server_name: The base name for servers created by autoscale
    #   image_id: the ID of the image to be used for new servers
    #   flavor_id: the ID of the flavor to be used for new servers
    #   disk_config: either 'MANUAL' or 'AUTO'
    #   metadata: Metadata key/value pairs to be applied to new servers
    #   personality: Small text files that are created on the new servers
    #   networks: The networks to which you want to attach new servers
    #   load_balancers: Either a  list of (id, port) tuples or a single such
    #       tuple, representing the loadbalancer(s) to add the new servers to
    #   key_name: name of the SSH public key to be added to the new servers'
    #       'authorized_keys' file.

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
