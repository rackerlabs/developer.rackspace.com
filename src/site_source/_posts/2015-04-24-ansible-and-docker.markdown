---
layout: post
title: Ansible and Docker
date: 2015-04-24 10:00
comments: true
author: Ash Wilson
bio: >
  Ash is a software developer on Rackspace's Developer Experience team. His interests include programming languages, continuous deployment, and plugging things into other things (we had to cover all the wall sockets).
published: true
categories:
- ansible
- docker
---

At first glance, [Ansible](http://www.ansible.com/) and [Docker](https://www.docker.com/) seem to be redundant. Both offer solutions to the configuration management problem: reliably and repeatably managing complicated software deployments. While you certainly can use either on its own with great success, it turns out that using both together can result in a faster, cleaner deployment process.

There are two ways that you can use Ansible and Docker together. You can use Ansible to orchestrate the deployment and configuration of your Docker containers on the host, or you can use Ansible to construct your Docker container images based on Ansible playbooks, as a more powerful alternative to Dockerfiles.

<!-- more -->

## Deploying Docker containers with Ansible

Docker containers are a powerful way to deliver a consistent environment for your software, from your laptop the whole way to a cluster of production machines, but there are still ample responsibilities left for Ansible to take. Ansible can provision your [servers](http://docs.ansible.com/rax_module.html), your [networks](http://docs.ansible.com/rax_network_module.html), your [load balancers](http://docs.ansible.com/rax_clb_module.html), and [more](http://docs.ansible.com/list_of_cloud_modules.html#rackspace). If the server image you choose doesn't already have Docker installed, you'll need some way to do that. Sometimes, you'll also need to manage the Docker daemon's configuration, or tweak [Linux kernel parameters](http://docs.docker.com/installation/ubuntulinux/#adjust-memory-and-swap-accounting).

Most prominently, though, you can use Ansible to manage how and where each of your containers run: image versions, environment variables, volumes and links.

### The Ansible Docker module

Ansible includes a [Docker module](http://docs.ansible.com/docker_module.html) that you can use to manage the Docker containers that are active on each host. It supports an intimidating number of module parameters, but you only need to know a few to get started.

The minimum information that you can specify is the name of an image. It's good practice to also be explicit about the desired state, even though there's a default value. I also prefer to name my containers whenever I can, so that the output of `docker ps` is as readable as possible, and to make it convenient to reference from other containers later.

```yaml
- name: Database
  docker:
    name: database
    image: postgres:9.4
    state: started
```

This task will pull the latest Postgres image from DockerHub if it isn't present, then launch a single container. It won't launch anything if any container called "database" is already running on the current host.

That's a good starting point, but if you're shipping code often, your application images will be changing frequently, and this task will never see those updates! To deploy new versions of your software, you'll want to take advantage of the "pull" parameter and the "reloaded" state.

### `pull=always` and `state=reloaded`

These two options, added in the recent Ansible 1.9.0 release, allow you to use the Docker module to deploy containers in a more idempotent fashion. `pull=always` performs a `docker pull` on the server before anything else is done, even if the image is already present -- this lets you be certain that you're running the latest builds of all of your containers. Using `state=reloaded` instead of `state=started` invokes more powerful logic about your container's state: it asserts that, not only is a container with the same *name* (or matching image and command) running, but a container with the same *configuration*. If anything has been changed in the playbook -- a new version of the container's image, a different value for an environment variable, or a redeployed container that was linked to this one -- the existing container or containers will be stopped and new ones will be started with the new configuration. If everything is still the same, though, nothing will be done and the module will report `changed=false`, like a well-behaved Ansible citizen.

Using them together lets you keep a container up to date, keep its configuration up to date, and automatically propagate container restarts to any dependent containers. Handy!

```yaml
- name: My application
  docker:
    name: web
    image: smashwilson/minimal-sinatra:latest
    pull: always
    env:
      SOMEVAR: value
      SHH_SECRET: "{{ "{{ from_the_vault" }} }}"
    link:
    - "database:database"
```

*Pro tip:* If you register the result of this task, you can use a `debug` task to inspect the `reload_reasons` variable and see *why* Docker decided to restart a specific container.

I like to use these parameters on my own application containers, because they change often and because I can write code that can gracefully handle the occasional restart. It's probably not a good idea to use them on containers that provide infrastructure services, like your database, because you won't want those to restart unless you really need them to!

### `restart_policy=always`

Another important option you should consider using is `restart_policy`, which lets you use Docker as a process supervisor, like upstart, monit, or forever.js.

## Using Ansible to build Docker images

Most of the time, Dockerfiles are perfectly reasonable for creating Docker container images. For me, most of the benefit of using Ansible is that you can create playbooks that are *idempotent* - when you re-run your playbook, only the tasks that actually require changes have any effect. However, when you're creating a Docker container image, each step is performed from a consistent starting state (in theory, at least!). Also, because the Ansible build is performed as a single "step", delegating image creation to Ansible prevents you from being able to use the build cache purposefully. Managing the build cache well is important because it allows you to keep your image build times quick.

Still, there are several reasons why using an Ansible playbook can be beneficial:

 * If you have existing infrastructure that's already using a pure Ansible approach, it's a simple way to kickstart a migration into containers.
 * Ansible allows you to use Jinja2 templates to create files from templates, enabling you to use variables to reduce duplication and derive values from the environment.
 * Ansible's [extensive module library](http://docs.ansible.com/modules_by_category.html) can help you simplify common administrative tasks.
 * You can use roles published on [Ansible Galaxy](https://galaxy.ansible.com/) to benefit from expertise from the community.

To do so, all that you need to do is use one of [the official base images](https://github.com/ansible/ansible-docker-base) that ship with Ansible pre-installed, and execute `ansible-playbook` in a `RUN` step:

```
FROM ansible/ubuntu14.04-ansible:stable

# Add your playbooks to the Docker image
ADD ansible /srv/example
WORKDIR /srv/example

# Execute Ansible with your playbook's primary entry point.
# The "-c local" argument causes Ansible to use a "local connection" that won't attempt to
# ssh in to localhost.
RUN ansible-playbook site.yml -c local

EXPOSE 443
ENTRYPOINT ["/usr/local/bin/myapp"]
CMD ["--help"]
```
