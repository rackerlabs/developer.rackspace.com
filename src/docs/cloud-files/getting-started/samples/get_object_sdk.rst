.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

  // We need to use the fs module to access the local disk.
  var fs = require('fs');

  // TODO use a real file here
  var filePath = '/tmp/somefile.txt';

  // create a writeable stream for our source file
  var dest = fs.createWriteStream(filePath);

  // create a writeable stream for our destination
  var source = client.download({
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

  // Get the object content (data) as a stream.
  $objectContent = $object->getContent();

  // Write object content to file on local filesystem.
  $objectContent->rewind();
  $stream = $objectContent->getStream();
  $localFilename = tempnam("/tmp", 'php-opencloud-');
  file_put_contents($localFilename, $stream);

.. code-block:: python

  # Get the data as a string
  data = obj.get()

  # Download the object locally to a file
  obj.download("/tmp")

.. code-block:: ruby

  file.body

.. code-block:: sh
