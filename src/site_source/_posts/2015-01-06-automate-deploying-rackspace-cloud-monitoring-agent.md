---
layout: post
title: "Automate deploying Rackspace Cloud Monitoring agent"
date: 2015-01-06 23:59
comments: true
author: Walter Bentley
published: true
categories:
    - Rackspace Cloud Monitoring
    - Ansible
---

So after being asked to do what I considered to be a easy thing…soon realized that it was not :(.  Rather it was easy to do just not easy to automate doing it.  Figured other could benefit from my discoveries.  Before getting started please note these instructions are for RHEL, Fedora and CentOS.  Some minor modifications would be needed to accommodate Ubuntu but, overall still possible.

Let’s get into some of the prep work that is required.  To install the agent you need the following: signing key, distro based package, your cloud API key and your cloud user name.  The signing key and distribution package lists can be found at: http://www.rackspace.com/knowledge_center/article/install-the-cloud-monitoring-agent.  

For testing purposes I created an Ansible role, of which I have already pulled down the required components for RHEL versions 6/7 and CentOS version 6.  Next step would be to pull down the key and package, if you needed a different Linux version.  Store the key and package file in the ‘files' directory of the Ansible role.

You can now pass those additional parameters directly when executing the ansible playbook

	$ ansible-playbook -i hosts basecmon.yml --extra-vars \
    “raxcmonrepo=rackspace-cloud-monitoring-centos6.repo \
    raxcmonkey=signing-key-centos6.asc raxkey=1234567890 \
    raxuser=testuser01"

or

designate them in a file named ‘localhost’ saved into the ‘group_vars’ directory of the playbook

	# Here are variables related to the play

	raxcmonrepo: rackspace-cloud-monitoring-centos6.repo
	raxcmonkey: signing-key-centos6.asc
	raxkey: 1234567890
	raxuser: testuser01

Out of all of this…the next part is the part that is special and allows for the ability to fully automate this process.  The agent setup program is needed to register the server you want monitored with the Cloud Monitoring system.  Per current instructions you have to enter your Cloud user name and API key when prompted, after running the agent setup.  Then unfortunately prompted two more times to ask to create a new agent token and select Cloud Monitoring entities.  To avoid this whole part, I discovered you can pass multiple parameters when running the agent setup program.  This is the top secret part :).  The below command is what is used in the role.

	$ rackspace-monitoring-agent --setup --username {{ raxuser }} --apikey {{ raxkey }}

By passing the additional parameters you are not prompted and the install completes including adding the new agent token, pre-selecting entities and all.  Ok, so now its time to give it a try.  Once completed each server should have the Rackspace Cloud Monitoring agent installed and running.  You can log into your Cloud Control Panel, under the server details you will note the Monitoring Agent details will show marked as ‘Installed’.

**Step 1: Clone the following repo from GitHub *(consider this a working example that you can later alter for your specific needs)*:**

	$ git clone --recursive https://github.com/wbentley15/rax-ansible-cmon-agent.git

**Step 2: Add some server names/IP to the ‘hosts’ file in the root of the playbook directory**

	[testserv]
	testserv-01
	testserv-02

**Step 3: Use the following command if you designated the parameters into the ‘localhost’ file:**

	$ ansible-playbook -i hosts basecmon.yml

OR If not, please execute this command instead:

	$ ansible-playbook -i hosts basecmon.yml --extra-vars \
    “raxcmonrepo=<package file> raxcmonkey=<agent key file> \
    raxkey=<Cloud key> raxuser=<Cloud user name>"