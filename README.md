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
