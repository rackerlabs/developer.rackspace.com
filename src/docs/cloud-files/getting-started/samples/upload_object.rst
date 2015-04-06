.. code-block:: csharp

  // Option 1: Upload an object using a Stream
  CloudFilesProvider cloudFilesProvider = new CloudFilesProvider(cloudIdentity);
  using (FileStream fileStream = File.OpenRead("{path_to_file}"))
  {
      cloudFilesProvider.CreateObject("{container_name}", fileStream, "{object_name}");
  }

  // Option 2: Upload a file directly using its filename
  cloudFilesProvider.CreateObjectFromFile("{container_name}", "{path_to_file}", "{object_name}");

.. code-block:: go

  f, err := os.Open("{pathToFile}")
  defer f.Close()
  reader := bufio.NewReader(f)

  _, err := objects.Create(
    serviceClient,
    "{containerName}",
    "{objectName}",
    reader,
    nil,
  ).ExtractHeader()

.. code-block:: java

  ObjectApi objectApi =
      cloudFilesApi.getObjectApiForRegionAndContainer("{region}", "{containerName}");

  // Upload a String
  Payload stringPayload = Payloads.newByteSourcePayload(ByteSource.wrap("sample-data".getBytes()));
  objectApi.put("{objectName}", stringPayload);

  // Upload a File
  ByteSource byteSource = Files.asByteSource(new File("{filePath}"));
  Payload filePayload = Payloads.newByteSourcePayload(byteSource);
  objectApi.put("{objectName}", filePayload);

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
  });

  dest.on('error', function(err) {
    // TODO handle err as appropriate
  });

  dest.on('success', function(file) {
    // TODO handle successful upload case
  });

  // pipe the source to the destination
  source.pipe(dest);

.. code-block:: php

  // Upload an object to the container.
  $localFileName  = __DIR__ . '/php-elephant.jpg';
  $remoteFileName = 'php-elephant.jpg';

  $handle = fopen($localFileName, 'r');
  $object = $container->uploadObject($remoteFileName, $handle);

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

.. code-block:: sh

  curl -i -X PUT $ENDPOINT/{containerName}/{objectName} /
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: image/jpeg" \
    -H "Content-Length: 0"
