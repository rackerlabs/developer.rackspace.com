---
layout: post
title: "Determining Optimal Storage based on IOPS"
date: 2013-03-18 8:00
comments: true
author: Edward Adame
categories: 
- Cloud Servers
- Cloud Block Storage
---
Rackspace Cloud Servers come in various sizes, and larger Cloud Servers are allocated a greater portion of available hypervisor resources. As of today, our largest Cloud Server (30 GB RAM / 1.2 TB disk space) consumes the entire physical hypervisor. A 15 GB Cloud Server consumes half of the hypervisor’s resources, an 8 GB server consumes a quarter, and so on until you get to the smallest allocation we provide (512 MB / 20 GB disk space).

One of the questions I was often asked as a Sales Engineer was how much disk performance customers should expect when using Cloud Servers. The short answer is that all Cloud Servers reside on storage local to the hypervisor and therefore contend for a fixed amount of I/O performance. Performance is influenced by many factors, including hypervisor load, where the virtual server physically exists on disk, etc., so it is not possible for me to predict how your specific Cloud Server will perform. What I can do, however, is perform some tests on a small sample of Cloud Servers to help you understand your options and what to consider when deploying applications on the Rackspace Cloud.

<!-- more -->

To start, I measured buffered disk reads on two different 1 GB Cloud Servers using hdparm. The average result of three tests on each was 180 MB/s – not bad for one of our smallest servers. Performing this same test on two different 15 GB Cloud Servers resulted in 250 MB/s – even better, but certainly comparable. Next, I measured I/O latency using a command called ioping. I committed a 1 MB write to disk every second for 24 hours, sorted the latency measurements from low to high and then removed the highest 5 percent.  Here are the results of tests on a 1 GB, 4 GB and 15 GB Cloud Server:

{% img center 2013-03-17-iops/1gb.jpg %}

{% img center 2013-03-17-iops/4gb.jpg %}

{% img center 2013-03-17-iops/15gb.jpg %}

This is not a scientific experiment; I would have had to repeat this test on dozens of Cloud Servers to make any specific performance claims, but what I can say is that sequential read / write performance will be similar across all Cloud Server sizes. Larger Cloud Servers tend to deliver more consistent I/O latency due to there being fewer ”noisy neighbors.”

Now, what about Cloud Block Storage? Cloud Block Storage is offered in two performance options, SATA and SSD. Both are connected to the server via gigabit Ethernet and sequential performance is limited to around 100 MB/s.  Performing the same latency test delivers the following results:

{% img center 2013-03-17-iops/sata.jpg %}

{% img center 2013-03-17-iops/ssd.jpg %}

Not surprisingly, _SSD Cloud Block Storage performs better than any other option tested_.

In conclusion, we can make a few general statements to help you pick the right storage option for your application:

* Always size your Cloud Server according to your RAM and storage needs. If you need additional storage, Cloud Block Storage will suit you well.
* If your server needs to run I/O intensive workloads, you should run at least an 8 GB instance to keep I/O latency to a minimum or add SSD Cloud Block Storage for good performance.
* For all other bulk storage needs, SATA Cloud Block Storage is your most cost effective option.
* If you choose to run SSD Cloud Block Storage for your database, consider using your local storage allocation for database backups.
