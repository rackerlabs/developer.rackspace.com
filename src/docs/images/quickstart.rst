==================================
Getting Started with Cloud Images
==================================

This guide helps you get started with the Rackspace Cloud Images service.

This service enables you to create and
manipulate images, image members, and associated metadata through
a simple Representational State Transfer (REST) web service interface.

This service is closely aligned with the Rackspace Cloud Servers
and Cloud Files services. 

Images are captured from, and applied to, cloud servers
and are stored on, and retrieved from, Cloud Files storage.

Concepts
========

Before you jump in, it helps to understand these core Images service concepts:

* A **server** is a virtual machine, hosted on a physical device in one of our top-notch data centers. 
  Your application, web server, and file system runs on a server.
    
* A **flavor** is a type of hardware configuration that describes the parameters of the available virtual 
  machine images. 
  
  Includes parameters such as CPU, storage, and memory. 
  
  Rackspace offers a set of standard hardware configurations for you to choose from. 
    
* A **network** is the virtual space where your servers live. 

  By default, Rackspace provides the PublicNet network
  that enables public Internet connectivity and the internal ServiceNet 
  network that enables internal connectivity with your other servers.

* An **image** is a collection of specific operating system (OS) files that you use to 
  create or rebuild a server. 
  
  Rackspace offers pre-built Linux and Windows images: 
  Ubuntu 14.04, Red Hat 6, Windows, and so on. 
  
  You can also create custom images, or snapshots, 
  from servers that you have launched. 
  
  You can use custom images for data backups and 
  as *gold* images to launch additional servers. 
  
* A **standard image** is one that has not reached its end of life 
  and that Rackspace supplies for your service 
  level or that is provided specifically for RackConnect customers. 
  
* A **nonstandard image** is one that is imported or exported, end-of-life, shared, 
  not standard for your account service level and not included
  in the subset of images provided for RackConnect customers.

Image entity
------------

An image entity is represented by a JSON-encoded data structure and its raw binary data. An image entity has an identifier (ID) that is guaranteed to be unique within its endpoint. The ID is used as a token in request URIs to interact with that specific image. An image is always guaranteed to have the following attributes: ``id``,
``status``,
``visibility``,
``protected``,
``tags``,
``created_at``,
``file``, and
``self``.

The other attributes defined in the image schema are guaranteed, but are only returned with an image entity if you set them explicitly.

A client can set arbitrarily-named attributes on their images if the image JSON-schema allows it. 
These user-defined attributes appear like any other image attributes.

Image identifiers
-----------------

Images are uniquely identified by a URI that matches this signature::

  {image server location}/v2/images/{imageId}

Where:

* ``{image server location}`` is the resource location of the Cloud Images service that knows about an image.
* ``{imageId}`` is the image identifier, which is a UUID, making it globally unique.

Common image properties
-----------------------

To help end users use your images, you can put additional common properties, or metadata, on your images.

The available properties and their expected values include:


**os_distro**
  The common name of the operating system          
  distribution. 
  
  Must be all lowercase and          
  entered exactly as shown here:                                      

  - arch. Arch Linux                               
  - centos. Community Enterprise Operating System  
  - debian. Debian                                 
  - fedora. Fedora                                 
  - freebsd. FreeBSD                               
  - gentoo. Gentoo Linux                           
  - mandrake. Mandrakelinux (MandrakeSoft)         
  - mandriva. Mandriva Linux                       
  - mes. Mandriva Enterprise Server                
  - msdos. Microsoft Disk Operating System         
  - netbsd. NetBSD                                 
  - netware. Novell NetWare                        
  - openbsd. OpenBSD                               
  - opensolaris. OpenSolaris                       
  - opensuse. openSUSE                             
  - rhel. Red Hat Enterprise Linux                 
  - sled. SUSE Linux Enterprise Desktop            
  - ubuntu. Ubuntu                                 
  - windows. Microsoft Windows                     

**os_version**   
  The distributor-specified OS version.
    
Authenticate
============

You must authenticate before you can complete any Rackspace API interaction.

To authenticate, you need a user name and API key. Find your API key in the control panel on the **Account Settings** page.

Once you've retrieved your details, you pass them into the client:

.. include:: samples/authentication.rst

Use images
==========

To see which images are available, you can list all images or get details for a specified image.
Then, you can update an image and use image tasks to import and export images.

List images and get image details
---------------------------------

An image, or operating system, forms the basis of your server. 
Each image has a unique ID, which you can use to get more details about the image.

To list images:

.. include:: samples/list_images.rst

Once you know the image ID, you can get more details about the image like this:

.. include:: samples/get_image.rst

Once you've found the desired operating system, and its ID, you can move on to picking your hardware.

Update an image
---------------

.. include:: samples/update_image.rst

Import and export images
------------------------

An image task is a request to perform an asynchronous image-related operation, such as importing or exporting an image. The request results in the creation of a disposable task resource that can be polled for information about the status of the operation.

After you initiate an image import or export, poll the status of the created task by using the instructions in Section 2.1.6, Get details for a task. When the task resource reaches a final status of success or failure, the poll response includes an expiration date and time stamp. After that expiration date and time, the disposable task resource itself expires and is subject to deletion. 
However, the result of the task, such as an imported or exported image, does not expire.

.. include:: samples/import_image.rst

.. include:: samples/export_image.rst

Share images
============

The Cloud Images API enables you and others to share your custom images. 
The following examples show some basic image sharing operations. 

Add an image member
-------------------

.. include:: samples/create_image_member.rst

List image members
------------------

.. include:: samples/list_image_members.rst

Get image member details
------------------------

.. include:: samples/get_image_member.rst

Update an image member
----------------------

.. include:: samples/update_image_member.rst

Delete an image member
----------------------

.. include:: samples/delete_image_member.rst

