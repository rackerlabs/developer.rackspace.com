---
layout: post
title: "MongoDB 3.0 and the Wired Tiger storage engine"
date: 2015-02-15 10:10
comments: true
author: Kenny Gorman
published: true
categories:
    - MongoDB
    - Databases
---

MongoDB 3.0 will include arguably the largest single architectural change in MongoDB's short history. MongoDB 3.0 includes a pluggable storage engine architecture. This allows for a new breed of core DB engines to exist, allowing MongoDB to be more useful and powerful. The first of these engines being WiredTiger. The Wired Tiger engine holds the promise (among other things) of better overall performance as well as increased compression of BSON documents on disk. This engine came from MongoDB inc's acquisition of WiredTiger in December of 2014. If you didn't know, WiredTigers development roots are squarely set in BerkeleyDB. Interesting right?

So how about some testing to see how WiredTiger is starting to stack up in terms of overall performance?

<!-- more -->

It should be noted at the time of this blog post, 3.0.0-rc8 was the latest and release candidate of 3.0, so things may be different with the actual stable version at release time.

## New architecture

All of the changes in 3.0 are detailed in the [release notes](http://docs.mongodb.org/v3.0/release-notes/3.0/). But there are a couple of items worth noting. Specifically [these](http://docs.mongodb.org/v3.0/administration/production-notes/#prod-notes-wired-tiger-concurrency). The interesting bits involve WiredTigers concurrent access to documents. Is this considered document level locking? Time will tell, but for now at least the concurrency model is a giant leap forward on paper.

Access is concurrent to the document level. Checkpoints happen on a 60 second or 2GB barrier. Journaling is present to replay operations left unapplied between checkpoints.

The journal, data, and indexes are all compressed. Blocks are compressed using the snappy compression algorithm from [Google](https://code.google.com/p/snappy/), as well as prefix compression. Compression uses more CPU than previous versions (obviously), but save some disk space. This is configured per collection as well.


## Tests

To test these major new components, sysbench-mongodb is used to simulate a generic MongoDB workload. The benchmark is available on Github, and the configuration is listed as a Gist here.

Rackspace OnMetal I/O flavor servers where used for database servers, and OnMetal Memory flavor was used as a load driver. One load driver per database server.

MongoDB 3.0.0-rc8 and 2.6.4 where compared.

It should be noted that the data sizes in question here are ALL IN MEMORY. The concept is we test the pure engine speed vs any disk related noise. We simply want to see the speed differential of the WT engine vs mmapv1.

## Results


There is a great [slide deck](http://www.slideshare.net/vkarpov15/mongodb-miami-meetup-12615-introduction-to-wiredtiger) from [@code_barbarian](https://twitter.com/code_barbarian) about all the nuances and differences of WiredTiger.
