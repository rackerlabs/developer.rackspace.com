---
layout: post
title: "Automate deploying Rackspace Cloud Monitoring agent"
date: 2015-01-06 23:59
comments: true
author: Walter Bentley
published: true
categories:
    - rackspace-cloud-monitoring
    - Ansible
---

So after being asked to do what I considered to be a easy thing, I soon realized that it was not :(. Rather it was easy to do, just not easy to automate doing it. Figured others could benefit from my discoveries. Before getting started, please note these instructions are for RHEL, Fedora and CentOS. Some minor modifications would be needed to accommodate Ubuntu, but the same concepts apply.

Let’s get into some of the prep work that is required.  To install the agent, you need the following: signing key, distro based package, your cloud API key, and your cloud user name.  The signing key and distribution package lists can be found at: http://www.rackspace.com/knowledge_center/article/install-the-cloud-monitoring-agent.  

For testing purposes, I created an Ansible role, for which I had already pulled down the required components for RHEL versions 6/7 and CentOS version 6. If you needed a different Linux version, you need to pull down the key and package for your OS and version. Store the key and package file in the ‘files' directory of the Ansible role.

You can now pass those additional parameters directly when executing the ansible playbook

	$ ansible-playbook -i hosts basecmon.yml --extra-vars \
    “raxcmonrepo=rackspace-cloud-monitoring-centos6.repo \
    raxcmonkey=signing-key-centos6.asc raxkey=1234567890 \
    raxuser=testuser01"

or

you can designate them in a file named ‘localhost’, which should be in the ‘group_vars’ directory of the playbook.  Example text is below.

	# Here are variables related to the play

	raxcmonrepo: rackspace-cloud-monitoring-centos6.repo
	raxcmonkey: signing-key-centos6.asc
	raxkey: 1234567890
	raxuser: testuser01

Out of all of this, the next part is special and allows us to fully automate this process.  The agent setup program is needed to register the server you want monitored with the Cloud Monitoring system.  Per current instructions, you have to enter your Cloud user name and API key when prompted, after running the agent setup.  Then, unfortunately, you get prompted two more times and are asked to create a new agent token and select Cloud Monitoring entities.  To avoid this whole part, I discovered you can pass multiple parameters when running the agent setup program.  This is the top secret part :).  Use the following command with your user name and key:

	$ rackspace-monitoring-agent --setup --username {{ raxuser }} --apikey {{ raxkey }}

By passing the additional parameters, you are not prompted, and the install completes, including adding the new agent token, pre-selecting entities and all.  Ok, so now its time to give it a try.  Once completed, each server should have the Rackspace Cloud Monitoring agent installed and running.  You can log into your Cloud Control Panel, and, under the server details, note that the Monitoring Agent details are marked as ‘Installed’.

**Step 1: Clone the following repo from GitHub *(consider this a working example that you can later alter for your specific needs)*:**

	$ git clone --recursive https://github.com/wbentley15/rax-ansible-cmon-agent.git

**Step 2: Add some server names/IP to the ‘hosts’ file in the root of the playbook directory**

	[testserv]
	testserv-01
	testserv-02

**Step 3: Step 3: Apply parameters to play using one of the follwoing two methods:**
If you designated the parameters into the ‘localhost’ file, use this command:

	$ ansible-playbook -i hosts basecmon.yml

Otherwise, use this command instead:

	$ ansible-playbook -i hosts basecmon.yml --extra-vars \
    “raxcmonrepo=<package file> raxcmonkey=<agent key file> \
    raxkey=<Cloud key> raxuser=<Cloud user name>"