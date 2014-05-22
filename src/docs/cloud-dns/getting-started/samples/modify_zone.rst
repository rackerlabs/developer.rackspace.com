.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

  myZone.emailAddress = 'changed@domain.com';
  rackspace.updateZone(myZone, function (err) {
    if (err) {
      console.dir(err);
      return;
    }

    // TODO The zone was successfully modified.
  });

.. code-block:: php

  // Specify your changes
  $changes = array(
      'email' => 'changed@domain.com',
      'ttl'   => 3600
  );

  $domain->update($changes);

.. code-block:: python

  domain.update(emailAddress="changed@domain.com")

.. code-block:: ruby

  zone.email = 'changed@domain.com'
  zone.ttl = 3600
  zone.save

.. code-block:: sh

.. code-block:: sh

  curl -X PUT -d \
    '{
        "comment" : "{updatedComment}",
        "ttl" : 300,
        "emailAddress" : "{updatedEMailAddress}"
    }' \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" \
    $ENDPOINT/domains | python -m json.tool
