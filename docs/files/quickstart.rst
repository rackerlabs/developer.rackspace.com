.. _quickstart:

Quickstart Guide
=================

Cloud Files
---------------

Rackspace offers a flexible and scalable solution to object storage through its Cloud Files service. The intent of this guide to get you up and running with Cloud Files as quick as possible. If your case is not covered in the samples below, you might want to check out SDK-specific documentation for much deeper treatment of the subjects below as well as use cases purposely left out.

Authentication and client creation
----------------------------------
First, we need to authenticate and create an instance of the appropriate API client.

.. include:: samples/auth_client.rst

Create Container
-----------------

Before we can upload any objects to Cloud Files, we first must create a container. In its simplest form a container can be created as follows:

.. include:: samples/create_container.rst
    

Set Container as CDN
----------------------

Once we have created a container, we can set it as CDN (Content Delivery Network). That implies that any objects within the container will be publicly readable. To set a container as CDN can be accomplished as follows:

.. include:: samples/set_container_as_cdn.rst
  

Unset Container as CDN
-----------------------

If you no longer wish to have your container set as CDN, you may do as as described in the code below:

.. include:: samples/unset_container_as_cdn.rst


Delete Container
-----------------

In that event that you need to delete a container, you can do so as shown below (please note that for data safety reasons you may not delete a container until **all** objects within it have been deleted):

.. include:: samples/delete_container.rst


Uploading Objects to Container
-------------------------------

Once we have a container, we can then upload objects to it in the following manner:

.. include:: samples/upload_object.rst


Change Object Metadata
------------------------

Once you have an object uploaded to a container it is possible to change its metadata in-place. For instance, you can change its content-type so that when delivered to requesting clients it can be treated accordingly. The code snippet below shows how:

.. include:: samples/change_object_metadata.rst

Getting Objects
---------------

We (and clients) can retrieve objects from Cloud Files in several ways. Below we explain the most common ways.

Get Object via Temporary URL
-----------------------------

To retrieve an object via temporary URL, see the example below:

.. include:: samples/get_object_temp_url.rst


Get Object Directly (i.e. download via SDK)
---------------------------------------------

You may also download objects directly into your local storage drive via SDK download. To do so, see the following code example:

.. include:: samples/get_object_sdk.rst


Get Object via CDN URL
-------------------------

Another common way of getting objects is through CDN URLs, which, unlike temporary URLs, never expire and may considered publicly-accessible "permalinks" 

.. include:: samples/get_object_cdn.rst

Delete Objects
---------------

To delete objects, consider the code below:

.. include:: samples/delete_object.rst


