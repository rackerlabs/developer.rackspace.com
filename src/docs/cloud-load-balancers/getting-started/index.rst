.. _quickstart:
===================================
Quickstart for Cloud Load Balancers
===================================

The Rackspace Cloud Load Balancers service provides a means to protect your infrastructure
by distributing traffic efficiently.

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

monitor
    A configurable feature of each load balancer.
    It is used to determine whether or not a back-end node is usable for processing a request.
    It is not related to the Cloud Monitoring service.

node
    A back-end device providing a service on a specified IP and port.

.. include:: ../../_common/authenticate.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Set up your load balancer
-------------------------
Create a load balancer
~~~~~~~~~~~~~~~~~~~~~~
When creating a load balancer, there are a few mandatory parameters that you need
to provide. You need to specify a **name**, a **protocol** (such as HTTP, FTP,
SMTP) and the types of IP you want to use (v4 or v6). Here is an example:

.. include:: samples/create_lb.rst

Once this operation is executed, the API will asynchronously provision a load
balancer based on your configuration. Some SDKs allow you to query its progress:

.. include:: samples/query_lb_progress.rst

Select the servers you want as nodes
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
For a load balancer to distribute traffic, it needs to be aware of the
servers or databases you want guarded. These are known as nodes.

.. include:: samples/select_servers.rst

The above sample retrieves two cloud servers using their unique IDs.
You can obtain a server's ID by following the steps outlined in the
`Quickstart for Cloud Servers </docs/cloud-servers/getting-started/>`_
for retrieving servers based on arbitrary properties.

Now that you have your servers, you can create your nodes and add them to the load
balancer:

.. include:: samples/create_nodes.rst

Monitor the health of your nodes
--------------------------------
The load balancing service includes a health monitor that periodically checks
your nodes to ensure they are responding correctly. If a node is not responding,
it is removed from rotation until the health monitor determines that it is
functional. In addition to periodic checking, a check is performed against every
node when added to your Load Balancer, ensuring that the node can safely service traffic.

To enable health monitoring on your load balancer, you'll need to set:

* Type - ``CONNECT`` is used to monitor connections
* Delay - Minimum number of seconds to wait before executing the monitor
* Timeout - Maximum duration in seconds to wait for a connection establishment before timing out
* Attempts before deactivation - Number of monitor failures to tolerate before removing a node from rotation

.. include:: samples/create_health_monitor.rst

To view the status of a health monitor:

.. include:: samples/get_health_monitor.rst

Only one health monitor can be enabled on a load
balancer at a time.

Set limits
----------
The connection throttling feature imposes limits on the number of connections per IP address to help mitigate malicious or abusive traffic to your applications. You can set the:

* Max connection rate - Maximum connections from a single IP within the supplied rate interval (frequency in seconds)
* Max connections - Maximum simultaneous connections to allow from a single IP within the rate interval
* Min connections - Minimum amount of connections per IP before doing any throttling
* Rate interval - the frequency in seconds at which the max connections are assessed

.. include:: samples/set_throttling.rst

Blacklist IP addresses
----------------------
Your access list allows fine-grained network access controls to be applied to a
load balancer. A single IP address, multiple IP addresses, or entire network
subnets can be added.

Items that are configured with the ``ALLOW`` type will take precedence over all
other traffic, and items with the ``DENY`` type will have their traffic rejected.

.. include:: samples/blacklist_ips.rst

Cache content for performance
------------------------------
When content caching is enabled, recently-accessed files are stored on the load
balancer for easy retrieval by web clients. Content caching improves the
performance of high traffic web sites by temporarily storing data that was
recently accessed. While it's cached, requests for that data will be served by
the load balancer, which in turn reduces load off the back end nodes. The result
is improved response times for those requests and less load on the web server.

.. include:: samples/enable_content_caching.rst

Use error pages
---------------
An error page is the HTML file that is shown to your users when accessing a
load balancer node that is offline or otherwise unavailable. By default, every
provisioned load balancer is configured with a default error page.
However, you can create a custom error page for load balancers that use the HTTP protocol:

.. include:: samples/set_custom_error_page.rst

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
