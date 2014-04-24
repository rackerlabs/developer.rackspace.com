.. code-block:: java

  CDNApi cdnApi = cloudFilesApi.getCDNApiForRegion("{region}");
  URI cdnUri = cdnApi.get("{container}").getUri();

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
