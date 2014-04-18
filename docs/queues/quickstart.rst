.. _quickstart:

Quickstart Guide
=================

Cloud Queues
---------------

Queues is an open source, scalable, and highly available message and notifications service, based on the OpenStack Marconi project. Users of this service can create and manage a producer-consumer or a publisher-subscriber model. Unlimited queues and messages give users the flexibility they need to create powerful web applications in the cloud.

It consists of a few basic components: queues, messages, claims, and statistics. In the producer-consumer model, users create queues in which producers, or servers, can post messages. Workers, or consumers, can then claim those messages and delete them after they complete the actions associated with the messages. A single claim can contain multiple messages, and administrators can query claims for status. 

Authentication and Client
---------------------------------

As with most other services in the Rackspace cloud, before we can get started we need an instance of the correct client and proper credentials.

Create Queue
--------------

First order of business is creating a queue. In its most basic usage, you can do so as follows:

.. include:: samples/create_queue.rst


List Queues
------------

To see a list of all the queues in a given region, see code below

.. include:: samples/list_queues.rst

Delete Queue
--------------

You may delete a queue, you may do so as follows:

.. include:: samples/delete_queue.rst

Warning: deleting a queue will also delete all messages within it.

Post Message 
--------------

Having an empty queue is not that interesting or useful. To remedy that, you should post messages to it. To do so, follow examples below:

.. include:: samples/post_message.rst


Claiming Messages
-------------------

On the other side of that model, the consumer of those messages can claim messages as follows:

.. include:: samples/claim_message.rst


Releasing Claimed Messages
---------------------------

If for whatever reason the consumer cannot complete the task specified in a message or simply refuses to do so, it can release the claim so that a different consumer could attempt to process the message, as shown below:

.. include:: samples/release_message.rst

Deleting Messages
-------------------

After a message has been used or completed and is no longer needed, a consumer should delete the message to avoid duplicate work by other consumers of the queue. Deleting a message can be done as follows:

.. include:: samples/delete_message.rst 

