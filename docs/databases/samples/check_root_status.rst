.. code-block:: php

    if (true === $instance->isRootEnabled()) {
        // yes it is
    }

.. code-block:: java

// Get the instanceApi
InstanceApi instanceApi = troveApi.getInstanceApiForZone("IAD");

// Gets the rooted status for an instance ID.
boolean rootedStatus = instanceApi.isRooted(myInstanceId);