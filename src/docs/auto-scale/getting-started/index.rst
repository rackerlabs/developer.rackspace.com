.. _quickstart:
=========================
Quickstart for Auto Scale
=========================
Rackspace Auto Scale is a service that lets you configure automated scaling of resources in response to an increase or decrease in overall workload based on user-defined policies. You can set up a schedule for launching Auto Scale or define an event that is triggered by Cloud Monitoring. You can also specify a minimum and maximum number of cloud servers, the amount of resources that you want to increase or decrease, and the thresholds in Cloud Monitoring that trigger the scaling activities.

To use Auto Scale, you define a scaling group consisting of cloud servers and cloud load balancers. Then you define policies, either schedule-based or monitoring-based. For monitoring-based policies, you define cloud monitoring alerts to watch the group's activity, and you define scaling rules to change the scaling group's configuration in response to alerts. For schedule-based policies, you simply set a schedule. Because you can change a scaling group's configuration in response to changing workloads, you can begin with a minimal cloud configuration and grow only when the cost of that growth is justified.

Assumptions
------------
This guide assumes the reader has working familiarity with (at the very least):

* `Cloud Servers </docs/cloud-servers/getting-started/>`_
* `Cloud Monitoring </docs/cloud-monitoring/getting-started/>`_

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

capability URL
    URL that gives authorization for a certain action or event.
    If you know the URL, you have access to it and you can use the URL to trigger a specific event.
    Capability URLs are usually long and random and cannot be guessed by a user.

scaling group
    Specifies the basic elements of the Auto Scale configuration.
    It manages how many servers can participate in the scaling group.
    It also specifies information related to load balancers if your configuration uses a load balancer.
    When you create a scaling group, you specify the details for the group configuration and the launch configuration.

scaling policy
    Defines the scaling activity that will take place, as well as when and how that scaling activity will take place.
    You can define a scaling policy to trigger Auto Scale activities through a webhook or based on a schedule.
    You can specify multiple policies to manage a scaling group.

webhook
    Industry-standard protocol for sending events between systems; for Auto Scale, webhoooks are used to execute policies.
    A webhook consists of an HTTP callback that is triggered by some user-defined event,
    such as an alarm that is set through Cloud Monitoring or another monitoring service.

.. include:: ../../_common/authenticate.rst

.. include::  samples/authentication.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Scaling Groups
-----------------
Create an autoscaling group.

.. include:: samples/create_scaling_group.rst

List the autoscaling groups you have setup.

.. include:: samples/list_scaling_groups.rst

Get details of the scaling group you created.

.. include:: samples/get_scaling_group_details.rst

Alter the details of the scaling group.

.. include:: samples/update_scaling_group_details.rst

Look into the state of the autoscaling group.

.. include:: samples/get_scaling_group_state.rst

Delete the autoscaling group.

.. include:: samples/delete_scaling_group.rst

Policies
----------
To create an autoscale policy that will

* Add one server
* Only allow another scaling actions to happen after a 360 second cool down

.. include:: samples/create_scaling_policy.rst

To list all autoscaling policies in a region:

.. include:: samples/list_scaling_policies.rst

To inspect details of a specific autoscaling policy:

.. include:: samples/get_scaling_policy_details.rst

To alter details for a policy:

.. include:: samples/update_scaling_policy.rst

To execute a particular autoscaling policy:

.. include:: samples/execute_scaling_policy.rst

To delete an autoscaling policy:

.. include:: samples/delete_scaling_policy.rst

Webhooks
---------
To trigger autoscale actions, you can create a webhook:

.. include:: samples/create_scaling_webhook.rst

To inspect the autoscaling webhook details:

.. include:: samples/get_scaling_webhook_details.rst

To list all available autoscaling webhooks in a particular region:

.. include:: samples/list_scaling_webhooks.rst

To alter details about a webhook:

.. include:: samples/update_scaling_webhook.rst

To delete a webhook:

.. include:: samples/delete_scaling_webhook.rst

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
