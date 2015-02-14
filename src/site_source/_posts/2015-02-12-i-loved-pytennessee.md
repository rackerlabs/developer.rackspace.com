---
layout: post
title: "I Loved PyTennessee 2015"
date: 2015-02-12
comments: true
author: Kyle Kelley
published: true
categories:
    - Python
    - Tennessee
    - PyTennessee
    - Conferences
---

[Python Tennessee](https://www.pytennessee.org/) was a wonderfully put together conference with a great variety of speakers.

{% img 2015-02-12-i-loved-pytennessee/deploy_in_the_back.png %}

<!-- more -->

## Opening Keynote

{% img 2015-02-12-i-loved-pytennessee/pytn_audience.jpg %}

Giving a talk at PyTennessee was wonderful. The audience was warm, receptive, and laughed at my jokes (even the impromptu ones). Nashville in general was a really polite and courteous place to be. I'll definitely come back here.

Largely speaking, I talked about the ephemeral docker workflows I've been working on with [Jupyter](https://github.com/jupyter) and with the [cloudpipe project](https://github.com/cloudpipe). This included a bit on Docker, the [Nature demo](https://developer.rackspace.com/blog/how-did-we-serve-more-than-20000-ipython-notebooks-for-nature/), [tmpnb](https://github.com/jupyter/tmpnb), PiCloud, multyvac, and the future with cloudpipe. The [slides for my talk are available on speakerdeck](https://speakerdeck.com/rgbkrk/ephemeral-docker-workloads-with-jupyter-at-pytn).

After my talk, there was plenty of time for questions. The questions posed at the beginning and throughout the conference were really illuminating. The recurring questions were all around multi user notebooks:

* How will this work for educational purposes?
* Can we see [Google Drive](https://github.com/jupyter/jupyter-drive) and [tmpnb](https://github.com/jupyter/tmpnb) get integrated?
* When will we see a release of [JupyterHub](https://github.com/jupyter/jupyterhub) (the multi user notebook server)?

Since that was a big topic, I showed the audience the JupyterHub that is currently run by Jess Hamrick for her Computational Models class.

{% img 2015-02-12-i-loved-pytennessee/jupyterhub_login.png  %}

Apparently my timing was good, as I snuck it in just before planned maintenance on the JupyterHub setup:

{% img 2015-02-12-i-loved-pytennessee/planned_maintenance.png %}

People were astounded to see this in action. [Brian J. Geiger](https://twitter.com/thefoodgeek) from the Center for Open Science was eager to see this for their team and is making me think hard about how multi user notebook servers can be set up. [Carol Willing](https://twitter.com/WillingCarol) professed her love of tmpnb.org for making zero-to-lets-go with workshops be near 0 seconds.

Additionally, several people came up to me to exclaim their excitement for [the cloudpipe project](https://github.com/cloudpipe) and bemoan the loss of PiCloud. There is definite excitement for an open source version to come online. It makes me hopeful CloudPipe can build a great community. All the current services propping up are making me wonder if PiCloud was just too early for its time. Another hypothesis is that charging margins for compute on top of cloud services is mighty difficult if you're not the hosting provider itself or running it on your own infrastructure.

In addition to all that, people asked if I could help them debug their Docker setups. I'm always game for some helping. It's fun!

There were some fun tweets during and afterward, but one tweet really summed it up:

<blockquote class="twitter-tweet" lang="en"><p>Reading through tmpnb code after <a href="https://twitter.com/rgbkrk">@rgbkrk</a>&#39;s talk <a href="https://twitter.com/PyTennessee">@PyTennessee</a>. I&#39;m impressed how simple python+docker made creating such an amazing service!</p>&mdash; sciurus (@sciurus) <a href="https://twitter.com/sciurus/status/564100686888337411">February 7, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

It really got at why I was *so* excited to talk about building services with Docker and Python. :) Containers are such a wonderful building block and Docker provides a great API to build **really interesting** systems. As we see this space get built out further, we'll see all sorts of interesting applications come out.

## The Glorious Food

The PyTennessee organizers taunted everyone by Twitter by tweeting and retweeting about the food that was present throughout the conference.

### Day 0

<blockquote class="twitter-tweet" lang="en"><p>Sprints food is amazing! Handmade tacos! Yummy! <a href="http://t.co/Uaki9YbrLH">pic.twitter.com/Uaki9YbrLH</a></p>&mdash; PyTN (@PyTennessee) <a href="https://twitter.com/PyTennessee/status/563847755253448705">February 6, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

### Day 1

<blockquote class="twitter-tweet" lang="en"><p>Breakfast is a wrap here at <a href="https://twitter.com/PyTennessee">@PyTennessee</a>. <a href="http://t.co/Fm6MuEGjNJ">pic.twitter.com/Fm6MuEGjNJ</a></p>&mdash; sifted (@siftedco) <a href="https://twitter.com/siftedco/status/564100088545304576">February 7, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" lang="en"><p>Lunch is just about over here at <a href="https://twitter.com/PyTennessee">@PyTennessee</a>. Hearing some great chatter about the sessions. <a href="http://t.co/LFc7HEROUf">pic.twitter.com/LFc7HEROUf</a></p>&mdash; sifted (@siftedco) <a href="https://twitter.com/siftedco/status/564134854124523520">February 7, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" lang="en"><p>Great lunch from <a href="https://twitter.com/siftedco">@siftedco</a> for <a href="https://twitter.com/PyTennessee">@PyTennessee</a> <a href="https://twitter.com/hashtag/pytn2015?src=hash">#pytn2015</a> <a href="http://t.co/Odr27D31U6">pic.twitter.com/Odr27D31U6</a></p>&mdash; Douglas Starnes (@poweredbyaltnet) <a href="https://twitter.com/poweredbyaltnet/status/564129900559613952">February 7, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" lang="en"><p>Burmese pork curry over basmati rice &amp; chopped salad with peanuts &amp; lime vinaigrette. Come get some <a href="https://twitter.com/PyTennessee">@PyTennessee</a> <a href="http://t.co/I2kEsqsbHJ">pic.twitter.com/I2kEsqsbHJ</a></p>&mdash; sifted (@siftedco) <a href="https://twitter.com/siftedco/status/564214595644973056">February 8, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

### Day 2

<blockquote class="twitter-tweet" lang="en"><p>Day 2 here at <a href="https://twitter.com/PyTennessee">@PyTennessee</a> <a href="http://t.co/bqfdRbxkub">pic.twitter.com/bqfdRbxkub</a></p>&mdash; sifted (@siftedco) <a href="https://twitter.com/siftedco/status/564429136672722944">February 8, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

<blockquote class="twitter-tweet" lang="en"><p><a href="https://twitter.com/PyTennessee">@PyTennessee</a> great lunch today! Honey Chicken was awesome! <a href="http://t.co/gXbboFglEC">pic.twitter.com/gXbboFglEC</a></p>&mdash; jayrodau (@jayrodau) <a href="https://twitter.com/jayrodau/status/564494507295916032">February 8, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

To quell your inner hunger as foodie and programmer, [Adrienne Lowe](https://twitter.com/adriennefriend) gave a [talk on pairing recipes with fine exercises from Learn Python the Hard Way](https://www.pytennessee.org/schedule/presentation/76/).

<blockquote class="twitter-tweet" lang="en"><p>. <a href="https://twitter.com/adriennefriend">@adriennefriend</a> is talking about cooking AND programming at <a href="https://twitter.com/PyTennessee">@PyTennessee</a> :-) <a href="http://t.co/wgCxcP7yl1">pic.twitter.com/wgCxcP7yl1</a></p>&mdash; Chris Armstrong (@radix) <a href="https://twitter.com/radix/status/564514572070309889">February 8, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

## Talks I attended

### [Cutting Off the Internet: Testing Applications that Use Requests](https://www.pytennessee.org/schedule/presentation/54/)

[Ian Cordasco](https://twitter.com/sigmavirus24) spoke on the many learning lessons he experienced in testing [github3.py](https://github.com/sigmavirus24/github3.py)

<blockquote class="twitter-tweet" lang="en"><p>Ways to .....FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFail at testing with <a href="https://twitter.com/sigmavirus24">@sigmavirus24</a> <a href="https://twitter.com/hashtag/pytn?src=hash">#pytn</a> <a href="http://t.co/srDK17EsFV">pic.twitter.com/srDK17EsFV</a></p>&mdash; Kyle R Kelley (@rgbkrk) <a href="https://twitter.com/rgbkrk/status/564154607744856064">February 7, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

...as well as [betamax](https://github.com/sigmavirus24/betamax), a VCR he wrote for Python requests (in the style of Ruby's VCR gem).

[Slides here](https://speakerdeck.com/sigmavirus24/cutting-off-the-internet-testing-applications-that-use-requests)

### [Why Your Next API Should Be Designed By A Linguist](https://www.pytennessee.org/schedule/presentation/82/)

[Rebecca Standig](https://twitter.com/understandig) made people think hard about how they design APIs and why language matters.

{% img 2015-02-12-i-loved-pytennessee/why_linguists_for_apis.jpg %}

<blockquote class="twitter-tweet" lang="en"><p>&quot;Learning an API should be intuitive - not hard to figure out. Choose your words intentionally.&quot; <a href="https://twitter.com/understandig">@understandig</a> <a href="https://twitter.com/PyTennessee">@PyTennessee</a> <a href="https://twitter.com/hashtag/pytn2015?src=hash">#pytn2015</a></p>&mdash; Aubrey Howell (@SimplyAubs) <a href="https://twitter.com/SimplyAubs/status/564185914860371969">February 7, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

### [Docker in Action - fitter, happier, more productive](https://www.pytennessee.org/schedule/presentation/51/)

Michael Herman gave the audience a wonderful introduction to Docker, services around Docker, and how to deploy a simple Flask App.

### [Twitter Network Analysis with NetworkX](https://www.pytennessee.org/schedule/presentation/52/)

{% img 2015-02-12-i-loved-pytennessee/networkx_students.jpg %}

This talk/tutorial stepped up their game by providing a handout.

{% img 2015-02-12-i-loved-pytennessee/handout.jpg %}

## Closing up

PyTennessee was incredible and as I always end up seeing, bigger than I expected for a regional conference. Hats off to the organizers for putting together such a great conference.

<blockquote class="twitter-tweet" lang="en"><p>The awesome pano I took during my talk at the fantastic <a href="https://twitter.com/PyTennessee">@PyTennessee</a> <a href="https://twitter.com/hashtag/pytn?src=hash">#pytn</a> <a href="http://t.co/vllC5AG6go">pic.twitter.com/vllC5AG6go</a></p>&mdash; sudo bake me a cake (@roguelynn) <a href="https://twitter.com/roguelynn/status/564567652442861568">February 8, 2015</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>

I can't wait until PyTennessee 2016!

{% img 2015-02-12-i-loved-pytennessee/offtopytn.png %}
