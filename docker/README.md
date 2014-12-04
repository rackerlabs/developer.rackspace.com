# Dockerfiles

This directory contains the specifications for constructing [Docker](https://docker.com/whatisdocker/) containers for individual parts of the developer.rackspace.com infrastructure.

If you're developing something that will ultimately be deployed within one of these containers, call `script/dockbuild` with the directory name to rebuild an image that includes your changes. Right now, this only includes the build stack itself.

If you're writing content in `src/docs` or `src/site_source`, you can use the latest build from Dockerhub instead, which entry points like `script/preview` will do for you automatically.

## Contents

Each subdirectory provides the pieces necessary for a single container image, which include:

 1. A [Dockerfile](https://docs.docker.com/reference/builder/) for that container.
 2. `buildcontext`, a text file containing the path relative to this repository's root.
 3. Any other resources that are only useful to this container.
