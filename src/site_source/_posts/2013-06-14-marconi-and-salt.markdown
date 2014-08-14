---
layout: post
title: "Marconi and Salt, part 1"
date: 2013-06-14 07:45
comments: true
author: Oz Akan
published: true
categories: 
- SaltStack
- Marconi
- OpenStack
---
When Henry Ford invented the assembly line at his automobile company he changed the world as we know it. It wasn’t the automobile itself that sparked that dramatic change; it was the mass production of the automobile that enabled people to go wherever they wanted whenever they wanted that created a world of possibilities. The automobile was not the final product; it was the great tool to build new products on top of it. And the ability to create a million copies of that great tool gave birth to the industrial age.
Our story starts with cloud computing era when IT finally gets rid of the boundaries of physical environments but still finds itself incapable of delivering the "great tool" quickly enough to the people who are building new products on top of it. The great tools in this story are computers or better put, servers configured for specific purposes.
In this series, we will explore Salt and automate an OpenStack Marconi environment. When we are done, with very little effort, we will be able to:

* Create new environments like production, development, test, etc. in different locations
* Create an unlimited number of Linux servers of different types
* Automatically adjust the environment when a component is changed
* Create a MongoDB primary-secondary replica set with desired number of secondaries in minutes
* Takes actions based on some metrics (like autoscale if average load is over five)

<!-- more -->

##What is Marconi?
As OpenStack matures, it needs to support bigger, more complex web applications. Such applications require a robust, web-scale message queuing service to support the distributed nature of large web applications. Marconi is a new OpenStack project designed to fill this need.

##What is Salt?

Salt is a configuration management tool capable of remote execution that is especially helpful for systems management in cloud environments and CICD. A developer would say it requires little programming, while an operations engineer would say it requires little administration. It is pretty quick when it comes to remote execution because it uses zeromq for message transportation. Everything is a file, so all of it can be tracked under a version control system. It is scary flexible - you can change storage type, template language and others to fit your expertise and goals.

It is developed in Python which makes it pretty easy to install on Linux. If you want to go beyond what has already been provided, it is pretty straightforward to extend its capabilities as well.

##Why Salt?

Salt brings simplicity. 

Salt, Chef or Puppet, these are all simple to use when all you want to do is to maintain an Apache configuration file on your web servers. This is how you will start using these tools. Once you get it, you will start to investigate how you can automate the whole environment where adding a web server means more than creating that server.

###Well Defined Servers

The benefit of having a server defined under Salt is that you won't have to worry next time when you want to create that server. It is already defined and all installation / configuration related issues have been solved, it is likely there had been several improvements over the course of the configuration and you have a "best practice" setup for the specific server.

As an example, let’s say you have a LAMP environment defined under Salt with two Apache web servers that have PHP support and two MySQL servers configured as master and slave. You can create a development environment very quickly without leaving a single package uninstalled or a configuration parameter forgotten because you won't have to solve the "installation / configuration" problem again. You don't even have to remember all those boring details. It will be perfect every time and Salt will act as if it is the very first server it is creating.

###Automation

Today's applications usually rely on several components. If we take a video conversion service as an example, it may rely on components like load balancers, firewalls, web servers, encoder / decoders, database servers, queuing service, FTP service, mail service, storage service, etc. When you add another web server to this environment, you may have to change the firewall, load balancer or database depending on the relation defined between these components. Instead, once relations are defined in Salt, it can make these changes, in all environments you have. You can add one more web server in five different environments and Salt will know which load balancer configuration to change in each one of these environments.

###IT as a Service

Chances are developers are asking for new environments all the time. They want you to re-configure one of the old servers, or create a temporary one for some specific test. Salt will take the entire burden. They will ask, you will deliver. You can even handover the development environment to developers by providing them a specific Salt installation.

###Cloud Computing
Cloud computing gives you unlimited resources and quick provisioning which almost urges everyone in the organization to create different environments sometimes with only a few servers. You will have to create more servers and turn over will be higher. For example, Salt can add a secondary mongo instance, configure a cloud load balancer with five web servers behind it and configure web servers to use this secondary mongo instance for, let’s say, interface testing.

##Salt Components
Let’s go over some of the important Salt components:

* master: the application that handles the communication and distribution of configurations.
* minion: the application that runs on all servers that you want to manage with Salt.
* pillar: data store that is accessed by master and distributed to minions based on defined rules.
* grain: local data stored on the minion that is accessible only by the minion itself.
* state: (aka SLS) the configuration we want a minion to have (example: Apache being installed)

We will discuss these more in detail later on.

##Salt Master Installation

###Create Salt Master Instance

There are a few different ways to create this instance. We can use novaclient, pyrax or the Rackspace Cloud Control Panel which I will use just once, to create the salt master server. Once we have master server running, we won't need to access anything else.

On the control panel, I create a 2GB instance in DFW region. I choose Ubuntu. Obviously, Salt master works well on other distributions as well.

{% img center 2013-06-14-marconi-and-salt/image01.png %}

###Configure The Instance

After the instance boots up, I login to the instance with the provided default password. 

    ~ oz$ ssh 162.209.15.12 -l root
    The authenticity of host '162.209.15.12 (162.209.15.12)' can't be established.
    RSA key fingerprint is 1c:71:0f:f1:74:f1:cf:9a:9b:90:7a:a2:db:6b:22:cb.
    Are you sure you want to continue connecting (yes/no)? yes
    Warning: Permanently added '162.209.15.12' (RSA) to the list of known hosts.
    root@162.209.15.12's password:

    The programs included with the Ubuntu system are free software;
    the exact distribution terms for each program are described in the
    individual files in /usr/share/doc/*/copyright.

    Ubuntu comes with ABSOLUTELY NO WARRANTY, to the extent permitted by
    applicable law.

    Welcome to Ubuntu 13.04 (GNU/Linux 3.8.0-19-generic x86_64)
    * Documentation:  https://help.ubuntu.com/
    root@salt01:~#

Once I am in, I will copy my public key to have passwordless access and disable password authentication to this server to increase the security. This server will be the hub for all of the actions that I will take on my environment. Later we will have some firewall rules in place as well.

###Passwordless SSH

I create a key pair for the server

    root@salt01:~# ssh-keygen
    Generating public/private rsa key pair.
    Enter file in which to save the key (/root/.ssh/id_rsa):
    Created directory '/root/.ssh'.
    Enter passphrase (empty for no passphrase):
    Enter same passphrase again:
    Your identification has been saved in /root/.ssh/id_rsa.
    Your public key has been saved in /root/.ssh/id_rsa.pub.
    The key fingerprint is:
    f2:7a:25:5b:36:62:a1:1e:9a:37:c6:c8:5c:0e:72:b0 root@salt01
    The key's randomart image is:
    +--[ RSA 2048]----+
    |                 |
    |     o.o         |
    |     2           |
    |  ....           |
    |   o  . S        |
    |  E o +o  *      |
    |   = X-p.* o     |
    |    * O.o        |
    |     2.o         |
    +-----------------+

I paste my id_rsa.pub to authorized_keys

    root@salt01:~# vi /root/.ssh/authorized_keys

I disable password authentication by editing the sshd configuration file and restarting the service.

    root@salt01:~# vi /etc/ssh/sshd_config
    ...
    PasswordAuthentication no
    ...
    root@salt01:~# service ssh restart
    ssh stop/waiting
    ssh start/running, process 2342

###Install Basic Packages

I install screen and emacs, both useful for managing configuration files and servers.

    root@salt01:~# apt-get install screen emacs

Create emacs configuration file.

    root@salt01:~# touch ~/.emacs

Contents of emacs configuration file.

    (setq backup-directory-alist
              `((".*" . ,temporary-file-directory)))
        (setq auto-save-file-name-transforms
             `((".*" ,temporary-file-directory t)))

    (setq-default indent-tabs-mode nil)
    (setq-default tab-width 4)
    (setq indent-line-function 'insert-tab)

###Install / Configure Nova Client
We need nova client to talk to Rackspace Cloud and get a list of images available. 

    root@salt01:~# pip install rackspace-novaclient

Create a file named nova-credentials 

    root@salt01:~# touch nova-credentials

Content of the file

    # source <this file>
    export OS_AUTH_URL=https://identity.api.rackspacecloud.com/v2.0/
    export OS_AUTH_SYSTEM=rackspace
    export OS_REGION_NAME=DFW
    export OS_USERNAME=<username>
    export OS_TENANT_NAME=<tenant_id>
    export NOVA_RAX_AUTH=1
    export OS_PASSWORD=<api_key>
    export OS_PROJECT_ID=<tenant_id>
    export OS_NO_CACHE=1

The information required above can be found in the Rackspace Cloud Control Panel (mycloud.rackspace.com), when you click the username at the very right top of the screen and click API Keys.

Source the file

    root@salt01:~# source nova-credentials

Test if nova client works fine

    root@salt01:~# nova credentials

###Install Salt Master

    root@salt01:~# apt-get install software-properties-common
    root@salt01:~# add-apt-repository ppa:saltstack/salt
    root@salt01:~# apt-get update
    root@salt01:~# apt-get install salt-master
    root@salt01:~# apt-get install salt-syndic

salt-master must be running at this moment

    root@salt01:~# ps -fe|grep salt
    root     10365     1  0 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10366 10365  2 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10373 10365  0 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10376 10365  0 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10379 10365  5 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10380 10365  5 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10383 10365  5 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10388 10365  5 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d
    root     10391 10365  5 19:07 ?        00:00:00 /usr/bin/python /usr/bin/salt-master -d

###Install Other Essential Components

We need salt-cloud application in order to be able to create cloud servers.

    root@salt01:~# apt-get install python-pip -y
    root@salt01:~# pip install salt-cloud
    root@salt01:~# pip install apache_libcloud
    root@salt01:~# apt-get install sshpass

##Configure salt-cloud for Rackspace Cloud

salt-cloud needs two configuration files in order to be able to create a cloud server instance. One of them is about the provider, the other one is about the type of the server.

###Cloud Provider File

Any file that has .conf extension under the cloud.providers.d folder is considered a cloud provider definition file. The name of the file doesn't matter. Salt uses the content of the file. Let’s define the cloud provider.

    root@salt01:~# mkdir /etc/salt/cloud.providers.d
    root@salt01:~# touch /etc/salt/cloud.providers.d/rackspace.conf

Content of the file

    marconi-test:
      minion:
        master: <IP address of salt master>

      identity_url: 'https://identity.api.rackspacecloud.com/v2.0/tokens'
      compute_name: cloudServersOpenStack
      protocol: ipv4

      compute_region: ORD

      user: <username>
      tenant: <tenant id>
      apikey: <API key>
    
    provider: openstack

(The information required above can be found at the Rackspace Cloud Control Panel when you click the username at the very right top of the screen and click API Keys.)

Above, marconi-test is the name of the provider configuration. Here we have credentials for Rackspace Public Cloud. The Rackspace Cloud is based on OpenStack (it’s the largest OpenStack implementation as of the date this article is written) so we give openstack as the provider which will be used by salt-cloud to determine the right API calls.

###Cloud Profile File

A cloud profile defines type of the instance and the provider for that instance.

Let’s create the folder and file.

    root@salt01:~# mkdir /etc/salt/cloud.profiles.d/
    root@salt01:~# touch /etc/salt/cloud.profiles.d/marconi.conf

in the `marconi.conf` file we will have definitions for cloud server instances. First we need to find out which instance types are available in the Rackspace Cloud.

    root@salt01:~# salt-cloud --list-sizes marconi-test
    openstack
      15GB Standard Instance
        disk: 620
        id: 7
        ram: 15360
        uuid: 0ef9c73c90226fb4e49854943d9b97a42ca75d7a
      1GB Standard Instance
        disk: 40
        id: 3
        ram: 1024
        uuid: 916b53726166c76dc51eeccd7ffc79a337a912bc
      2GB Standard Instance
        disk: 80
        id: 4
        ram: 2048
        uuid: b8122b232b105e228f1fd46488a6f731c877063c
      30GB Standard Instance
        disk: 1200
        id: 8
        ram: 30720
        uuid: a925708be0cf852459a1cc9668b7266704e29b32
      4GB Standard Instance
        disk: 160
        id: 5
        ram: 4096
        uuid: 8f0083f719dbc84a16323b7ad37ef6d0f240dba9
      512MB Standard Instance
        disk: 20
        id: 2
        ram: 512
        uuid: 4fdfc2dbc25fe8e7d640be69eb5e201382996d62
      8GB Standard Instance
        disk: 320
        id: 6
        ram: 8192
        uuid: dcb6758ab0f6a1f88b97ffb1156bcc5e4eac6820

I also need the list of available images.

    root@salt01:~# salt-cloud --list-images marconi-test|grep Ubuntu
      Ubuntu 10.04 LTS (Lucid Lynx)
      Ubuntu 12.04 LTS (Precise Pangolin)
      Ubuntu 12.10 (Quantal Quetzal)
      Ubuntu 13.04 (Raring Ringtail)

The list is very long, so I filtered the output to get a smaller list.

With the information above, I will populate the marconi.conf file. I choose Ubuntu 12.10 as the image. You can use different images as well.
Content of `/etc/salt/cloud.profiles.d/marconi.conf`:

    marconi-test-512MB:
        provider: marconi-test
        size: 512MB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

    marconi-test-1GB:
        provider: marconi-test
        size: 1GB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

    marconi-test-2GB:
        provider: marconi-test
        size: 2GB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

    marconi-test-4G:
        provider: marconi-test
        size: 4GB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

    marconi-test-8G:
        provider: marconi-test
        size: 8GB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

    marconi-test-15GB:
        provider: marconi-test
        size: 15GB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

    marconi-test-30G:
        provider: marconi-test
        size: 30GB Standard Instance
        image: Ubuntu 12.10 (Quantal Quetzal)

##Create a Cloud Server

Let’s create a cloud server using our cloud profile definition.

    root@salt01:~# salt-cloud -l debug -p marconi-test-512MB my-web-server
    {'id': 'ac0c369c-2854-41bf-a71e-18bdf812dc53',
    'image': None,
    'private_ips': [],
    'public_ips': [],
    'size': None,
    'state': 'PENDING'}

Above we asked the Rackspace Cloud to create a cloud server with the specifications defined in marconi-test-512MB section of profiles file named marconi.conf.
After a few minutes, you will see an output like below with the details of the cloud server created.

    my-web-server:
        ----------
        _uuid:
            None
        driver:
        extra:
            ----------
            created:
                2013-06-11T02:44:12Z
            flavorId:
                2
            hostId:
    
            imageId:
                88130782-11ec-4795-b85f-b55a297ba446
            key_name:
                None
            metadata:
                ----------
            password:
                Tv9JL8cHVfh2
            tenantId:
                806067
            updated:
                2013-06-11T02:44:13Z
            uri:
                https://ord.servers.api.rackspacecloud.com/v2/806067/servers/ac0c369c-2854-41bf-a71e-18bdf812dc53
        id:
            ac0c369c-2854-41bf-a71e-18bdf812dc53
        image:
            None
        name:
            my-web-server
        private_ips:
            - 10.177.36.179
        public_ips:
            - 198.61.226.221
            - 2001:4801:7817:0072:53f3:b724:ff10:c544
        size:
            None
        state:
            3

salt-cloud creates the cloud server and installs salt-minion. At this point we have a server that can be managed by Salt. We can install packages, copy files, modify configuration files and stop or start services. 
Let’s do a quick test to see how communication between salt-master and salt-minion is running on my-web-server.

    root@salt01:~# salt 'my-web-server' test.ping
    my-web-server:
        True

All good! In my next post we will start configuring a Marconi environment.
