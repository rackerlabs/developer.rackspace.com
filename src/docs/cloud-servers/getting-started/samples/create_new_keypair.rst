.. code-block:: csharp

.. code-block:: java

  KeyPairApi keyPairApi = novaApi.getKeyPairExtensionForZone("{region}").get();
  KeyPair keyPair = keyPairApi.create("my-keypair");

  File keyPairFile = new File("my-keypair.pem");
  // Using com.google.common.io.Files
  Files.write(keyPair.getPrivateKey(), keyPairFile, UTF_8);

.. code-block:: javascript

  client.addKey({ name: 'my-keypair' }, function (err, key) {
    if (err) {
      // TODO handle as appropriate
      return;
    }

    // TODO use your key
  });

.. code-block:: php

  // Get the API to generate a new keypair.
  $keypair = $service->keypair();
  $keypair->create(array(
     'name' => 'my-keypair'
  ));

  // Save the generated key to your local filesystem and set appropriate permissions.
  $localPath = 'my-keypair.pem';
  file_put_contents($localPath, $keypair->getPrivateKey());
  chmod($localPath, 0600);

.. code-block:: python

.. code-block:: ruby

  key_pair = @client.key_pairs.create(:name => 'my-keypair')
  File.write('my-keypair.pem', key_pair.private_key, :perm => 0600)

.. code-block:: sh

  curl -X POST $ENDPOINT/os-keypairs -d \
    '{"keypair":{"name":"{keyPairName}"} }' \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool
