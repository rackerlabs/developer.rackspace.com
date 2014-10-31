---
layout: post  
title: "Docker host management, Kubernetes Rackspace binary install, and Gophercloud 1.0"  
date: 2014-10-30 11:45  
comments: true  
author: Developer Experience Team  
published: true  
categories:
  - docker
  - gophercloud
  - rackspace
---
### Docker Host Management

While Docker has been the talk of town for the last few months, it has always been a bit painful to get started with and deploy. To alleviate this, [one](https://github.com/docker/docker/issues/8681) of the folks at Docker came up with a *proposal* to include Docker host management in the Docker client itself and phase out wrapper utilities like `boot2docker`.

<!-- more -->

Today while at worldwide Docker Hackday, this proposal was [publicly announced](https://www.youtube.com/watch?v=lZGmvGw-mWc&feature=youtu.be). We are glad to announce that Rackspace will be supported very soon thanks to the [work](https://github.com/bfirsh/docker/pull/10) of Ash Wilson and Nels Nelson among others. 

If included in Docker, this would be great news all around for users, developer and providers. So, we strongly encourage anyone interested check out the proposal (linked above) and provide any feedback and to help to make this feature happen.

### Kubernetes Releases Rackspace Binary Installs

Google's open source cluster management tool, [Kubernetes](https://github.com/GoogleCloudPlatform/kubernetes) has included binary installation packages for [Rackspace/CoreOS](https://github.com/GoogleCloudPlatform/kubernetes/releases/tag/v0.4.2) which greatly streamlines the initial installation process on CoreOS hosts in Rackspace.

### Gophercloud 1.0.0 Announced

Last but not least, as we recently [announced](https://developer.rackspace.com/blog/introducing-gophercloud/), we have officially released Gophercloud version 1.0.0, an OpenStack SDK for Go. This release comes with our commitment to support and maintain it from here onwards, much like [all of our SDKs](https://developer.rackspace.com/sdks/) and is ready to be included in your Go-based application.
