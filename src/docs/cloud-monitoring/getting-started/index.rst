.. _quickstart:
===============================
Quickstart for Cloud Monitoring
===============================

Cloud Monitoring helps you keep a keen eye on all of your resources. You can monitor activity on websites, web servers, routers, load balancers, and more.

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

agent
    A monitoring daemon that resides on the server being monitored.
    The agent gathers metrics based on agent checks and pushes them to Cloud Monitoring.

alarm
    Contains a set of rules that determine when the monitoring system sends a notification.
    You can create multiple alarms for the different check types associated with an entity

check
    Contains the specific details of how you are monitoring the entity, such as
    which components of the entity to monitor, the monitoring frequency, and how many monitoring zones are launching the check.

entity
    The object or resource that you want to monitor.
    An entity is commonly a web server, but it might also be a website, a web page, or a web service.

monitoring zone
    The launch point of a check; a monitoring zone is a geographical region. When you create a check, you specify which monitoring zone(s) you want to launch the check from.

notification
    An informational message sent to one or more addresses by the monitoring system when an alarm is triggered.

notification plan
    Contains a set of notification rules to execute when an alarm is triggered. A notification plan can contain multiple notifications for Critical, Warning, and Ok states.


.. include:: ../../_common/authenticate.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Workflow
--------
* Create an entity to represent the item you want to monitor. For example, the entity might represent a website.

* Attach a predefined check to the entity. For example, you could use the PING check to monitor your website's public IP address.

* Create notifications and notification plans. A notification lets you define an action which Cloud Monitoring uses to communicate with you when a problem occurs. Notification plans allow you to organize a set of several notifications, or actions.

* Create alarms to specify which notifications get triggered.

Create entity
-------------
To begin monitoring a resource, create an entity that represents the resource in the monitoring system:

.. include:: samples/create_entity.rst

List monitoring zones
----------------------
Before creating a check for the new entity, choose which monitoring zones to run the check from.
Cloud Monitoring is divided into different monitoring zones.
You may want to have a single server monitored from several monitoring zones to reduce the risk of false alarms and check the response time from different locations around the world.
To see which zones are available to you:

.. include:: samples/list_monitoring_zones.rst

Delete entity
-------------
To delete a monitoring entity:

.. include:: samples/delete_entity.rst

Note: you cannot delete a monitoring entity until you have removed all its checks and alarms have been removed.

Create check
------------
Any entity that you create can have many checks, each monitoring a different aspect of the entity:

.. include:: samples/create_check.rst

List checks
-----------
To list all the monitoring checks for a given entity:

.. include:: samples/list_checks.rst

Delete check
------------
To remove a check:

.. include:: samples/delete_check.rst

Notifications and alarms
------------------------
In most cases you, and perhaps several people on your team, will be interested in multiple alerts.
Cloud Monitoring lets you set up notification plans that can be shared between multiple alerts.

Create notification
~~~~~~~~~~~~~~~~~~~
To create a notification plan:

.. include:: samples/create_notification.rst

Create alarm
~~~~~~~~~~~~
To create an alarm for an entity:

.. include:: samples/create_alarm.rst

Delete alarm
~~~~~~~~~~~~
To delete an alarm:

.. include:: samples/delete_alarm.rst

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
