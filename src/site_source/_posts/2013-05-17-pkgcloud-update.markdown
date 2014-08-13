---
layout: post
title: "pkgcloud update"
date: 2013-05-17 09:23
comments: true
author: Ken Perkins
published: true
categories: 
- pkgcloud
- SDK
- node.js
---
As we hinted at in our [post earlier this week](http://devops.rackspace.com/release-of-pkgcloud.html), Rackspace is working towards an official release of node.js SDK bindings for the Rackspace Cloud. I thought it was important to provide more clarity on exactly what we're doing and why. 

### Enter pkgcloud

Rackspace is now sponsoring development to an existing multi-cloud provisioning package for node.js called [pkgcloud](https://github.com/nodejitsu/pkgcloud) authored primarily by the team at [Nodejitsu](http://nodejitsu.com). We evaluated a number of options for our node.js SDK strategy, including authoring our own package, but we felt that contributing back to the prominent cloud provisioning package for node.js aligned with strategies we already have in place for Ruby with [fog](https://github.com/fog/fog) and Java with [jclouds](https://github.com/jclouds/jclouds).

When we talk about an *official release*, in actuality we're saying it's our first [*supported*](http://www.rackspace.com/blog/rackspace-developer-support-fanatical-support-for-your-code/) release of `pkgcloud`. You can download and use `pkgcloud` against the Rackspace Cloud today, but we're not yet ready to call the current version official. 

The `pkgcloud 0.7.2` release supports First Generation Cloud Servers, Cloud Files, and Cloud Databases.

<!-- more -->

In the next major release of pkgcloud, we plan to add the following new features:

* Support for Next Generation Cloud Servers
* Support for v2 authentication. Enables you to specify multi-region authentication
* Region support, DFW, ORD, and so on, for Cloud Servers, Cloud Files, and Cloud Databases
* Enhanced streaming support for Cloud Files
* Improved samples and documentation for current and new features

### Roadmap

As soon as we add these features to the next release of `pkgcloud` *(tentatively v0.8)*, the plan going forward is to add support for our ever-expanding cloud portfolio, including:

* Cloud Backup
* Cloud Block Storage
* Cloud DNS
* Cloud Files with CDN
* Cloud Load Balancers
* Cloud Monitoring
* Cloud Networking
* Cloud Servers Extension Support (from OpenStack)
* ...and many more projects, some that you haven't heard of yet that will blow your socks off!

As a result of some of our early contributions, I've been granted committer privileges with `pkgcloud`, which is a huge sign of trust and colloboration from Nodejitsu. It's great to see companies that share a philosophy towards open-source software.

To that end, we'd also like to call out for more contributors; there's a ton of work to do and it'd be great if other individuals or organizations engaged with node.js and Rackspace or OpenStack wanted to help out. 

We'll share more specifics about the coming releases as they get closer, and about our longer-term roadmap as it has more clarity, but we wanted to share our plans with you early enough to provide feedback and start planning the coming `pkgcloud` integrations.

_As the lead node.js Developer Advocate for the Developer Relations Group, Ken spends most of his time developing Rackspace's node.js plans and code contributions. A recently appointed core commiter on [pkgcloud](https://github.com/nodejitsu/pkgcloud), Ken joined Rackspace in March of 2013 after 2 years at Clipboard and almost 9 years at Microsoft. Follow Ken on twitter at <http://twitter.com/kenperkins>_
