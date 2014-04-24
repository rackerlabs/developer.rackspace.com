.. code-block:: nodejs

.. code-block:: python

  import urllib
  import urlparse
  encoded_name = urllib.quote(obj.name)
  cdn_url = urlparse.urljoin(container.cdn_uri, encoded_name)
