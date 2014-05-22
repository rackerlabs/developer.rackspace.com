.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

.. code-block:: php

.. code-block:: python

    # To delete an image member:
    from __future__ import print_function

    import os
    import pyrax

    pyrax.set_setting('identity_type', 'rackspace')
    creds_file = os.path.expanduser('~/.rackspace_cloud_credentials')
    pyrax.set_credential_file(creds_file)
    imgs = pyrax.images

    print('You will be able to remove members from an image (that is, unshare it)'
          )
    images = imgs.list(visibility='private')

    images_with_members = []
    for image in images:
        members = image.list_members()
        if not members:
            continue
        images_with_members.append((image, members))

    if not images_with_members:
        print('You have no images that are shared with other members.')
        exit()

    member_index = 0
    to_delete = []
    for (image, members) in images_with_members:
        print('Image: %s' % image.name)
        for member in members:
            print(' [%s] - %s (%s)' % (member_index, member.id,
                  member.status))
            to_delete.append(member)
            member_index += 1
    snum = raw_input('Enter the number of the member you wish to delete: ')
    if not snum:
        exit()
    try:
        num = int(snum)
    except ValueError:
        print("'%s' is not a valid number." % snum)
        exit()
    if not 0 <= num < member_index:
        print("'%s' is not a valid member number." % snum)
        exit()
    member = to_delete[num]

    imgs.http_log_debug = True
    res = imgs.delete_image_member(member.image_id, member.id)

    print('RES', res)

.. code-block:: ruby

  # N/A

.. code-block:: sh

  curl -i $ENDPOINT/images/{imageId}/members/{memberId} -X DELETE \
      -H "X-Auth-Token: $TOKEN"

  # NOTE: {imageId} and {memberId} are placeholders:
  # Replace them with actual values and do not enclose the values with {}.
