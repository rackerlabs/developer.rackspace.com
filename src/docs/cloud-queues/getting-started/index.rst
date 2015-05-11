.. _quickstart:
===========================
Quickstart for Cloud Queues
===========================
Cloud Queues is an open source, scalable, and highly available message and notifications service, based on the OpenStack Marconi project.
Cloud Queues uses a few basic components--queues, messages, and claims--which give you the flexibility you need to create powerful web applications in the cloud.

This service supports a variety of messaging patterns, such as the *producer-consumer* model and the *publisher-subscriber* model, through which you create and manage queues and messages.

* In the *producer-consumer* model, you create queues in which producers, or servers, can post messages. Workers, or consumers, can then claim those messages and delete them after they complete the actions associated with the messages. A single claim can contain multiple messages, and administrators can query claims for status. This pattern is ideal for dispatching jobs to multiple processors.

* In the *publisher-subscriber* model, the publisher sends a message to the queue. All subscribers (or workers) listen for messages in the queue, but they do not claim them. Multiple subscribers can work on a message. Messages are eventually deleted based on their time to live (TTL) value. This pattern is ideal for notification of events to multiple workers at once.

Concepts
========
To use this service effectively, you should understand how these key ideas are used in this context:

claim
    The process of a worker checking out a message to perform a task. Claiming a message prevents other workers from attempting to process the same message.

message
    A task, a notification, or any meaningful data that a producer or publisher sends to the queue.

queue
    The entity that holds messages. Ideally, a queue is created per work type.
    For example, if you want to compress files, you would create a queue dedicated to files awaiting compression.

.. include:: ../../_common/authenticate.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Create queue
------------
To create a queue:

.. include:: samples/create_queue.rst

List queues
-----------
To list all the queues in a given region:

.. include:: samples/list_queues.rst

Delete queue
------------
To delete a queue:

.. include:: samples/delete_queue.rst

Warning: Deleting a queue also deletes all messages within it.

Post message
-------------
To post a message to a queue used by the consumer of the message:

.. include:: samples/post_message.rst

Claim messages
--------------
To claim a message or messages from a queue used by the consumer of the messages:

.. include:: samples/claim_message.rst

Release claimed messages
------------------------
To release a claimed message so that a different consumer can attempt to process the message,
if the consumer cannot complete the task specified in a message or simply refuses to do so:

.. include:: samples/release_message.rst

Delete messages
---------------
To delete a message after it has been used or completed and is no longer needed, avoiding duplicate work by other consumers of the queue:

.. include:: samples/delete_message.rst

More information
================
This Quickstart is intentionally very brief, demonstrating only a few basic operations.
If you want to know more, these are some good places to continue exploring:

* http://developer.rackspace.com/ links to all our Software Development Kits. It also offers developer-focused support resources such as our IRC channel.

* http://docs.rackspace.com/ links to all our API reference documentation, where you can find additional examples and extended explanations of key concepts. It also links to our documentation for Cloud Control Panel users.

* https://community.rackspace.com/developers/default is a forum where you can discuss your questions and concerns with a community of Rackers, Rackspace customers, and others interested in developing software in the cloud.
