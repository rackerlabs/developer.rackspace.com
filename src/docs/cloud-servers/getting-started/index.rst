.. _quickstart:
============================
Quickstart for Cloud Servers
============================

Rackspace Cloud Servers allows you to allocate and deallocate compute resources.
It is based on OpenStack Compute (Nova), a community-led open-source platform.

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

server
    A computer that provides explicit services to the client software running on that system.
    A server is a virtual machine (VM) instance in the Cloud Servers environment.
    To create a server, you must specify a name, flavor reference, and image reference.

image
    The type of operating system you want to use.
    You can choose from pre-defined images or create your own custom images.

flavor
    A resource configuration for a server.
    Each flavor is a unique combination of disk, memory, vCPUs, and network bandwidth.
    You can choose from pre-defined flavors.

network
    The virtual space where your servers live. Rackspace has two default networks:
    PublicNet, which is the Internet; ServiceNet, which is our internal network.
    Although you can create as many isolated networks as you want,
    the default configuration is for servers to be connected to
    both PublicNet (for public Internet connectivity) and ServiceNet (for internal connectivity with your other servers).

.. include:: ../../_common/authenticate.rst

.. include::  samples/authentication.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Set up your first server
------------------------
Once you've finished your initial setup, you can begin the process of creating your first server.

To do this, you need to decide which **Image** and **Flavor** you want to use.

Choose operating system
~~~~~~~~~~~~~~~~~~~~~~~
An image, or operating system, forms the basis of your server.
Each image has a unique ID, which is used to retrieve more details from the API.
If you already know the ID, you can retrieve more details about the image:

.. include:: samples/get_image.rst

Alternatively, you can traverse through the list of images:

.. include:: samples/list_images.rst

Once you've found the appropriate operating system and its ID, you can move on to picking your hardware.

Choose hardware configuration
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Flavors, or hardware configurations, dictate how powerful your servers are.
Like images, each flavor has its own UUID.
If you already know which flavor to use, you can retrieve its details:

.. include:: samples/get_flavor.rst

Alternatively, you can traverse through the standard list Rackspace provides:

.. include:: samples/list_flavors.rst

Build server
~~~~~~~~~~~~
Now that you have an image ID and flavor ID, you can create your server:

.. include:: samples/create_server.rst

This is an asynchronous operation, meaning that it will _not_ block your request while the build process runs.
It will provision your VM behind the scenes.
When the build completes, it places your server in an ``ACTIVE`` state. At this point, the server is available for you to use.

Some SDKs allow you to check on the status of the build:

.. include:: samples/query_server_build.rst

Delete server
-------------
To permanently delete a server:

.. include:: samples/delete_server.rst

Manage keypairs
----------------
By default, servers use password-based authentication.
When a server is created, the HTTP response contains a root password that is required for all subsequent SSH connections. You do have the option, however, of using keypairs instead.

Register keypair
~~~~~~~~~~~~~~~~
To use keypair-based authentication, the API needs to know about it. You have two options: upload your existing key, or have the API create a new one for you. We'll cover both.

Upload keypair:

.. include:: samples/upload_existing_keypair.rst

or have the API create one:

.. include:: samples/create_new_keypair.rst

Use keypairs
~~~~~~~~~~~~
If you want an existing server to use keypair-based auth, you will need to configure this yourself.

However, getting new servers to acknowledge keypairs is easy. You just need to supply the name of the pre-existing keypair when you do the create server operation, like this:

.. include:: samples/create_server_with_keypair.rst

This server, after being spun up, will respond to that keypair.

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
