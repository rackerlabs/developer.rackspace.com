.. code-block:: java

  ObjectApi objectApi = cloudFilesApi.getObjectApiForRegionAndContainer("{region}", "{container}");

  // Create a Payload from a ByteSource and upload the object
  ByteSource data = ByteSource.wrap("objectData".getBytes());
  Payload payload = Payloads.newByteSourcePayload(data);

  objectApi.put("{object}", payload, ImmutableMap.<String, String> of());

.. code-block:: javascript

  // we need to use the fs module to access the local disk
  var fs = require('fs');

  // TODO use a real file here
  var filePath = '/tmp/somefile.txt';

  // create a read stream for our source file
  var source = fs.createReadStream(filePath);

  // create a writeable stream for our destination
  var dest = client.upload({
    container: 'sample-container-test',
    remote: 'somefile.txt'
  }, function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

  // pipe the source to the destination
  source.pipe(dest);

.. code-block:: php

  // Upload an object to the container.
  $localFileName  = __DIR__ . '/php-elephant.jpg';
  $remoteFileName = 'php-elephant.jpg';
  
  $fileData = fopen($localFileName, 'r');
  $object = $container->uploadObject($remoteFileName, $fileData);
  
  // Note that while we call fopen to open the file resource, we do not call fclose at the end.
  // The file resource is automatically closed inside the uploadObject call.

.. code-block:: python

  container = pyrax.cloudfiles.create_container("gallery")
  obj = container.store_object("thumbnail", data)

.. code-block:: ruby

  # :body can also be an open IO object like a File, to stream content instead
  # of providing it all at once.

  file = directory.files.create(
    :key => 'somefile.txt',
    :body => 'Rackspace is awesome!'
  )

