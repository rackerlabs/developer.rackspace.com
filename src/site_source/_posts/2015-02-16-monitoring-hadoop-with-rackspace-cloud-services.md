---
layout: post
title: "Monitoring Hadoop with Rackspace Cloud services — managing partial failure"
date: 2015-02-16 10:00
comments: true
author: Dave Beckett
published: true
categories:
  - rackspace-cloud-monitoring
bio:
  - Dave Beckett is a software engineer with a background in multiple
    data and web metadata systems (from RDF to CSV) working in
    the Rackspace Data Platform group.  He spends most of his
    time tending the Hadoop cluster and writing Python to automate
    that.  Dave lives in San Francisco with his wife and in his spare
    time codes on his free software projects such as
    Redland RDF http://librdf.org/
    More details at http://www.dajobe.org/ and https://github.com/dajobe/
---

Hadoop is constructed from a large set of servers (or nodes) so to
properly manage it you need to have a good overall view of the
system.  It is ok if some data nodes are **not** in service — up to a
certain number — which is called *partial failure*.  The important
thing is to be able to see how many nodes are in an up or down state
to know the overall health of the cluster.

This article discusses how The Rackspace Global Data team created a
well-monitored Hadoop cluster by taking advantage of cloud services.

<!-- more -->

## The Problem ##

There are two aspects of monitoring a Hadoop system that need considering:

1. The *applications*  view: what's provided to the user.
  Applications include Hive, HBase, Map-Reduce and HDFS.  These are
  user applications with APIs and an expectation of being available.
2. The *infrastructure* view: nodes, processes: how the applications are built.
  The infrastructure consists of the processes that implement
  the applications and provide their own API.  These run across multiple
  nodes for redundancy and performance, fundamental aspects of Hadoop
  itself.


We run [Hortonworks Data Platform (HDP) 1.3](http://hortonworks.com/hdp/)
on a Cloud Big Data (CBD) cluster.  The *applications* in the cluster
look like this:

{% img center 2015-02-16-monitoring-hadoop-with-rackspace-cloud-services/hadoop-application-view.png "Rackspace Global Data Hadoop: Applications View" %}

The infrastructure that implements these looks like a big pool of
similar *Data Nodes* plus a set of special nodes that run application
processes:

{% img center 2015-02-16-monitoring-hadoop-with-rackspace-cloud-services/hadoop-systems-view.png "Rackspace Global Data Hadoop: Infrastructure View" %}

Applications can be combined onto nodes but generally are separated
for performance, operational stability and resilience and run in
Cloud Big data (CBD) or Cloud Servers (CS):

0. Gateway Node (CBD)
1. HDFS Name Node (CBD)
2. HDFS Secondary Name Node (CBD)
3. Map-Reduce Job Tracker (CBD)
4. HBase master (CBD)
5. Database master (CS)
6. Database slave (CS)

Hadoop V1 running in non-High Availability (HA) configuration means
some Hadoop applications and nodes are single points of failure
(SPOF) that require **non-automatic changes** to return them to
operation.  That means that there needs to be *monitoring* which
checks the applications and nodes are working, and an *alerting* path
from the monitoring so that failure can be noticed and escalated.
The alert and failed check needs to be delivered and inspected by a
person and further analysis any related metrics viewed.  Then the
recovery can be performed by the person:

1. promoting secondary or slave nodes
2. restoring from *backup*
3. deploying a replacement node

(Hadoop V2 has a different set of applications but the approach
described here remains valid: you need both infrastructure and
application monitoring.  Some applications can be configured for
automatic failover and the name node is not such a SPOF)


## Approach ##

Cloud Monitoring is the core of our monitoring the applications and
infrastructure and we supplement it with a several additional
Rackspace cloud services.  This section describes our approach and
how we used the services.

* **Cloud Big Data** and **Cloud Servers**: these provide the provisioned
  Hadoop cluster nodes and auxiliary nodes.  Provides: **compute nodes**
* **Cloud Monitoring**: a reliable, distributed checking system,
  filtering of events and reliably triggering alerts.  It has to
  survive no matter what at massive scale (billions of checks per day), so
  it contains lots of redundancy and resilience to failure.  This
  provides the **monitoring** and **alerting**
* **Cloud Metrics**: a service providing near real-time metric rollup
  (downsamples) with live access to the latest metrics over multiple
  time periods, with longer periods having less detail.  It
  implements a large time series database to allow a huge write rate
  along with indexes for rollups and searches. This provides **time
  series database of metrics**
* **Grafana**: a user interface to a time series database — in this case
  — Cloud Metrics.  This component provides the **user interface to metrics**
* **Cloud Intelligence**: a user interface over monitoring and metric
  data that allows both viewing and configuring of metrics, checks,
  alarms and notifications in Cloud Monitoring.  It uses Cloud
  Metrics to get time series data of metrics.  This provides **user
  interface to monitoring and alerting**
* **Cloud Backup**: A cloud service that provides a web-based
  configurable backup of file systems to the Cloud Files persistent,
  redundant and resilient cloud file storage.  Provides: **backup**

The rough pipeline of monitoring events is this:

                     /--> Cloud Metrics -> Cloud Metrics API -> Grafana
    Cloud Monitoring |
                     \--> Hadoop HDFS   -> Hadoop Hive       -> Analytical


It might sound like there are multiple services reading the same
monitoring data and processing and/or displaying it.  This is
partially true in that it is the same data however the different
approaches are valid since the requirements are different.
You can't really build one data system to do all these things well.

* **real-time**: metric event A, event B, event C, ...
* **monitoring**: current state of metric. read it and react.
* **time-series**: metric over a set of time periods with different
  rollup operators (sum, avg, max, min, ...).
* **adhoc**: answer "what if?" questions.  explore all the data.
  very large data size.
* **analytical**: analysis across other data dimensions such as
  datacenter,

In this article we are focused on the monitoring and time-series
requirements although we have a solution for the adhoc (Hive) and are
working on real-time and analytical needs.


## Monitoring Hadoop infrastructure — Cloud Monitoring standard checks ##

The
[standard Cloud Monitoring checks](http://www.rackspace.com/knowledge_center/article/rackspace-cloud-monitoring-checks-and-alarms)
make monitoring infrastructure very easy as they include all the most
useful checks.

We used the following
[check types](http://docs.rackspace.com/cm/api/v1.0/cm-devguide/content/appendix-check-types.html)
for monitoring infrastructure:

* *CPU Load*: check type `agent.cpu` check with metric `usage_average`
* *Filesystem Use*: check type `agent.filesystem` with metrics `used` and `free`
* *Memory Use*: check type `agent.memory` with metrics `used` and `free`
* *Network Ping response time*: check type `remote.ping` with metric `average`
  over three monitoring zones (data centers)

We initially configured these by hand, using the
[MyCloud dashboard](https://mycloud.rackspace.com/) for the Cloud Servers
and the Cloud Monitoring API for the CBD servers.
Later on we used the new and very easy to use — and strongly recommended —
[Cloud Intelligence UI](https://intelligence.rackspace.com/)
described in more detail below.
Later on, Cloud Monitoring added a new feature called
[Server-side configuration](http://www.rackspace.com/blog/monitor-like-a-pro-with-server-side-configuration/)
which allows configuration of checks on a node by dropping a simple
[YAML](http://www.yaml.org/) configuration file.  That makes
configuring easy to automate with your favourite deployment tool.


## Monitoring Hadoop applications — Cloud Monitoring standard and custom checks ##

Monitoring the Hadoop applications is relatively straightforward
since these are distributed, networked applications that have
addresses and ports.  Network checks or small custom scripts can be
used to connect to the application and validate that it is up.  The
applications include:

1. SSH on gateway node
2. Hive thrift server on gateway node
3. Hive metastore server on gateway node
4. Map Reduce job tarcker on gateway node
5. HBase master on hbase master node
6. HDFS primary nameserver on hdfs name node
7. HDFS secondary nameserver on hdfs secondary name node
8. MySQL server on database master node
9. MySQL server on database slave node

Cloud Monitoring has standard checks for SSH and MySQL.  The other
applications can be monitored using TCP connection checks for basic
functionality.  To check that the application is functioning as well
as connected, custom monitoring checks can run that perform
*heartbeat* checks that HDFS or Hive etc. are actually working.
Cloud Monitoring plugins are small scripts that are installed in a
standard directory on a node and can then be called by a monitoring
check to return variables that can be alerted.

The distributed applications (HDFS, Map-Reduce, HBase) have their own
central or master server with either an API (JMX) or a command line
tool or both that allows querying the state.  We use these to gather
the metrics for the distributed applications in one place so that
monitoring can be made of the more complex form where allowing
*partial failure* is OK up to a certain state.  We implemented these
with custom plugins for Cloud Monitoring that are deployed on the
relevant master nodes.  The plugins we use for HDFS, Map-Reduce and
HBase are available at the
[Monitoring Agent Custom plugins](https://github.com/racker/rackspace-monitoring-agent-plugins-contrib)
GitHub repository.

## Time-series Metrics — Cloud Metrics and Grafana ##

All Cloud Monitoring data is fed live into Cloud Metrics' distributed
time-series metrics processing system where rollups (downsamples) are
performed to allow accessing of all metrics by different time ranges
such as by hour, day, etc.  It is implemented with
[Blueflood](http://blueflood.io/) using a Cassandra backend
and provides near real-time rollups.

Cloud Metrics has no user interface itself however Grafana can be
deployed on a Cloud Server node via an
[OpenStack HEAT](https://wiki.openstack.org/wiki/Heat)
template talking to Cloud Metrics API backend.  This template was
created by the Cloud Metrics team and is available in the
[Rackspace Orchestration Templates](https://github.com/rackspace-orchestration-templates/grafana)
repository on GitHub.  We deployed it using the Orchestration feature
of [MyCloud](https://mycloud.rackspace.com/) using the custom
template from the repository above.  It takes only a few clicks
and a short amount of time to go from template to running Grafana.

The dashboard below shows one Grafana deployment monitoring some core
Hadoop metrics such as the number of mappers, reducers, HDFS state
and general CPU usage.

{% img center 2015-02-16-monitoring-hadoop-with-rackspace-cloud-services/grafana-small.png "Rackspace Global Data Hadoop: Grafana Dashboard" %}

## Viewing and Configuring Monitoring — Cloud Intelligence ##

Cloud Intelligence provides a user interface over the monitoring data
to view individual the state of single nodes, multiple nodes,
checks and alarms. It also provides a very easy to use interface for
configuring those checks, alerts and notification plans.  CI uses the
Cloud Monitoring API to do most of the work as well as the Cloud
Metrics API to render monitoring data time series graphs when
investigating nodes or metrics.

{% img center 2015-02-16-monitoring-hadoop-with-rackspace-cloud-services/hadoop-cloud-intelligence-small.png "Cloud Intelligence for Hadoop" %}



## Adhoc and Analytical — Hive and Hadoop (Cloud Big Data) ##

A large part of the data that is processed inside our hadoop cluster
is Cloud Monitoring's own data.  This data is written to HDFS and
loaded into Hive ORC tables for efficient access.  This allows Adhoc
queries and reports to be run against all monitoring data (1BN+
events per day, TBs/day).

This might sound like there is recursion going on: Cloud Monitoring
data about the Hadoop nodes is processed in Hadoop.  This is not an
operational problem because the system checks and alerting are
delivered via Cloud Monitoring itself.

## Backup and recovery — Cloud Backup ##

This is used in it's standard configuration and saves the critical
data needed for recovery (return to service) or deploying new nodes:

* backup user and system disks on all critical nodes (non-datanodes)
* backup databases from the MySQL slave
* backup HDFS secondary nameserver database from the secondary name node

Custom Cloud Monitoring checks were added to monitor that the backups
themselves have been performed (date of last backup is <1 day).

Recovery of SPOFs:

* **gateway node**: This is dataless — apart from user files — and
  could have hot spares or we can just deploy a new node.  Off-node
  recovery is provided by restoring from backup or installing a new one.
* **name node**:  The secondary namenode
  has a copy of the data providing recovery if the node fails.
* **database master**: Node failure can be handled by promoting a
  database slave or restoring the data from backup.


## Gaps and future work ##

We are not currently processing log files from applications or the
infrastructure and these contain valuable information.  Rackspace
does not currently have a log-focused service but a HEAT template for
Logstash with Elastic Search could be used to aggregate logs as well
as provide a searchable interface over them.

[Airbrake](https://airbrake.io/) (A Rackspace company) provides
error tracking products (formerly known as Exceptional).  It could
be used to receive application exceptions from the Java applications
and provide a different alerting path for those issues.

The current approach does not provide a good way to see an
aggregated view across multiple nodes (a network view) which is
useful for cluster applications like Hadoop.  We dealt with this
using Grafana to aggregate and display the results but this is not
alertable since it is too late in the pipeline.

The Cloud Monitoring system does not currently provide a way to
provide alarms computed above individual checks or nodes; such as
"alert me if more than 10% of my nodes are down" although in the
Hadoop case, we can work around this with a custom check.

Other monitoring systems such as Nagios provide heatmaps for high
level summary of metrics across nodes.  This would be useful for
high level views of metrics or nodes.

In future we are considering real-time (low latency focused) and
analytical (pre-computed, indexed, data cubes) needs for our
monitoring data.  We have some of this already in different systems
but not integrated with this world.

In addition to the raw Map-Reduce, there are higher level
applications such as job scheduling, pipelines and dataflow systems
such as provided by Oozie, Luigi and Azkaban.  These applications
deserve their own monitoring checks that can trigger alerts when
the rules or SLAs are not being met.

## Summary ##

The Rackspace Global Data team is operating and monitoring Hadoop
built in the cloud using multiple public Rackspace platforms:

1. [Cloud Big Data](http://www.rackspace.com/cloud/big-data) and [Cloud Servers](http://www.rackspace.com/cloud/servers)
2. [Cloud Monitoring](http://www.rackspace.com/cloud/monitoring)
3. [Cloud Metrics](http://www.rackspace.com/blog/cloud-metrics-working-toward-a-public-launch/)
4. [Cloud Intelligence](https://intelligence.rackspace.com/)
5. [Cloud Backup](http://www.rackspace.com/cloud/backup)

plus the open source [Grafana](http://grafana.org/) to
view the cluster metrics.
