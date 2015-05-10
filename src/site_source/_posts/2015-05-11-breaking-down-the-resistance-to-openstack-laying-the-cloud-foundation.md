---
layout: post
title: "Breaking Down the Resistance to OpenStack: Laying the Cloud Foundation"
date: 2015-05-11 23:59
comments: true
author: Walter Bentley
published: true
categories:
    - openstack
    - openstack-for-enterprises 
---

As the look and feel of the cloud evolves, matures and hedges toward main stream adoption, the Solution Architects, Developers and Infrastructre engineers of Enterprises are facing the challenge of determing what technologies to consume.  Should I go with something that requires vendor licensing? Or should I look to Open Source technologies, such as OpenStack?  Then if you do decide that OpenStack solves for your technology needs, how best could someone layout its pros and cons to their senior leadership.  

Those of us who have ever had to stand in front of their Director/CTO/CIO and figurtively 'fight' for a particular technology/product, completely understands that this task is not for the meek of heart.  I can remember very vividly holding index cards in my hands with bullet points, as I was attempting to lay out all the reasons why OpenStack should be the companies next major infrastructure shift.  Being prepared for this converstion is critical to the overall enterprises architecture and to articulate clearly why OpenStack is the best choice.  You can never be too prepared.  There will always be questions, you as a technology advocate, will not even think of.  In my opinion being prepared is key.  So let’s start on our technology layer cake.

---
#####Why the Cloud? Why OpenStack?
The foundation of your cake has to be rock solid, or else it will all fall down.  With this it is fundamental to find statistical evidence around the topic.  I would imagine in this day and time, it is not hard to show the value the cloud can have on an IT organization.  Just in case some are still on the fence, here are some stats from a recent report by RightScale published on [Forbes.com](http://www.forbes.com/sites/benkepes/2015/03/04/new-stats-from-the-state-of-cloud-report).

>93% of organizations surveyed are running applications or experimenting with infrastructure-as-a-service

>82% of enterprises have a hybrid cloud strategy, up from 74% in 2014

>88% of organizations are using public cloud while 63% are using private cloud

Among the many advantages around the cloud, these are the ones I always like to emphasize.

* Flexibility - Scale up and down to meet your organization’s requirements. You no longer have to build for the future, or be constrained by decisions made in the past.
* Security - Not in terms of getting more but, proving viable to providing equal security measures that currently existent within virtualization platforms.
* Capacity – People cost and DC footprints reduced.
* Predictable Cost/Cap-Ex Free – Using cloud technology can mean reduced operational costs. No more Cap-Ex expense; predictable spend with base subscription fees.
* Scalability and Elasticity - Environment can grow and stretch on demand
* Reduce Recovery Time - The time to recover from an Infrastructure related outage can be cut in half. NO more spending hours troubleshooting a VM.

Despite all this positive, the RightScale report still has some not so positive stats.  The one that stood out the most to me was:

>68% of enterprises run less than a fifth of their application portfolio in the cloud

>55% of enterprises report that a significant portion of their existing application portfolio is not in cloud, but is built with cloud-friendly architectures

Is something missing from the cloud? Are organizations just dipping their toe in to test the temperature?  Not 100% sure but, what is sure is there is plenty of room for growth around cloud adoption.  Tools like OpenStack provide that additional value, which will help grow the overall cloud adoption.

![OpenStack Saved IT](http://www.hitchnyc.com/content/images/2015/05/Slide07.jpg)

---
#####Tell OpenStack's Story and Evolution
Part of the power of OpenStack is it’s story and how it has evolved just over a short 5 years.  Personally, I am to this day very impressed on how OpenStack came about and who really controls the progress.  For the sake of time, here are the cliff notes:

* July 2010 - Rackspace sought out to create an open source cloud platform, initially focused on fully distributed object storage (know known as Swift).
* October 2010 - the OpenStack project was launched
* January 2011 - NASA was empowered by the US Government to assist in the Open Government Initiative, instructing all Federal agencies to break down barriers to enable transparency, participation, and collaboration between the federal government and the American public.  The output of this was NASA creating a computing platform called Nebula (know known as Nova).
* September 2012 - the OpenStack Foundation was created; for the first two years the project was closely managed by Rackspace and its 25 initial partners before transferring the intellectual property and governance of OpenStack project into the non-profit member run foundation, which since then manages the software and its community.
* The OpenStack community collaborates around a six-month, time-based release cycle with frequent development milestones. During the planning phase of each release, the community gathers for the OpenStack Design Summit to facilitate developer working-sessions and to assemble plans.

---
#####OpenStack Pros & Cons
Let’s be honest, there is no perfect software or platform in this world.  Leading with a technologies flaws is another way of gaining back a small bit of power.  

**Cons**

Being transparent, it is an Open Source technology that is community driven…and at times this can cause pain when implementing, updating or migration to new services.

* Initial install/implementation can be painful
* Services mature over time (do not jump the gun!)
* YOU have to tell OpenStack what you want it to do; certain remediation actions are not automatic and require forethought or automation

**Pros**

* Fully Distributed Architecture
* Scale horizontally using commodity hardware to add additional compute resources
* Add OpenStack control plane nodes to handle needed capacity
* Availability Zones provide compute isolation
* Multi-DCSupport viaRegions
* OpenStack meets High Availability requirements for its own infrastructure services
* Multi-Tenancy Isolation provided by robust Role Based Access Control (RBAC)

---
#####OpenStack's Market Presence
These stats prove that OpenStack is very much an ‘active’ community platform with improvements happening daily, by the people who actually use and believe in the system.

![OpenStack Community Stats](http://www.hitchnyc.com/content/images/2015/05/Slide17.jpg)

![OpenStack Community](http://www.hitchnyc.com/content/images/2015/05/Slide18.jpg)

Another point that really grabbed my attention was the over all IT industry focus on OpenStack this and last year.  Here are some of the resent announcements that all have one deep rooted thing in common, OpenStack:

* EMC acquires Cloudscaling
* HP buys Eucalyptus
* Cisco acquires Metacloud
* Red Hat buys eNovance and InkTank
* Citrix Sponsors OpenStack Foundation

---
#####Have Answers for the Hard Questions
As mentioned earlier, someone in your leadership will begin to pose some ‘in your face’ questions about OpenStack.  Having some answers simulated in your mind will ease this process for your leadership AND yourself.

Using my personal experience at hand, decided to write down some of the questions I was asked.

* This software is Open Source, who is going to support us?
* We are not interested in supporting yet another hypervisor?
* We want to use the cloud but, it has to be in our DC?
* It’s too new for us to use it?
* What are the hardware requirements?
* What Enterprise organizations actually use it?

Now that you have the questions, you can develop some answers.  To help you down the path, thought it would be helpful to share my opinionated feelings on the matter.

***OpenStack is NOT a Hypervisor - OpenStack Nova is a direct manager of hypervisors and built to manage multiple types of hypervisors***

***Flexibility is Power - The flexibility around designing and deploying OpenStack is the power all Infrastructure admins want/need***

***CommodityHardware - OpenStack likes all hardware equally; No more worrying about matching server specs***

***IT Moral Booster - Internal IT will have an opportunity to learn a new technology; increasing their value and increasing productivity***

***5 Years & Going - The OpenStack Community has been up and running for 5 years this year
Managed Cloud built on OpenStack - Support for OpenStack can come from Managed Cloud providers like Rackspace (it would be our pleasure!)***

---
#####Foundation Has Been Laid
You now has been armed with all the ammo to go get your OpenStack cloud.  This last and final slide normally drives home the point flawlessly. 

![OpenStack Enterprise Use Cases](http://www.hitchnyc.com/content/images/2015/05/Slide24.jpg)

It is very hard to deny any of the logos displayed here and challenge their success around technology decisions.  It is the IT equivalent of ‘dropping the mic’ on stage.  Boom!