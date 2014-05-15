.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

    # To list image members:
    
.. code-block:: python

    # To list image members:
    from __future__ import print_function


    import os

    import pyrax


    pyrax.set_setting("identity_type", "rackspace")

    creds_file = os.path.expanduser("~/.rackspace_cloud_credentials")

    pyrax.set_credential_file(creds_file)

    imgs = pyrax.images


    print("This will loop through all your private images and list the members for "
    
        "each.")

    images = imgs.list(visibility="private")

    if not images:
   
     print("No images exist.")
         exit()

    for image in images:
    members = imgs.list_image_members(image)
    if not members:
        print("Image %s: no members" % image.id)
    else:
        print("Image %s:" % image.id)
        for member in members:
            print(" %s (%s)" % (member.id, member.status))

.. code-block:: ruby

.. code-block:: curl
    # To list image members:
    $ curl -s $publicUrl/images/{imageId}/members \
        -H "X-Auth-Token: $token" | python -m json.tool
    # NOTE: {imageId} is a placeholder: replace it with 
    # an actual value and do not enclose it with {}.
