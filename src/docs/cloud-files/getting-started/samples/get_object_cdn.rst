.. code-block:: csharp

.. code-block:: java

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

  # NOTE: {containerName} and {objectName} are placeholders: Replace them
  # with actual values and do not enclose them with {}.
