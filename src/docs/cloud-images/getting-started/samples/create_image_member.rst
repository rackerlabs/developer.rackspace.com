.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

  # To create an image member:
  from __future__ import print_function

  import os
  import pyrax

  pyrax.set_setting("identity_type", "rackspace")
  creds_file = os.path.expanduser("~/.rackspace_cloud_credentials")
  pyrax.set_credential_file(creds_file)
  imgs = pyrax.images

  print("You will need a valid project_id for the member you wish to add.")
  images = imgs.list(visibility="private")

  if len(images) == 1:
      image = images[0]
      print("Only one image available; using '%s'." % image.name)
  else:
      print("Images:")
      for pos, image in enumerate(images):
          print("[%s] - %s" % (pos, image.name))
      snum = raw_input("Enter the number of the image you want to share: ")
      if not snum:
          exit()
      try:
          num = int(snum)
      except ValueError:
          print("'%s' is not a valid number." % snum)
          exit()
      if not 0 <= num < len(images):
          print("'%s' is not a valid image number." % snum)
          exit()
      image = images[num]

  project_id = raw_input("Enter the project ID of the member you wish to share "
          "this image with: ")
  if not project_id:
      print("No project ID entered; exiting.")
      exit()
  imgs.http_log_debug = True
  member = imgs.add_image_member(image, project_id)
  print("The following member was added:")
  print(" ID: %s" % member.id)
  print(" Status: %s" % member.status)
  print(" Created at: %s" % member.created_at)

.. code-block:: ruby

  # N/A

.. code-block:: sh

  # To create an image member for an image, specify the image ID
  # for the image you want to share and the user name (member ID)
  # for the user that you want to share the image with:

  curl -s $ENDPOINT/images/{imageId}/members -X POST \
    -d '{"member":"{memberId}"}' \
    -H "X-Auth-Token: $TOKEN" | python -m json.tool

  # NOTE: {imageId} and {memberId} are placeholders: replace these with
  # actual values and do not enclose these values with {}.
