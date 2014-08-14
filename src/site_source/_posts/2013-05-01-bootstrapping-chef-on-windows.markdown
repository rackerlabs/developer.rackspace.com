---
layout: post
title: "Bootstrapping Chef on Windows"
date: 2013-05-01 09:25
comments: true
author: Edward Adame
published: true
categories: 
- Chef
- Windows
---
{% img right 2013-01-09-cooking-with-chef/chef_logo.png "Chef Logo" %}
Windows presents some challenges when it comes to using deployment automation tools.  If you’ve used 'knife-rackspace' to create Linux servers on our cloud, you know that the bootstrap process happens automatically.  The server is created, a connection is made via SSH, the Chef client is installed, and the server role is assigned… all with a single command.  If you spin up a Windows server, knife-rackspace still attempts to bootstrap the server via SSH… and this will obviously fail.  So what next?  What are your options, and which approach will produce the best results?

<!-- more -->
 
Picking the "best" method is probably a matter of opinion, so I’ll present a few options for your consideration.  You have to log into the server to take the next step, but you’ll obviously want to minimize the amount of work needed to get the server to a production state.  Here are the methods I discovered:
 
1. Log into the server and install the Chef client by hand.
2. Log into the server and configure winrm to allow remote administration.  Use knife-windows to bootstrap the server from your workstation via the winrm protocol.  You’ll need to consider security if taking this approach, which will require at a minimum a self-signed certificate for communication over HTTPS.  You could also use knife-windows on a different cloud server and communicate across the private 10.x.x.x network, thereby reducing security concerns.
3. Log into the server and install an ssh service (there are several free options for you to choose from).  Use knife-windows to bootstrap the server from your workstation via the ssh protocol.  Communication with the server will be encrypted, however, you may not wish to rely on non-native software.  You may also not want to leave the service running, though you could potentially disable or remove it once the bootstrap process completes.
 
Option 1 may seem tedious to you, but what if we configure the client by hand and then clone the server with the client already installed?  At least with this approach you do not have to duplicate your efforts on subsequent server builds.  This presents a few different things to consider:
 
1. You can shut a server down, create an image, and then deploy a new server from that image… however, if you do this without first running sysprep you will end up with servers that have duplicate SIDs.  This may not be an issue for you, but it’s a practice that I would generally avoid for safety’s sake.
2. If you simply run the sysprep command, shut the server down, and create an image, building a new server from this image will fail.  Fortunately, there is a workaround.   From a command prompt or PowerShell, check out the contents of c:\windows\system32\sysprep.  Within it is a batch file named RunSysprep_2.cmd, and if you run this rather than just using the sysprep command directly, things will work as expected.
 
We now have an approach that allows us to make a totally generic server template with both Server 2008 and 2012.  It’s also the approach I prefer because it allows me to configure the server in any number of ways.  I can install the Chef client… or forego a deployment automation tool altogether and simply perform my software installation by hand.  For smaller deployments, this may be a totally reasonable thing to do.
 
If you ever have to restart the original server, just keep in mind the fact that the server will be generalized and no longer have IP addresses assigned.  Use the Rackspace Control Panel to ‘Connect via Terminal’, complete the abbreviated setup process, and reassign your designated IP’s once you log in.  Once you do that, you can connect via RDP as usual.
 
In my next entry I will focus on building a clean Windows Server template and deploying new servers using the Nova client.  Stay tuned for details…