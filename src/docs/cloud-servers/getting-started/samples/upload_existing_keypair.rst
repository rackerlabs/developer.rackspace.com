.. code-block:: csharp

  // This is not supported through the .NET SDK at this time

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone("{region}").get();

  File keyPairFile = new File("{/home/my-user/.ssh/id_rsa.pub}");
  String publicKey = Files.toString(keyPairFile, UTF_8); // Using com.google.common.io.Files

  KeyPair keyPair = keyPairApi.createWithPublicKey("my-keypair", publicKey);

.. code-block:: javascript

  // we need the fs module to access the local disk
  var fs = require('fs');

  // TODO provide the path to your existing key.
  var keyPath = '/home/my-user/.ssh/id_rsa.pub';

  fs.readFile(keyPath, function(err, data) {
    client.addKey({
      name: 'my-keypair',
      'public_key': data.toString()
    }, function (err, key) {
      if (err) {
        // TODO handle as appropriate
        return;
      }

      // TODO use your key
    });
  });

.. code-block:: php

  $keypair = $service->keypair();

  $keypair->create(array(
     'name'      => 'my-keypair',
     'publicKey' => file_get_contents('/home/my-user/.ssh/id_rsa.pub')
  ));

.. code-block:: python

  import os

  public_key = open(os.path.expanduser("~/.ssh/id_rsa.pub")).read()
  keypair = cs.keypairs.create("mykeypair", public_key)

.. code-block:: ruby

  key_pair = @client.key_pairs.create(
    :name => 'my-keypair',
    :public_key => File.read('/home/my-user/.ssh/id_rsa.pub')
  )

.. code-block:: sh

  curl -X POST $ENDPOINT/os-keypairs -d \
    '{
    "keypair":{
        "name":"{keyPairName}",
        "public_key":"ssh-rsa AAAAB3Nz ..."
      }
    }' \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
