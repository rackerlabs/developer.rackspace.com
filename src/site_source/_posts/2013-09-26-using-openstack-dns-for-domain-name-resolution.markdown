---
layout: post
title: "Using Openstack DNS for Domain Name Resolution"
date: 2013-09-26 08:00
comments: true
author: Tim Simmons
published: true
categories: 
- OpenStack
- DNS
- Designate
---
*Last week, you saw a guide on [Getting Started with OpenStack and Designate][11] that covered the basic setup for getting a Domain Name Server up and running using Openstack DNS's solution, Designate. Today we'll dive into actually using Designate to resolve your domain names.*
 
In my opinion the best way to learn about a project is to use the software yourself. In that spirit, I’m going to show you how to use Designate as a name server for your own domains and servers.

<!-- more -->

Here is a very basic picture of what we’ll be doing:
 
![diagram][1]
 
### What You’ll Need
 
* 2 Domains. You must own them or have the ability to set their name servers, a common option on sites where you register domains. If you don’t have any, I recommend registering with [namecheap.com][2]. They come in handy, perhaps you’d like to get some variations of your name, or your kids’ names. One of these will be a name server. So you won’t be able to use it to post pictures of your cats, keep that in mind.
* Access to a server with an externally visible IP Address. I recommend a [Cloud Server][3] from Rackspace. You can spin one up pretty cheaply, or get a [developer discount][12].
* Access to a DNS Service. Again I recommend the free Rackspace [Cloud DNS][4]
 
### Setting Up Server/Records
 
First, you’ll need the IP of your server. This means that if you don’t have it already you’ll need to spin up your Cloud Server. Using Rackspace Cloud Servers, spin up a 512 MB, 20 GB, 12.04 Ubuntu Cloud Server. Note the password and IP Address.
 
I’m going to assume you use Rackspace Cloud DNS and Namecheap. But everything I’m doing here is straightforward with other services.
 
Once you have your domains pick one of them to use for your name servers, meaning all domains you would like to use Designate to administer DNS for will point to a variation of this domain. When you’ve done that, we need to point this domain to your Designate Cloud Server.

In the Cloud DNS section of the Rackspace Cloud Control Panel, create a domain.
 
In the domains, create an A(Address) Record that points to your Cloud Server. Like so:
![Screen Shot 2013-07-26 at 3.45.07 PM][5]
 
I recommend setting up two A records for subdomains that you can set your nameservers to.
 
In Hostname you would enter 'ns1' and drop the IP address of your Cloud Server into the Target field. Then make another record and do the same thing, perhaps with 'ns2' for the hostname.
 
Alternatively, if you have two extra domains lying around, you can simply set up A records with no Hostname field, and point each one to your Cloud Server.
 
### Setting Name Servers
 
Now go to [Namecheap][6]&nbsp;(or whatever site you used to register your Domains) and log in through the ‘My Account’ button on the right side of the toolbar. When you’ve done so, hover over that button and choose ‘Manage Domains’
 
From the listed Domains, choose the ones that you just set up records for in Cloud DNS. You’ll want to choose Domain Name Server Setup.

![Screen Shot 2013-07-26 at 3.54.10 PM][7]
 
Here you’ll need to set (for each of your name server domain) your name servers as shown below (assuming you’re using Rackspace Cloud DNS:
 
![Screen Shot 2013-07-26 at 3.58.02 PM][8]
 
For your other domain, the one that will use Designate for its DNS. You’ll need to set its name servers to the domains that you’ve decided will be name servers.
 
So instead of what you see above, the name servers will be set to the other domain (or variations of the domain) you own.
 
In the example I talked about above, you would set your name servers (again for the domain that will use Designate for DNS) to ns1.domain.com and ns2.domain.com.
 
### Setting Up Designate
 
You now need to install/configure Designate. Feel free to follow my guide [here][9]&nbsp;but I would recommend simply running a script.
 
Once you log into your server, you can run the script by executing the following commands:
 
 
```
apt-get install git
git clone https://github.com/stackforge/designate.git
git clone https://github.com/TimSimmons/designate-script.git
cd designate-script
chmod +x script.sh #Update permissions
export DESIGNATE_SRC="/var/lib/designate"
./script.sh # Execute the script
```

Designate should be running now, you’ll see the text from everything being started.
 
It's important to note here that this setup doesn't include authentication or some of the features that you would have in a true Designate architecture, it's merely meant as a demonstration.
 
### Putting Information in Designate
 
Now you need to tell Designate about the Domain that you’re pointing to Designate as a name server. This is relatively simple. Open up a HTTP Client, I use [Dev HTTP Client][10]&nbsp;and point it at:
 
``` 
HTTP your.ip.address:9001/v1/GET
Accept: application/json
Content-Type: application/json
```

```
#Response
{
	"versions": [
		{
		"status": "CURRENT",
		"id": "v1"
		}
	]
}
```
Now we’ll create a server, domain, and A record for the site.
Name your server the same as your domain that you’re using for the name server.
 
 
```
HTTP your.ip.address:9001/v1/servers POST
Accept: application/json
Content-Type: application/json
{
	"name": "ns1.whatever.com"
}
```

```
#Response
{
	"id": "ecc3f3e2-b2c6-4c39-a9e8-1ef0d97aad48",
	"created_at": "2013-07-29T15:09:03.359742",
	"updated_at": null,
	"name": "ns1.whatever.com."
}
```
Name your domain properly for the one that you’d like to use Designate for DNS.&nbsp;
 
``` 
HTTP your.ip.address:9001/v1/domains POST
Accept: application/json
Content-Type: application/json
{
	"name": "domain1.com.",
	"ttl": 3600,
	"email": "nsadmin@example.org"
}
``` 
 
```
#Response
{
	"id": "89acac79-38e7-497d-807c-a011e1310438",
	"name": "domain1.com.",
	"ttl": 3600",
	"serial": 1351800588,
	"email": "nsadmin@example.org"
	"created_at": "2012-11-01T20:09:48.094457"
}
```
Now create an A Record for the domain you just created pointing to something, perhaps Google (74.125.224.72) or another website you own.
 
``` 
HTTP your.ip.address:9001/v1/domains/id/records POST
Accept: application/json
Content-Type: application/json
{
	"name": "www.domain1.com.",
	"type": "A",
	"data": "74.125.224.72"
}
``` 
```
#Response
{
	"id": "2e32e609-3a4f-45ba-bdef-e50eacd345ad",
	"name": "www.domain1.com.",
	"type": "A",
	"created_at": "2012-11-02T19:56:26.366792",
	"updated_at": null,
	"domain_id": "89acac79-38e7-497d-807c-a011e1310438",
	"ttl": 3600,
	"data": "74.125.224.72",
}
``` 
###Conclusion
 
That’s it! Your domain should now resolve to whatever IP address you've entered into Designate above.
 
To summarize, we’ve set up a server that runs Designate, pointed two domains(or variations of a single domain) to that server using Rackspace Cloud DNS, then pointed another domain’s name servers to those domains (which is to say pointing to Designate), and added some information to Designate to allow our third domain to be served.
 
If you have any issues, feel free to email me at tim.simmons@rackspace.com
 
###About the Author
Tim Simmons is a Rackspace intern on the Cloud DNS team. Recently the team evaluated the OpenStack DNSaaS solution, Designate. Tim took an active role in the investigation; writing a "Getting Started" guide and this guide, among other things. Tim continues to play an essential role in our next generation DNS offering.
 
[1]: http://timsimmons.me/wp-content/uploads/2013/07/diagram1.jpg
[2]: http://namecheap.com (Namecheap)
[3]: http://www.rackspace.com/cloud/servers/ (Cloud Servers)
[4]: http://www.rackspace.com/cloud/dns/ (Cloud Dns)
[5]: http://timsimmons.me/wp-content/uploads/2013/07/Screen-Shot-2013-07-26-at-3.45.07-PM.png
[6]: http://namecheap.com
[7]: http://timsimmons.me/wp-content/uploads/2013/07/Screen-Shot-2013-07-26-at-3.54.10-PM.png
[8]: http://timsimmons.me/wp-content/uploads/2013/07/Screen-Shot-2013-07-26-at-3.58.02-PM.png
[9]: http://timsimmons.me/getting-started-with-openstack-and-designate
[10]: https://chrome.google.com/webstore/detail/dev-http-client/aejoelaoggembcahagimdiliamlcdmfm?hl=en (Dev HTTP Client)
[11]: http://developer.rackspace.com/blog/getting-started-with-openstack-and-designate.html
[12]: http://developer.rackspace.com/blog/developer-love-welcome-to-the-rackspace-cloud-developer-discount.html
