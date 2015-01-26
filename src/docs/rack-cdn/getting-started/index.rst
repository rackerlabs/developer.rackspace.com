.. _quickstart:
==================================
Quickstart for Rack CDN
==================================

Rack CDN provides CDN-as-a-service for Rackspace:

* REST API for CDN service provisioning
* Multi-tenant
* Integrated with Keystone for Authentication
* Integrated with Designate for dynamic CNAMEing
* Support for CDN providers (Fastly, MaxCDN, CloudFront, Akamai, Edgecast)

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

service
    The Service resource is the central resource in the REST API. It represents your application whose assets are to be distributed.

Authenticate to gain access to the service
==========================================
To use this service you have to authenticate first. To do this, you will need your Rackspace username, and one of the following:

* your Rackspace account password
* your Rackspace API key

Your username and password are the ones you use to login to the Cloud Control Panel at http://mycloud.rackspace.com/.

To find your API key, first navigate to the Cloud Control Panel, then click on your username at the top right corner, and then finally click on Account Settings. You will be taken to a page that shows your settings. Under Login Details, you can show or reset your API key.

Once you have these pieces of information, you can pass them into the SDK:

.. include:: samples/authentication.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Create service
------------
You can create the service in the Rackspace cloud:

.. include:: samples/create_service.rst

List services
-----------
To see the services you have already deployed in a given region:

.. include:: samples/list_services.rst

Get service data
--------------
To inspect a single service's detail data:

.. include:: samples/get_service_data.rst

Update service
------------
To update or modify an existing service:

.. include:: samples/update_service.rst

Delete service
------------
To delete a service and destroy all resources the service has provisioned:

.. include:: samples/delete_service.rst

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
