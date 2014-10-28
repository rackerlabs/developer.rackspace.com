---
layout: post  
title: "Introducing Gophercloud: an OpenStack SDK for Go"  
date: 2014-10-22 11:45  
comments: true  
author: Developer Experience Team  
published: true  
categories:
  - golang
  - sdk
  - gophercloud
---

As part of our ongoing mission to serve developers, we are proud to announce the initial public release of [Gophercloud](https://github.com/rackspace/gophercloud/). Gophercloud is a [Go](http://golang.org) OpenStack&trade;-first SDK with Rackspace support. What that means is Rackspace and OpenStack&trade; users can seamlessly integrate it into their existing applications, and users of other Openstack-based clouds can extend it to work with theirs.

<!-- more -->

Initially Gophercloud supports the following services:

 - Compute
 - Object Storage
 - Networking
 - Block Storage
 - Identity

And more [on the way](https://github.com/rackspace/gophercloud/pulls). 

To install Gophercloud, first set the $GOPATH environment variable to the root of your Go workspace:

```
 mkdir $HOME/go
 export GOPATH=$HOME/go
```
After that, you can install it with the following command:

```
 go get github.com/rackspace/gophercloud
```

From there on, you can follow the [getting started guides](https://gophercloud.io/docs/) to help you get up and running.

As with any of our other SDKs, you can always reach us via email (sdk-support@rackspace.com) or chat (#rackspace channel on Freenode IRC) if you have any issues, questions or suggestions.