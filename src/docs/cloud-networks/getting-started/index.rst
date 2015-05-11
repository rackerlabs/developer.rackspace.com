.. _quickstart:
===============================
Quickstart for Cloud Networking
===============================

Cloud Networking is used to provide on-demand, scalable, and technology-agnostic network abstraction.

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

networks
  A network provides connectivity to and from instances.

  Tenant networks are networks created by users within tenants, or groups of users. By default, networks created with tenants are not shared among other tenants. Useful network types in this category are vlan (802.1q tagged) and gre (unique id). With the use of the L3 agent and Neutron routers, it is possible to route between GRE-based tenant networks. Without a Neutron router, these networks are effectively isolated from each other (and everything else, for that matter).

subnets
  A subnetwork, or subnet, is a logical, visible subdivision of an IP network. The practice of dividing a network into two or more networks is called subnetting.

  Computers that belong to a subnet are addressed with a common, identical, most-significant bit-group in their IP address. This results in the logical division of an IP address into two fields, a network or routing prefix and the rest field or host identifier. The rest field is an identifier for a specific host or network interface.

ports
  In computer networking, a port is a software construct serving as a communications endpoint in a computer's host operating system. A port is always associated with an IP address of a host and the protocol type of the communication. It completes the destination or origination address of a communications session. A port is identified for each address and protocol by a 16-bit number, commonly known as the port number.

Extensions
==========

* **Security Groups** -- Create and enforce ingress and egress traffic rules per-port

* **Virtual Interfaces** -- Use the Cloud Networks virtual interface extension to create a virtual interface to a specified network and attach that network to an existing server instance. You can attach either an isolated network that you have created or a Rackspace network.

  A virtual interface works in the same way as the network interface card (NIC) inside your laptop. Like a NIC, a virtual interface is the medium through which you can attach a network to your server. You create a virtual interface for a specified network, and the network, which is composed of logical switches, is attached to your server instance through the virtual interface.

  You can create a maximum of one virtual interface per instance per network.

* **Nova-Network** -- Please see Deprecation of Nova Network: http://docs.openstack.org/openstack-ops/content/nova-network-deprecation.html

.. include:: ../../_common/authenticate.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Create Network
--------------
To create a network:

.. include:: samples/create_network.rst

List Networks
--------------
To list networks:

.. include:: samples/list_networks.rst

Create Subnet
--------------
To create a subnet:

.. include:: samples/create_subnet.rst

List Subnets
--------------
To list subnets:

.. include:: samples/list_subnets.rst

Create Port
--------------
To create a port:

.. include:: samples/create_port.rst

List Ports
--------------
To list ports:

.. include:: samples/list_ports.rst

Create Security Group
------------------------
To create a Security Group:

.. include:: samples/create_security_group.rst

Create Security Group Rule
---------------------------
To create a Security Group Rule:

.. include:: samples/create_security_group_rule.rst

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
