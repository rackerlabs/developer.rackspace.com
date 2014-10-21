.. code-block:: csharp

  // This is not supported through the .NET SDK at this time

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  // Create a new ContextBuilder
  ContextBuilder builder = ContextBuilder.newBuilder("rackspace-cloudfiles-us")
          .credentials("{username}", "{apiKey}");

  // Access the RegionScopedBlobStore and get the Cloud Files API
  BlobStore blobStore = builder.buildView(RegionScopedBlobStoreContext.class)
          .blobStoreInRegion("{region}");
  CloudFilesApi cloudFilesApi = blobStore.getContext().unwrapApi(CloudFilesApi.class);

  // Get the AccountApi and update the temporary URL key if not set
  AccountApi accountApi = cloudFilesApi.getAccountApiForRegion("{region}");
  accountApi.updateTemporaryUrlKey("jnRB6#1sduo8YGUF&%7r7guf6f");

  // Get the temporary URL
  BlobRequestSigner signer = blobStore.getContext().signerInRegion("{region}");
  HttpRequest request = signer.signGetBlob("example_container", "someobject");
  URI tempUrl = request.getEndpoint();

.. code-block:: javascript

  // This is not supported through the pkgcloud SDK at this time

.. code-block:: php

  // First, you'll need to set the "temp url key" on your Account. This is an
  // arbitrary secret shared between Cloud Files and your application that's
  // used to validate temp url requests. You only need to do this once.
  $account = $service->getAccount();
  $account->setTempUrlSecret();

  // Get a temporary URL that will expire in 3600 seconds (1 hour) from now
  // and only allow GET HTTP requests to it.
  $tempUrl = $object->getTemporaryUrl(3600, 'GET');

.. code-block:: python

  # First, you'll need to set the "temp url key" on your Account. This is an
  # arbitrary secret shared between Cloud Files and your application that's
  # used to validate temp url requests. You only need to do this once.

  # Let pyrax set the temp URL key for you.
  pyrax.cloudfiles.set_temp_url_key()

  # Or, you can set your own.
  # pyrax.cloudfiles.set_temp_url_key("jnRB6#1sduo8YGUF&%7r7guf6f")

  # Get a temporary URL that will expire in 3600 seconds (1 hour) from now.
  temp_url = obj.get_temp_url(1800)

.. code-block:: ruby

  # First, you'll need to set the "temp url key" on your Account. This is an
  # arbitrary secret shared between Cloud Files and your application that's
  # used to validate temp url requests. You only need to do this once.

  account = @client.account
  account.meta_temp_url_key = 'jnRB6#1sduo8YGUF&%7r7guf6f'
  account.save

  # Then, when you want to generate temp urls, pass it to the Fog::Storage
  # constructor as ":rackspace_temp_url_key":

  @client = Fog::Storage.new(
    :provider => 'rackspace',
    :rackspace_username => '{username}',
    :rackspace_api_key => '{apiKey}',
    :rackspace_region => '{region}',
    :rackspace_temp_url_key => 'jnRB6#1sduo8YGUF&%7r7guf6f'
  )

  # Now, you can create a temporary url for any file you access from that
  # @client with the #url method. Its argument is the expiration time for
  # the generated URL, expressed as seconds since the epoch (1970-01-01 00:00).

  directory = @client.directories.get('example_container')
  file = directory.files.get('someobject')
  temp_url = file.url(Time.now.to_i + 600)

.. code-block:: sh

  # To create a TempURL, first set the X-Account-Meta-Temp-Url-Key metadata
  # header on your Cloud Files account to a key that only you know.

  curl -i -X POST $ENDPOINT \
    -H "X-Auth-Token: $TOKEN" \
    -H "X-Account-Meta-Temp-Url-Key: {arbitraryKey}"

  # Create the temp_url_sig and temp_url query parameter values. OpenStack
  # Object Storage provides the swift-temp-url script that auto-generates
  # the temp_url_sig and temp_url_expires query parameters. For example,
  # you might run this command:

  bin/swift-temp-url GET 3600 $ENDPOINT/{containerName}/{objectName} {arbitraryKey}

  # To create the temporary URL, prefix this path that is returned by the swift-temp-url
  # command with the storage host name. For example, prefix the path with
  # https://swift-cluster.example.com, as follows:

  $ENDPOINT/{containerName}/{objectName}\
    ?temp_url_sig=5c4cc8886f36a9d0919d708ade98bf0cc71c9e91\
    &temp_url_expires=1374497657
