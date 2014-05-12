==================================
Getting Started with Cloud Servers
==================================

This guide will provide an introduction to Rackspace Cloud Servers, describing what it is and what it can do. In a nutshell, it is a service that allows you to allocate and deallocate compute resources. It is based on OpenStack Compute (Nova), a community-led open-source platform.

The compute service has a few core concepts which need to be understand before plunging in. These are:

1. Servers
2. Flavors
3. Images
4. Networks

The concepts
============

Server
------

A Server is a virtual machine, hosted on a physical device in one of our top-notch data centers. This is where your application, web server, and filesystem sits.

Image
-----

An Image is the type of operating system you want to use. We offer a whole bunch of Linux or Windows images for you to choose from: Ubuntu 12.04, RedHat 6, Windows, etc. You can also create your own custom images based on personal configuration, but this is not within the scope of this tutorial.

Flavor
------

A Flavor is a type of hardware configuration. Like images, we offer a list of standard hardware configurations for you to choose from. You might want a '2GB RAM flavor' for a smaller project, or a '32GB RAM performance flavor' for more intense computational needs.

Network
-------

A Network is the virtual space where your Servers live. Rackspace has two default Networks that you need to be aware of: PublicNet, which is the Internet; and ServiceNet, which is our internal network.

Although you can create as many isolated networks as you like, the standard default is for servers to be connected to both PublicNet (for public Internet connectivity) and ServiceNet (for internal connectivity with your other servers).

Authenticating
==============

Okay, we've covered the concepts, so now we need to deal with authentication. Authentication is a required step for all API interactions.

There are three required attributes for using Rackspace Cloud Servers with an SDK:

* your rackspace username
* API key `(how to find your API key) <http://www.rackspace.com/knowledge_center/article/rackspace-cloud-essentials-viewing-and-regenerating-your-api-key>`_
* region `(available regions) <http://developer.rackspace.com/regions>`_


Once you've retrieved your details, you pass them into the client:

.. include:: samples/authentication.rst

Setting up your first server
============================

Once you've finished your initial setup, you can begin the process of creating your first server.

In order to do this, you need to decide which **Image** and **Flavor** you want to use.

Task 1: Finding an Operating System
-----------------------------------

An Image, or operating system, will form the basis of your server. Each one has a unique ID, which is used to retrieve more details from the API. If you already know the ID, you can retrieve more details about the image like this:

.. include:: samples/get_image.rst

Alternatively, you can traverse through the list:

.. include:: samples/list_images.rst

Once you've found the perfect operating system, and its ID, you can move on to picking your hardware.

Task 2: Finding the right hardware
----------------------------------

Flavors, or hardware configurations, will dictate how powerful your servers are. Like images, each flavor has its own UUID. If you already know which flavor to use, you can retrieve its details like this:

.. include:: samples/get_flavor.rst

Alternatively, you can traverse through the standard list Rackspace provides:

.. include:: samples/list_flavors.rst

Task 3: Putting the pieces together
-----------------------------------

Now that we have our image ID and flavor ID, you can create your server:

.. include:: samples/create_server.rst

This is an asyncronous operation, meaning that it will _not_ block your request until the process is complete. It will provision your VM behind the scenes, allowing you to optionally query its status. Once the build reaches a ``COMPLETE`` state, it will be available for you to use.

Some SDKs allow you to check on the status of the build:

.. include:: samples/query_server_build.rst

Deleting your server
--------------------

If you've finished working with your server, you can permanently delete it like so:

.. include:: samples/delete_server.rst

Managing key pairs
==================

By default, servers will use password-based authentication. When a server is created, the HTTP response will contain a root password that is required for all subsequent SSH connections. You do have the option, however, of using keypairs instead.

Registering your keypair
------------------------

In order to use keypair-based authentication, the API needs to know about it. You have two options: upload your existing key, or have the API create a new one for you. We'll cover both.

In order to upload an existing keypair:

.. include:: samples/upload_existing_keypair.rst

In order to have the API create on for you:

.. include:: samples/create_new_keypair.rst

Using keypairs
--------------

If you want an existing server to use keypair-based auth, you will need to configure this yourself.

However, getting new servers to acknowledge keypairs is easy. You just need to supply the name of the pre-existing keypair when you do the create server operation, like this:

.. include:: samples/create_server_with_keypair.rst

This server, after being spun up, will respond to that keypair.
