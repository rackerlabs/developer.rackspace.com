.. code-block:: nodejs


.. code-block:: python

  # First, you'll need to set the "temp url key" on your Account. This is an
  # arbitrary secret shared between Cloud Files and your application that's
  # used to validate temp url requests. You only need to do this once.

  # Let pyrax set the temp URL key for you
  pyrax.cloudfiles.set_temp_url_key()

  # Set your own
  # pyrax.cloudfiles.set_temp_url_key("jnRB6#1sduo8YGUF&%7r7guf6f")

  # Get a temporary URL that will expire in 1800 seconds
  temp_url = obj.get_temp_url(1800)
