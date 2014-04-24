developer.rackspace.com
=======================

Third gen of the developer.rackspace.com Portal

This repo represents a refactor of the developer.rackspace.com site. 

The dev site currently builds from rackerlabs/devsite but only accepts new blog posts as of March 1 2014.

We are migrating to this repo by June-ish 2014. The areas now building are:
- landing page: jekyll, content maintained in this repo
- /docs/: sphinx, content maintained in this repo

In progress:
- /api/: maven, content maintained in /dian4554/rax-api-ref (to move to rackerlabs/rax-api-ref)
- elastic search
- automation of staging/prod

To do:
- assets to CDN
- feeds
- automated push to prod
=======
Workflow
================

Needed:
* Stage 1: build jekyll site; if no errors; put into directory pending push to nginx
* Stage 2: build sphinx docs; if no errors; put into stage 1 directory tree under /docs
* Stage 3: build API index; if no errors; put into stage 1 directory tree under /apis
* Stage 4: Push assets to cdn (tbd)
* Stage 5: Push stage 1 static dir to nginx

Continuous Integration
================
* staging-jenkins (+1 slave node. note: slave nodes need the exact same software stack as master node. Unlike Travis, Jenkins needs the environment to be setup ahead of time.)
* 2-stage build process following workflow above
  * Job "Build Site" performs Stage 1 related tasks
  * Then, if "Build Site" succeeds, triggers job "Build Sphinx Docs" which runs the tasks deliniated in Stage 2 above.