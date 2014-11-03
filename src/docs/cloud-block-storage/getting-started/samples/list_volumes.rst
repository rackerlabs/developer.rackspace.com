.. code-block:: csharp

  IEnumerable<Volume> volumeList = cbsProvider.ListVolumes(region: "{region}");

.. code-block:: go

  volumes.List(client).EachPage(func(page pagination.Page) (bool, error) {
    volumeList, err := volumes.ExtractVolumes(page)
    for _, v := range volumeList {
      // ...
    }
    return true, nil
  })

.. code-block:: java

  VolumeApi volumeApi = cinderApi.getVolumeApiForZone("{region}");

  List<Volume> volumes = volumeApi.listInDetail().toList();

.. code-block:: javascript

  client.getVolumes(function(err, volumes) {
    if (err) {
      // TODO handle as appropriate
    }

    // TODO use your list of volumes
  });

.. code-block:: php

  $volumes = $volumeService->volumeList();

.. code-block:: python

  volumes = cbs.list()

.. code-block:: ruby

  volumes = @client.volumes.all

.. code-block:: sh

  curl -X GET $ENDPOINT/volumes \
    -H "X-Auth-Token: $TOKEN" \
    -H "Content-Type: application/json" | python -m json.tool
