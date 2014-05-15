.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php
    # To retrieve all images:
    $images = $service->imageList();

    # Choose the first image in the collection
    foreach ($images as $image) {
        $imageId = $image->id;
        break;
    }
    
.. code-block:: python
    # To retrieve all images:
    from __future__ import print_function

    import os
    import pyrax

    pyrax.set_setting("identity_type", "rackspace")
    creds_file = os.path.expanduser("~/.rackspace_cloud_credentials")
    pyrax.set_credential_file(creds_file)
    imgs = pyrax.images

    images = imgs.list()

    if not images:
        print("No images exist.")
        exit()
    print("There are %s images:" % len(images))
    for image in images:
        print("  (%s) %s (ID=%s)" % (image.visibility, image.name, image.id))

.. code-block:: ruby

.. code-block:: curl
    # To list images:
    $ curl -s $publicUrl/images -H "X-Auth-Token: $token" | python -m json.tool