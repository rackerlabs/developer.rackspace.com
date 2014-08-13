---
layout: post
title: "The Rackspace Cloud SDK for Java"
date: 2012-12-10 08:00
comments: true
author: Everett Toews
published: true
categories: 
- SDK
- Java
---
At the OpenStack Grizzly Summit in October 2012, Rackspace announced our Java Software Development Kit (SDK) for the open cloud. The Java SDK is powered by jclouds, an open source library that helps you get started in the cloud and employ your Java development skills. The jclouds Application Programming Interface (API) gives you the freedom to write portable code that works with many cloud providers or write code that utilizes cloud specific features. It also works with both public and private clouds, enabling hybrid cloud workloads.

<!-- more -->

To get started head over to the [Rackspace Cloud SDK for Java](http://docs.rackspace.com/sdks/guide/content/java.html). If you're looking for an SDK for another language, please have a look at the [Introduction](http://docs.rackspace.com/sdks/guide/content/intro.html) to all of our SDKs.

#Benefits
There are many benefits of using a Rackspace SDK:

* Coding in the language of your choice.
* Working with production tested code.
* Documentation on how to use the SDK.
* Examples that you can use as a starter kit for your own cloud applications.
* Engaging an established community of developers.
* Avoiding writing "plumbing" code to handle the HTTP level details.
* You only need one SDK for all Rackspace Cloud products.
* Interoperability with OpenStack

It's clear, using an SDK accelerates development and gives developers the most bang for their buck when it comes to learning new skills.

#Concepts
Because jclouds works across many cloud providers there are some differences in terminology and when writing portable cross-cloud code.

<table width="100%" border="1" cellspacing="0" cellpadding="0" align="left">
<tbody>
<tr>
<td>jclouds</td>
<td>Rackspace</td>
</tr>
<tr>
<td>ComputeService</td>
<td>Cloud Servers</td>
</tr>
<tr>
<td>Node</td>
<td>Server</td>
</tr>
<tr>
<td>Location/Zone</td>
<td>Region</td>
</tr>
<tr>
<td>Hardware</td>
<td>Flavor</td>
</tr>
<tr>
<td>NodeMetadata</td>
<td>Server details</td>
</tr>
<tr>
<td>User Metadata</td>
<td>Metadata</td>
</tr>
<tr>
<td>BlobStore</td>
<td>Cloud Files</td>
</tr>
<tr>
<td>Blob</td>
<td>File</td>
</tr>
</tbody>
</table>

Some cloud features, such as compute and object storage, have jclouds APIs that allow you to write code that is portable across different clouds. In order to take advantage of this portability you would write your Java code to the ComputeService and BlobStore APIs. To take advantage of Rackspace cloud specific features, such as changing your administrator password on a server or making a file available via a content distribution network, you would code to the ServerApi and CloudFilesClient APIs. There are also certain cloud features that have no portable API, such as block storage, where the only option is to use the VolumeApi.

It's also worthwhile to note that many of the jclouds APIs that you would use to work with the Rackspace open cloud are actually using the OpenStack API under the hood. This is because many of the Rackspace open cloud services are powered by OpenStack. For example, Rackspace Cloud Block Storage is powered by the OpenStack Cinder project, hence when you get an instance of the VolumeApi to create a block storage volume, you first access it via the CinderApi with `CinderApi.getVolumeApiForZone(String)`. This is made clear in the example code discussed below. These APIs work across all public and private OpenStack powered clouds, for more info read [jclouds and OpenStack](http://blog.phymata.com/2012/09/04/jclouds-and-openstack/).

So you can view jclouds as having 3 distinct layers of APIs.

<table width="100%" border="1" cellspacing="0" cellpadding="0" align="left">
<tbody>
<tr>
<td>Layer</td>
<td>Example APIs</td>
</tr>
<tr>
<td>Portable</td>
<td>ComputeService, BlobStore</td>
</tr>
<tr>
<td>OpenStack</td>
<td>NovaApi, ServerApi, CinderApi, VolumeApi</td>
</tr>
<tr>
<td>Rackspace</td>
<td>CloudFilesClient</td>
</tr>
</tbody>
</table>

You can find all of the APIs referenced above in the [latest jclouds Javadoc](http://demobox.github.com/jclouds-maven-site-1.5.3/1.5.3/jclouds-multi/apidocs/).

#Examples
After reading through the [Getting Started](http://www.jclouds.org/documentation/quickstart/rackspace/) guide, I find one of the best ways to learn a new technology is to start by running some examples yourself. The Rackspace SDKs have well tested examples in spades. For jclouds, you can find instructions on how to run them from the command line or Eclipse at [Rackspace Examples](https://github.com/jclouds/jclouds-examples/blob/master/rackspace/README.md).

Start with the [rackspace package](https://github.com/jclouds/jclouds-examples/tree/master/rackspace/src/main/java/org/jclouds/examples/rackspace). There you will find general purpose examples of things that are useful across all services.

* [Logging.java](https://github.com/jclouds/jclouds-examples/blob/master/rackspace/src/main/java/org/jclouds/examples/rackspace/Logging.java) - How to enable and configure logging. See what jclouds is sending across the wire. Essential for debugging, troubleshooting, and getting help!
* [Authentication.java](https://github.com/jclouds/jclouds-examples/blob/master/rackspace/src/main/java/org/jclouds/examples/rackspace/Authentication.java) - How you can use your credentials to authenticate with the Rackspace open cloud.


The [cloudfiles package](https://github.com/jclouds/jclouds-examples/tree/master/rackspace/src/main/java/org/jclouds/examples/rackspace/cloudfiles) demonstrates how to accomplish common tasks for putting files in and getting files from the cloud.

* [CloudFilesPublish.java](https://github.com/jclouds/jclouds-examples/blob/master/rackspace/src/main/java/org/jclouds/examples/rackspace/cloudfiles/CloudFilesPublish.java) - An end to end example of publishing a file on the internet with Cloud Files.
* Other examples of creating, updating, listing, and deleting containers/objects.


The [cloudservers package](https://github.com/jclouds/jclouds-examples/tree/master/rackspace/src/main/java/org/jclouds/examples/rackspace/cloudservers) demonstrates how to accomplish common tasks for working with servers in the cloud.

* [CloudServersPublish.java](https://github.com/jclouds/jclouds-examples/blob/master/rackspace/src/main/java/org/jclouds/examples/rackspace/cloudservers/CloudServersPublish.java) - An end to end example of publishing a web page on the internet with Cloud Servers.
* Other examples of creating, manipulating, listing, and deleting servers.


The [cloudblockstorage package](https://github.com/jclouds/jclouds-examples/tree/master/rackspace/src/main/java/org/jclouds/examples/rackspace/cloudblockstorage) demonstrates how to accomplish common tasks for working with block storage (aka volumes) in the cloud.

* [CreateVolumeAndAttach.java](https://github.com/jclouds/jclouds-examples/blob/master/rackspace/src/main/java/org/jclouds/examples/rackspace/cloudblockstorage/CreateVolumeAndAttach.java) - An end to end example of creating a volume, attaching it to a server, putting a filesystem on it, and mounting it for use to store persistent data.
* Other examples of creating, manipulating, listing, and deleting volumes and snapshots.

#Conclusion

I have been working with jclouds for a while now and I’ve been thoroughly impressed by their commitment to quality and community. It’s a well maintained project with a bright future. Pull request #1000 was merged just one week ago!

If you need a hand or encounter any troubles, I invite you to join the [jclouds group](https://groups.google.com/forum/?fromgroups#!forum/jclouds) or file an [issue](https://github.com/jclouds/jclouds/issues). If you’re interested in contributing, read about [Contributing to the jclouds Code Base](http://www.jclouds.org/documentation/devguides/contributing-to-jclouds/). We love to get feedback too so for general feedback and support requests, you can send an email to <sdk-support@rackspace.com>.
