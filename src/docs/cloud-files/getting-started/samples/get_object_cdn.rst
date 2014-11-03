.. code-block:: csharp

  CloudFilesProvider cloudFilesProvider = new CloudFilesProvider(cloudIdentity);
  ContainerCDN container = cloudFilesProvider.GetContainerCDNHeader(container: "{container_name}");
  string urlForHTTP = container.CDNUri;
  string urlForHTTPS = container.CDNSslUri;
  string urlForiOSStreaming = container.CDNIosUri;
  string urlForStreaming = container.CDNStreamingUri;

.. code-block:: go

  // Not currently supported by this SDK

.. code-block:: java

  CDNApi cdnApi = cloudFilesApi.getCDNApiForRegion("{region}");

  CDNContainer cdnContainer = cdnApi.get("{containerName}");

  URI uri = cdnContainer.getUri();
  URI sslUri = cdnContainer.getSslUri();
  URI streamingUri = cdnContainer.getStreamingUri();
  URI iosUri = cdnContainer.getIosUri();

.. code-block:: javascript

  var cdnUrl = container.cdnUri + '/' + encodeURIComponent(file.name);

.. code-block:: php

  $cdnUrl = $object->getPublicUrl();

.. code-block:: python

  import urllib
  import urlparse

  encoded_name = urllib.quote(obj.name)
  cdn_url = urlparse.urljoin(container.cdn_uri, encoded_name)

.. code-block:: ruby

  file.public_url

.. code-block:: sh

  curl -i -X HEAD $CDN_ENDPOINT/{containerName}/{objectName} /
      -H "X-Auth-Token: $TOKEN"

