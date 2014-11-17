---
layout: post
title: "Deploying Rackspace Private Cloud v9.0 with Ansible"
date: 2014-11-17 23:59
comments: true
author: Walter Bentley
published: true
categories:
    - Rackspace Private Cloud
    - v9.0
    - Ansible
    - OpenStack
    - Icehouse 
---

Among the many changes made to the reference architecture for the newest release of the Rackspace Private Cloud (RPC v9.0) to improve stability, we also decided to change the approach in which we deploy the cloud internally and for anyone interested in running the Rackspace private cloud.  The decision to use Ansible going forward was based on two major thoughts: ease of deployment and flexible configuration.  Ansible made it very easy for Rackspace to simplify the overall deployment and give users the ability to reconfigure the deployment as needed to fit their environments.  Are you familiar with Ansible?  If yes…skip the next paragraph and if not, please read on.

####What is Ansible?
**Ansible** is an IT automation tool that can be used to configure, deploy and orchestrate many different Infrastructure based tasks.  Such examples are: system configuration, software deployment, application or infrastructure orchestration and yes eventually replace most infrastructure folks…*just kidding…maybe*?  With my many years of supporting Production based applications…with tools like Ansible on the rise, the writing would have certainly been on the wall.  <u>Major word of advice for Infrastructure engineers…LEARN one of the many orchestration tools and begin to love the term DevOps.</u>  

As stated in Ansible’s main delivery statement: 
>first and foremost is simplicity and maximum ease of use.  

They have certainly accomplished this in a great way!  My time spent working and learning Ansible has been time very well spent.  

Here are a few facts about Ansible that is useful to know:

   * It is an Open Source project, source is available on GitHub
   * No remote daemon or agent is required
   * Manages systems using Secure Shell (SSH) and prefers pre-shared keys (a very cloudy approach and yes it does work with traditional authentication also)
   * Code is organized into something called, playbooks, of which is written in the standard YAML language
   * Has built in modules that work with various cloud providers (including Rackspace) and to do many administrative tasks
   * If you run into an issue and/or potential bug, do not be shy…report it to them via GitHub…you will be shocked in how quickly they respond

You can find more information about Ansible on their [Intro](http://docs.ansible.com/intro.html) and [Best Practices](http://docs.ansible.com/playbooks_best_practices.html) page.
</br>
####How do you get started?
Since all the OpenStack deployment and environment configuration playbooks are already created, you need to start by provisioning your environment.  

**To run RPC v9.0 you need a minimum of:**


   * Deployment Node
   * Infrastructure Node (Control Plane)
   * Logging Node
   * Compute Node
   * Storage Node *(optional and only needed if your going to try out Cinder block storage)*

Personally I have found a few create ways of doing this locally on my workstation.  For this article we will try the 100% Open Source approach by using **VirtualBox**, **Vagrant** and of course **Ansible**.  

Follow the steps below to install Rackspace Private Cloud v9.0.  Keep in mind the full installation guide can be found at: http://docs.rackspace.com/rpc/api/v9/bk-rpc-installation/content/rpc-common-front.html

</br>
####Step 0: Prerequisites


   * The machine where RPC is being deployed must have internet connectivity
   * Machine with at least 8GB RAM, processors with hardware virtualization capability
   * Git, [Virtualbox](https://www.virtualbox.org/manual/ch02.html), [Vagrant](https://docs.vagrantup.com/v2/installation/index.html) and [Ansible](http://docs.ansible.com/intro_installation.html) installed

####Step 1: Provision Target Environment

Clone the below two repositories to pull down the preconfigured Vagrant files:

	$ git clone --recursive https://github.com/wbentley15/vagrant-rpcv901_deploy.git
	$ git clone --recursive https://github.com/wbentley15/vagrant-rpcv901.git

Change into the ‘vagrant-rpcv901’ directory and execute the 'vagrant up' command:
	
    $ vagrant up

This will provision the deployment, infrastructure, and logging node.  Also, install base required software and configuration need to prepare the target host for RPC.  Feel free to adjust the Vagrant file to increase RAM available for a particular node and/or add vCPU capacity.  If you plan to deploy with Cinder also, please refer to the full installation guide for details on how to do that.

####Step 2: Provision Deploy Environment

Change into the ‘vagrant-rpcv901_deploy’ directory and execute the ‘vagrant up’ command:

	$ vagrant up

This will provision the deployment node.  Also, install base required software and configuration need to deploy RPC to target hosts.

*There are two RPC configuration files that you can configure to make changes to how your RPC environment is deployed and default values for setting up OpenStack and supporting software (i.e. MariaDB and RabbitMQ).  Those two configuration files are found on the deployment node in the '/etc/rpc_deploy’ directory and are named: rpc_user_config.yml and user_variables.yml. No changes are needed if following these instructions.*

####Step 3: Start RPC Deployment

SSH into the deployment node (deploy1) and change to the /opt/ansible-lxc-rpc/rpc_deployment/ directory:

	$ vagrant ssh deploy1
    $ sudo su
	$ cd /opt/ansible-lxc-rpc/rpc_deployment/


Perform an fping to all target hosts from deployment node (this is to ensure connectivity):

	$ fping 172.29.236.2 172.29.236.5 172.29.236.6 172.29.236.10



If response to all target hosts was successful, you can start executing the following playbooks.


The first playbook will setup the target hosts with required software repos, create the LXC containers and validate network configuration: *(runtime 15-20 minutes)*

	$ ansible-playbook -e @/etc/rpc_deploy/user_variables.yml playbooks/setup/host-setup.yml

Since the v9.0 reference architecture expects a distributed control plane with a Load Balancer in front, we need to install a load balancer (this playbook will install HAProxy and configure required OpenStack backends): *(runtime 5 minutes)*

	$ ansible-playbook -e @/etc/rpc_deploy/user_variables.yml playbooks/infrastructure/haproxy-install.yml

This playbook installs all support software required to run OpenStack and any additional tools used with RPC: *(runtime 20-25 minutes)*

	$ ansible-playbook -e @/etc/rpc_deploy/user_variables.yml playbooks/infrastructure/infrastructure-setup.yml

The final and last playbook will build and install OpenStack components: *(runtime 30-35 minutes)*

	$ ansible-playbook -e @/etc/rpc_deploy/user_variables.yml playbooks/openstack/openstack-setup.yml


Pending all play books finish with no failures, your environment should be up and running...**Congratulations!**

Now from your browser connect to the deployment node (default IP address for the deployment node is 172.29.236.7, unless changed in the Vagrant file) for the “Horizon" dashboard:  https://172.29.236.7.  The default username is “admin” and default password can be found in the '/etc/rpc_deploy/user_variables.yml' file on the deployment node.
</br>

---
####Issues and Trouble Spots
First clear message I want to deliver is…if you run into deployment issues please make sure to ask for help and/or report the bug.  This can be done via our GitHub page for Rackspace Private Cloud: http://rcbops.github.io/ansible-lxc-rpc/

</br>
#####Playbooks continuously fail:
If you find that the playbooks continuously fail in different places, you should probably adjust how many parallel tasks Ansible is allowed to run.  This can be done by editing /opt/ansible-lxc-rpc/rpc_deployment/ansible.cfg and change the following values (the default value is 25 but, I normally adjust it to 5)

	from: forks = 25 
	to: forks = 5

#####Ansible SSH failure connecting to target hosts:
This issue is normally tied to someone doing multiple RPC environment deployments due to failures or etc and re-provisioning the environment.  There is a very simple reason and fix to this.  On the local machine you need to remove the ‘known_hosts’ file located in the ‘~/.ssh’ directory.  Do not worry you will not miss this file :).

	$ rm ~/.ssh/known_hosts

#####Glance container not starting:
This one has been giving me a hard time here lately.  This is based on an internal datacenter dependency required for internal deployments.  


Connect to the Glance container on the Infrastructure node, remove the Glance logs and start Glance services

	$ lxc-ls --fancy
	$ lxc-attach -n <glance container name from above> -e -- rm -rf /var/log/glance/*.log
	$ service glance-registry start
	$ service glance-api start


If you still run into issues, let me know and I will share another set of instructions on how to get past it.  We plan to fix this in future releases.

#####Fixing broken PIP repos :
On the deployment node, edit /opt/ansible-lxc-rpc/rpc_deployment/inventory/group_vars/all.yml and change the following values

	rpc_repo_url: "http://rpc.cloudnull.io" 
	rpc_release: "9.0.1"