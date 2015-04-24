---
layout: post
title: Ansible and Docker
date: 2015-04-24 10:00
comments: true
author: Ash Wilson
published: true
categories:
- ansible
- docker
---

At first glance, [Ansible](http://www.ansible.com/) and [Docker](https://www.docker.com/) seem to be redundant. Both offer solutions to the configuration management problem: reliably and repeatably managing complicated software deployments. While you can certainly use either on its own with great success, it turns out that using both together can result in a faster, cleaner deployment process.

There are two ways that you can use Ansible and Docker together. You can use Ansible to orchestrate the installation and configuration of your Docker containers, or you can use Ansible to construct your Docker container images based on Ansible playbooks, as a more powerful alternative to Dockerfiles.

<!-- more -->

## Deploying Docker containers with Ansible

### The Ansible Docker module

Ansible includes a [Docker module](http://docs.ansible.com/docker_module.html) that you can use to manage the Docker containers that are active on a particular host.

### `pull=always` and `state=reloaded`

Two specific options, added in the recent Ansible 1.9.0 release, allow you to use the Docker module to deploy containers in a more idempotent fashion.

### `restart_policy=always`

Another important option you should consider using is `restart_policy`, which lets you use Docker as a process supervisor.

## Using Ansible to build Docker images

Most of the time, Dockerfiles are perfectly reasonable for creating Docker container images. For me, most of the benefit of using Ansible is that you can create playbooks that are *idempotent* &emdash; when you re-run your playbook, only the tasks that actually require changes have any effect. However, when you're creating a Docker container image, each step is performed from a consistent starting state (in theory, at least!). Also, because the Ansible build is performed as a single "step", delegating image creation to Ansible prevents you from being able to use the build cache purposefully. Managing the build cache well is important because it allows you to keep your image build times quick.

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
