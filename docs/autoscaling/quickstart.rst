.. _quickstart:

Quickstart Guide
=================

Auto Scaling
---------------

Rackspace Auto Scale is a service that lets you configure automated scaling of resources in response to an increase or decrease in overall workload based on user-defined policies. You can set up a schedule for launching Auto Scale or define an event that is triggered by Cloud Monitoring. You can also specify a minimum and maximum number of cloud servers, the amount of resources that you want to increase or decrease, and the thresholds in Cloud Monitoring that trigger the scaling activities.

To use Auto Scale, you define a scaling group consisting of cloud servers and cloud load balancers. Then you define policies, either schedule-based or monitoring-based. For monitoring-based policies, you define cloud monitoring alerts to watch the group's activity, and you define scaling rules to change the scaling group's configuration in response to alerts. For schedule-based policies, you simply set a schedule. Because you can change a scaling group's configuration in response to changing workloads, you can begin with a minimal cloud configuration and grow only when the cost of that growth is justified.

Assumptions
------------
This guide assumes the reader has working familiarity with (at the very least):
    
    * Cloud Servers
    * Cloud Monitoring


Concepts
===========

Scaling Groups
---------------

The scaling group is at the heart of an Auto Scale deployment. The scaling group specifies the basic elements of the Auto Scale configuration. It manages how many servers can participate in the scaling group. It also specifies information related to load balancers if your configuration uses a load balancer.

When you create a scaling group, you specify the details for the following two configurations:

  * Group Configuration: Outlines the basic elements of the Auto Scale configuration. The group configuration manages how many servers can participate in the scaling group. It sets a minimum and maximum limit for the number of entities that can be used in the scaling process. It also specifies information related to load balancers

  * Launch Configuration: Creates a blueprint for how new servers will be created. The launch configuration specifies what type of server image will be started on launch, what flavor the new server is, and which load balancer the new server connects to.


Webhooks and Capability URLs
-----------------------------

Auto Scale uses webhooks to initiate scaling events. A webhook is an industry-standard protocol for sending events between systems; for Auto Scale, they are used to execute policies. A webhook consists of an HTTP callback that is triggered by some user-defined event, such as an alarm that is set through Cloud Monitoring or another monitoring service.

Capability URLs are URLs that give authorization for a certain action or event. If you know the URL, you have access to it and you can use the URL to trigger a specific event. Capability URLs are usually long, and random, and cannot be guessed by a user.


Scaling Policies
-----------------

Auto Scale uses policies to define the scaling activity that will take place, as well as when and how that scaling activity will take place. Scaling policies specify how to modify the scaling group and its behavior. You can specify multiple policies to manage a scaling group.

You can create two kinds of Auto Scale policies:

  * Policies that trigger Auto Scale activities through a webhook.

  * Policies that trigger Auto Scale activities based on a schedule.


API Operations
===============

Scaling Groups 
-----------------

You can create an autoscaling group via SDK or API as shown below:

.. include:: samples/create_scaling_group.rst

To list autoscaling groups you already have setup in any given region, consider the following code:

.. include:: samples/list_scaling_groups.rst

If you want to see the details of a scaling group, see the code below:

.. include:: samples/get_scaling_group_details.rst

If you want to alter details for a scaling group, see code samples below:

.. include:: samples/update_scaling_group_details.rst

Additionally, if you want to inspect the current state of an autoscaling group, do as follows:

.. include:: samples/get_scaling_group_state.rst

And, in the event that you want to delete an autoscaling group, follow the code sample below:

.. include:: samples/delete_scaling_group.rst


Policies
----------

In order to create an autoscaling policy consider the code samples below:

.. include:: samples/create_scaling_policy.rst

Then, to list all autoscaling policies in a region you can do as follows:

.. include:: samples/list_scaling_policies.rst

Alternatively, the code samples below show how to go about if you want to inspect details of a specific autoscaling policy:

.. include:: samples/get_scaling_policy_details.rst

If you want to alter details for a policy, see code samples below:

.. include:: samples/update_scaling_policy.rst

To execute a particular autoscaling policy see code samples below:

.. include:: samples/execute_scaling_policy.rst

Finally, if you want to delete an autoscaling policy, consider the following:

.. include:: samples/delete_caling_policy.rst


Webhooks
---------

The first task as with most other API operations in this guide is to create an entity. In this case, to create a Webhook follow the code below:

.. include:: samples/create_scaling_webhook.rst

The next step is to inspect autoscaling Webhook details:

.. include:: samples/get_scaling_webhook_details.rst

Alternatively, you could list all available autoscaling Webhooks in a particular region as follows:

.. include:: samples/list_scaling_webhooks.rst

If you want to alter details about an existing Webhook, see code samples below:

.. include:: samples/update_scaling_webhook.rst

Finally, to delete an autoscaling Webhook, you can do as suggested in the code samples below:

.. include:: samples/delete_scaling_webhook.rst
