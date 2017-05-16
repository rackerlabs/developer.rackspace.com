---
layout: post
title: "Automate deploying Rackspace Cloud Monitoring agent"
title: "Using Docker Machine to run your Docker Containers in Rackspace"
date: 2015-01-26 23:59
comments: true
author: Simon J
published: true
categories:
    - Orchestration
    - docker
---

## Background
Beginning of December Docker anounnced [Docker Machine](http://blog.docker.com/2014/12/announcing-docker-machine-swarm-and-compose-for-orchestrating-distributed-apps/). Docker Machine does away with the need to first create virtual machines, install Docker on them, to finally use them as a Docker host. Instead it covers the provisioning and install process for you behind the scences. To read more about Docker Machines [click here](https://github.com/docker/machine).

Let's take a quick look at how we can get some of this awesomeness!


### Install

Docker Machine is currently still unreleased, which means you have to first download the machine binary from [https://github.com/docker/machine/releases](https://github.com/docker/machine/releases). There aren't any dependencies to install, so you can just rename it to docker-machine and place it into your path somewhere.


### Credentials

Unsurprisingly you'll also need your API-key and Rackspace username. If you're unsure about how or where to get those please follow [these instructions](http://www.rackspace.com/knowledge_center/article/view-and-reset-your-api-key).


### Create your first Docker Host

We can now user docker-machine to create our Docker host:


      docker-machine create --driver rackspace \
                            --rackspace-api-key $YOUR_API_KEY \
                            --rackspace-username $YOUR_USERNAME \
                            --rackspace-region DFW my-first-docker-machine-host

This step will take a few minutes. If you want to streamline your command further you can export your credentials and region via `OS_USERNAME`, `OS_API_KEY` and `OS_REGION_NAME`. This reduces the command to:


    docker-machine create --driver rackspace my-second-docker-machine-host

In either case you should see something along the lines of:


    INFO[0001] Creating server...                            Name=my-second-docker-machine-host
    INFO[0070] "my-second-docker-machine-host" has been created and is now the active machine
    INFO[0070] To connect: docker $(machine config my-second-docker-machine-host) ps


### Some other basic operations

We can also perform some other basic management tasks:


    # show the active "machine" (the default one)
    docker-machine active

    # displaying a complete list of details
    docker-machine inspect my-first-docker-machine-host

    # display the docker-client connection details for a machine
    docker-machine config my-first-docker-machine

    # removing a machine
    docker-machine rm my-first-docker-machine-host

More information can be [found here](https://github.com/docker/machine#rackspace).


### Lets actually put this to work

Lastly we want to actually use our machine to deploy a container. To do so we simply use the `docker` command, we extract the matching config via the docker-machine command:


    docker `docker-machine config my-second-docker-machine-host` run --name my-redis-container -d redis

We can then run the `ps` command to check on our container:

    docker `docker-machine config my-second-docker-machine-host` ps

    CONTAINER ID        IMAGE               COMMAND                CREATED             STATUS              PORTS               NAMES
    3cff35053aee        redis:latest        "/entrypoint.sh redi   35 minutes ago      Up 5 minutes       6379/tcp            my-redis-container

and finally we confirm that Redis is indeed running and accepting connections. In order to do so we spin up a mini container, drop to the console, and use telnet to see if Redis `PINGs`:

    docker `docker-machine config my-second-docker-machine-host` run --link my-redis-container:redis -it --rm busybox

    / >>> telnet redis 6379
    PING
    +PONG


If you have feedback please contact us via [sdk-support@rackspace.com](mailto:sdk-support@rackspace.com), twitter [@rackspace](https://twitter.com/rackspace), chat with us in #rackspace on Freenode, or post in our [developer forum](https://community.rackspace.com/developers/default).
