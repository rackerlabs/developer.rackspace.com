.. code-block:: csharp

   new CloudBlockStorageProvider({cloudIdentity}).CreateVolume(
	size: {size}, 
	displayDescription: "{description}", 
	displayName: "{name}", 
	region: "{region}");

.. code-block:: go

	options := &CreateOpts{Name: "vol-001", Size: 100}
	n, err := Create(ServiceClient(), options).Extract()

.. code-block:: java

  VolumeApi volumeApi = cinderApi.getVolumeApiForZone("{region}");

  CreateVolumeOptions options = CreateVolumeOptions.Builder
          .name("photos")
          .volumeType("SATA");

  Volume volume = volumeApi.create(100, options);

.. code-block:: javascript

  // To create the volume, specify a name and size (in GB, with a miminum of
  // 100GB). You may optionally specify a volume type, which is either 'SSD'
  // (faster, more expensive), or 'SATA' (more affordable). SATA is the
  // default if you omit this.

  client.createVolume({
    name: 'photos',
    volumeType: 'SATA',
    size: 100
  }, function(err, volume) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your newly created volume
  });

.. code-block:: php

  // To create the volume, specify a name and size (in GB, with a miminum of
  // 100GB). You may optionally specify a volume type, which is either 'SSD'
  // (faster, more expensive), or 'SATA' (more affordable). SATA is the
  // default if you omit this.

  $volume = $volumeService->volume();
  $volume->create(array(
      'display_name' => 'photos',
      'size' => 100,
      'volume_type' => 'SATA'
  ));

.. code-block:: python

  # To create the volume, specify a name and size (in GB, minimum 100GB). You
  # may optionally specify a volume type, which is either 'SSD' (faster, more
  # expensive), or 'SATA' (more affordable). SATA is the default if you omit
  # this.

  vol = cbs.create('photos', 100, 'SATA')

.. code-block:: ruby

  # The :size parameter is specified in GB, with a minimum of 100GB.
  # The :volume_type parameter may be either 'SSD' (faster, more expensive), or
  # 'SATA' (more affordable). SATA is the default it you omit this.
  volume = @client.volumes.create(
    :display_name => 'photos',
    :size => '100',
    :volume_type => 'SATA'
  )
  volume.wait_for { ready? }

.. code-block:: sh

  $ curl -X POST -d \
    '{
    "volume": {
        "display_name": "{name}",
        "size": 100
     }
    }'\
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/volumes | python -m json.tool
