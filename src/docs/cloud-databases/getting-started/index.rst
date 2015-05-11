.. _quickstart:
==============================
Quickstart for Cloud Databases
==============================

This guide introduces you to Rackspace Cloud Databases. Cloud Databases is a MySQL relational database service that allows you to easily provision instances without having all the maintenance overhead. You can also back up your databases on a regular basis and set up monitoring functionality to track usage.

Concepts
========

To use this service effectively, you should understand how these key ideas are used in this context:

flavor
    A flavor is an available hardware configuration for a database instance.
    Each flavor has a unique combination of memory capacity and priority for CPU time.

instance
    An isolated environment in which your databases run. This is similar
    to the virtualized instances used by Cloud Servers,
    but database instances are optimized for database performance.

.. include:: ../../_common/authenticate.rst

Use the API
===========

Some of the basic operations you can perform with this API are described below.

Work with instances
-------------------

Two common issues that developers have with a traditional RDS relate to peformance: increasing the amount of IO transactions per second, for example, or reducing application latency. Because of this, our instances were designed from the ground up with two primary features in mind:

* *Performance*. By using container-based virtualization, instances are allocated the resources they truly need and there is no compute power waste such as with traditional virtualization. As a result, things are faster and more efficient.

* *Reliability*. We use fault-tolerant components, such as: RAID levels for individual drives, multi-tenant environments across different nodes, as well as dedicated power supplies and network adapters. This means you have redundancy both from a hardware level and a software level.

Create an instance
~~~~~~~~~~~~~~~~~~

To create an instance, you must decide on the hardware type, or flavor, that you want to use:

.. include:: samples/get_flavor.rst

Alternatively, you can traverse through the standard list Rackspace provides:

.. include:: samples/list_flavors.rst

Once you have this flavor, you can use it to create your instance:

.. include:: samples/create_instance.rst

Resize an instance
~~~~~~~~~~~~~~~~~~

As with creating an instance, in order to resize one, you need to know which flavor to use. Once you've decided on a new flavor, you can use it to resize your running instance:

.. include:: samples/resize_instance.rst

Restart an instance
~~~~~~~~~~~~~~~~~~~

In order to restart your instance:

.. include:: samples/restart_instance.rst

Enable root user
~~~~~~~~~~~~~~~~

Although you create a default user when creating a database instance, sometimes it might be necessary to execute operations as the root user. To do so, you will need to enable root:

.. include:: samples/enable_root_user.rst

This operation returns the root password for your use. Note that changes you make as a root user may cause detrimental effects to the database instance and unpredictable behavior for API operations.

If you're not sure whether you've already enabled the root user, you can easily query whether root is enabled or not:

.. include:: samples/check_root_status.rst

Create database
~~~~~~~~~~~~~~~

This is a simple MySQL database that you interact with normally. Creating one is very easy:

.. include:: samples/create_db.rst

Create user
~~~~~~~~~~~

To allocate a new user to a database:

.. include:: samples/create_user.rst

The user is granted all privileges on this database. Please bear in mind that ``root`` is a reserved name and cannot be used.

Backups
-------

Create backup
~~~~~~~~~~~~~

To create a backup for your instance:

.. include:: samples/create_backup.rst

When creating a backup, there are some things to bear in mind:

* During the backup process, MyISAM writes will be disabled. InnoDB is completely unaffected.
* You also cannot add or delete databases or users during this process.

Restore instance from backup
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

To restore your instance from a backup:

.. include:: samples/restore_backup.rst

More information
================

This Quickstart is intentionally very brief, demonstrating only a few basic operations. If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
