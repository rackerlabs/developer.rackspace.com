.. code-block:: csharp

.. code-block:: java

.. code-block:: javascript

  file.metadata = {
    'some-key': 'some-value'
  };

  file.updateMetadata(function(err) {
    if (err) {
      // TODO handle as appropriate
    }
  });

.. code-block:: php

.. code-block:: python

  obj.change_content_type("application/json")

  # Generic metadata can be set with:
  obj.set_metadata({"some-key": "some-value"})

.. code-block:: ruby

  file.content_type = 'application/json'
  file.save

  # Generic metadata can be set with:
  file.metadata['some-key'] = 'some-value'
  file.save

.. code-block:: sh
