---
layout: post
title: "Building A .NET SDK For OpenStack"
date: 2013-05-06 08:00
comments: true
author: Alan Quillin
published: true
categories: 
- OpenStack
- .NET
---
As developers on the [RackConnect](http://www.rackspace.com/cloud/hybrid/dedicated_cloud/rackconnect/) product, my team has a strong need for RackConnect to interact with OpenStack when we launched the next generation public cloud. RackConnect is written in .NET, and we began baking pieces of a rudimentary .NET software development kit (SDK) functionality into our software to help use the OpenStack services. 

After doing some research, we realized that there was not a .NET SDK and that other developers could benefit from what we were creating. A huge part of our enterprise customers are Microsoft shops that write in .NET and it would be incredibly helpful for them to have a .NET SDK to interact with our cloud. This clear need led our team to write and open source the [Rackspace Cloud SDK for Microsoft .NET](http://openstacknetsdk.com/).

<!-- more -->

We built this SDK on top of a .NET 4.0 framework, so it can be used on 4.0 and 4.5. My team has it on the roadmap to make the SDK backwards compatible to support .NET 3.5. Our SDK can be used in any .NET project type that you might need to interact with your OpenStack services, whether it be an ASP.NET web application, console application or a Windows Forms application. If you have a .NET project in a .NET compiled language, this SDK will work for you. 

We broke the Rackspace Cloud SDK for Microsoft .NET down to a set of providers that talk to different services. Currently the .NET SDK supports Cloud Servers, Cloud Files, Cloud Networks, Identity and Cloud Block Storage. RackConnect uses the servers and identity portion of the SDK very heavily, making thousands of calls a day to spin up servers, automatically verify that RackConnect is set up properly with the correct identity roles and updating metadata during the build process to our control panel.

While this SDK currently supports only Rackspace’s flavor of OpenStack, we have open sourced the [SDK on Github](https://github.com/rackspace/openstack.net). We are in the process of also tweaking and verifying that the SDK can work for the default version of OpenStack. Since it is open source, developers can fork the code to help out with this effort or make particular customizations to work with their version of OpenStack.

The spirit of OpenStack is to work together as a community to drive innovation in cloud hosting. As we worked on Rackconnect, we recognized the need of having a .NET SDK to interface with OpenStack and hope that others will join us to build it out further.

##Related Links:

* [Getting Started with openstack.net](https://github.com/rackspace/openstack.net/wiki/Getting-Started) – get started with installation and examples.
* [API Reference Manual](http://docs.rackspace.com/sdks/api/net/) – detailed documentation for the classes and m    ethods.
* [Release Notes](https://github.com/rackspace/openstack.net/wiki/Feature-Support) – release notes for software versions.
* [Code Examples](https://github.com/rackspace/openstack.net/wiki/Code-Samples) – code examples demonstrating how to accomplish common tasks.
