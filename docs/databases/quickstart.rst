====================================
Getting started with Cloud Databases
====================================

This guide will introduce Rackspace Cloud Databases and familiarize you with some common functionality. Cloud Databases is a MySQL relational database service
that allows you to easily provision instances without having all the maintenance overhead. You can also backup your databases on a regular basis,
as well as setting up monitoring functionality to track usage.

Authentication
==============

Before we can use the API, we need to authenticate and create an instance of the appropriate API client.

.. include:: samples/authentication.rst


Working with instances
======================

An instance is an isolated environment which your databases run on. It is similar
to the virtualized instances found with Cloud Servers (they both utilize
OpenStack Nova), but database instances are optimized for optimal database performance.

Two common issues that developers have with traditional RDS relate to peformance:
increasing the amount of IO transactions per second, for example, or reducing
application latency. Because of this, our instances were re-designed from the
ground-up with two primary features in mind:

* *Performance*. By using Container-based virtualization, instances are allocated the resources they truly need and there is no compute power waste like there is with traditional virtualization. As a result, things are faster and more efficient.

* *Reliability*. We use fault-tolerant components, such as: RAID levels for individual drives, multi-tenant environments across different nodes, as well as dedicated power supplies and network adapters. This means you have redundancy both from a hardware level and a software level.

Create a new instance
---------------------

To create a new instance you first need to decide on the hardware type, or Flavor, you want to use:

.. include:: samples/get_flavor.rst

Alternatively, you can traverse through the standard list Rackspace provides:

.. include:: samples/list_flavors.rst

Once you have this Flavor, you can use it to create your instance:

.. include:: samples/create_instance.rst

Resizing
--------

As with creating an instance, in order to resize one, you need to know which
Flavor to use. Once you've decided on a new Flavor, you can use it to resize
your running instance:

.. include:: samples/resize_instance.rst

Enable root user
----------------

Although you create a default user when creating a database instance, sometimes
it might be necessary to execute operations as the root user. To do so, you will
need to enable root:

.. include:: samples/enable_root_user.rst

This operation will then return the root password for your use. If you're not sure
whether you've already done this, you can easily query whether root is enabled or not:

.. include:: samples/check_root_status.rst

Restarting
----------

In order to restart your instance:

.. include:: samples/restart_instance.rst


Create a database
=================

This is a simple MySQL database that you interact with normally. Creating one
is very easy:

.. include:: samples/create_db.rst


Create a new user
=================

To allocate a new user to a database, you need to run:

.. include:: samples/create_user.rst

The user is granted all privileges on this database. Please bear in mind that ``root``
is a reserved name and cannot be used.


Backups
=======

Create backup
-------------

In order to create a backup for your instance, just run:

.. include:: samples/create_backup.rst

When creating a backup, there are some things to bear in mind:

* During the backup process, MyISAM writes will be disabled. InnoDB is completely unaffected.
* You also cannot add or delete databases or users during this process.

Restore instance from backup
----------------------------

Once you have a backup, you can use it to restore your instance:

.. include:: samples/restore_backup.rst