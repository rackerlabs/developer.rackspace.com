---
layout: post
title: "How did we serve > 20,000 IPython notebooks for Nature readers?"
date: 2015-01-20
comments: true
author: Kyle Kelley
published: true
categories:
    - IPython
    - Science
    - Data Science
    - Docker
    - tmpnb
    - Jupyter
    - Ephemeral
---
The IPython/Jupyter notebook is a wonderful environment for computations, prose, plots, and interactive widgets that you can share with collaborators. People use the notebook [all](http://blog.quantopian.com/quantopian-research-your-backtesting-data-meets-ipython-notebook/) [over](http://nbviewer.ipython.org/github/GoogleCloudPlatform/ipython-soccer-predictions/blob/master/predict/wc-final.ipynb) [the](https://github.com/facebook/iTorch) [place](http://nbviewer.ipython.org/url/norvig.com/ipython/TSPv3.ipynb) across [many varied languages](https://github.com/ipython/ipython/wiki/IPython-kernels-for-other-languages). It gets used by [data scientists](http://nbviewer.ipython.org/gist/wrobstory/1eb8cb704a52d18b9ee8/Up%20and%20Down%20PyData%202014.ipynb), researchers, analysts, developers, and people in between.

As I alluded to in a writeup on [Instant Temporary Notebooks](http://lambdaops.com/ipythonjupyter-tmpnb-debuts/), we (combination of IPython/Jupyter and Rackspace) were prepping for a big demo as part of a [Nature article on IPython Notebooks](http://www.nature.com/news/interactive-notebooks-sharing-the-code-1.16261) by [Helen Shen](https://twitter.com/HelenShenWrites). The impetus behind the demo was to show off the IPython notebook to readers in an interactive format. What better way than to [provide a live notebook server to readers on demand](http://www.nature.com/news/ipython-interactive-demo-7.21492)?

[![Screenshot 2015-01-15 21.17.15.png](https://d23f6h5jpj26xu.cloudfront.net/nvqcj7okftoqw_small.png)](http://img.svbtle.com/nvqcj7okftoqw.png)

To do this, we created a [temporary notebook service](https://tmpnb.org) in collaboration with the IPython/Jupyter team.

### How does this temporary notebook service work?

[tmpnb](https://github.com/jupyter/tmpnb) is a service that spawns new notebook servers, backed by Docker, for each user. Everyone gets their own sandbox to play in, assigned a unique path.

When a user visits a tmpnb, they're actually hitting an [http proxy](https://github.com/jupyter/configurable-http-proxy) which routes initial traffic to tmpnb's orchestrator. From here a new user container is set up and a new route (e.g. `/user/fX104pghHEha/tree`) is assigned on the proxy.

[![tmpnb-setup.gif](https://d23f6h5jpj26xu.cloudfront.net/z9gjan4yftabyq_small.gif)](http://img.svbtle.com/z9gjan4yftabyq.gif)

Working our way up to the Nature demo, we had several live alpha prototypes around the same time at Strata NYC 2014:

* [Paco Nathan](https://twitter.com/pacoid)'s Just Enough Math tutorial at Strata, in coordination with O'Reilly Media/[Andrew Odewahn](https://twitter.com/odewahn)

* tmpnb was announced and used during the very first [PyData talk at Strata NYC 2014](http://strataconf.com/stratany2014/public/schedule/detail/37035) by Fernando Perez

* [Olivier Grisel](https://twitter.com/ogrisel) used it in his scikit learn tutorial

After all the above activity, we learned quite a bit:

### Static assets should be served via nginx instead of within each user container

```
location ~ /(user[-/][a-zA-Z0-9]*)/static/(.*) {
    alias /srv/ipython/IPython/html/static/$2;
}
```

This is both for speed and to use fewer file descriptors across the system. We ended up pulling some neat tricks with our deployment setup to [mount a dummy user container's static files into an nginx container](https://github.com/jupyter/tmpnb-deploy/pull/3). However, if you don't do this, you get the neat side effect of never having caching problems when launching new versions of userland containers, and even means you could ship different ones to different users!

### Websockets and this simple proxy gobble up ports

Each websocket opened by a user ends up opening several ports that will stay open. Since each notebook on a notebook server will open at least one websocket, the number of notebooks you have open directly correlates with the number of websockets.

Here's some trimmed and commented `lsof -i` output when a single user is accessing an IPython 3.x notebook server through tmpnb:

```
# The fixed pieces of networking for tmpnb itself
# Node proxy
node     2646 *:8000 (LISTEN)

# Orchestration layer of tmpnb
python   2669 *:9999 (LISTEN)
python   2669 *:9999 (LISTEN)
node     2646 ip6-localhost:8001 (LISTEN)

# Now for the pieces that each user will be "allocated"
# Established connection with client
node     2646 10.184.2.134:8000->10.223.242.4:53254 (ESTABLI)

# Docker exposes port for IPython notebook server
docker  18173 ip6-localhost:49216 (LISTEN)

# Proxy connects to docker container, keeping port allocated to websocket
node     2646 ip6-localhost:35831->ip6-localhost:49216 (ESTABLI)
docker  18173 ip6-localhost:49216->ip6-localhost:35831 (ESTABLI)

# Docker routes onward to the IPython Notebook
docker  18173 ip6-localhost:49216->ip6-localhost:35673 (ESTABLI)
python   2669 ip6-localhost:35673->ip6-localhost:49216 (ESTABLI)
```

This got really bad when the notebook server had 3 websockets per notebook and users were opening more than a few notebooks during a tutorial. Websockets have to stay established, by nature.

### People want to share notebooks

Honestly, I should know this from working on the [notebook viewer](http://nbviewer.ipython.org/). On several occasions, people have passed me links on the demo version of tmpnb, hosted at tmpnb.org.


```
https://tmpnb.org/user/FKlVK3haOQRF/notebooks/SharingEffects.ipynb
```

While I would *love* to enable people to do this, we probably need an alternate way to share these temporary calculations. I've used it myself and [wished for a way to get it directly on to nbviewer](https://twitter.com/rgbkrk/status/557942542063652864). Posting static content isn't the same as giving someone the ability to remix your code though. For now, this works and has no bearing on the Nature demo. 

### Pool user containers ahead of time

Docker can only boot and route so many containers so quickly. To mitigate this, we created a pool of ready to go userland containers. [@smashwilson](https://github.com/smashwilson) came in and [added the spawn pools](https://github.com/jupyter/tmpnb/pull/69) after we dangled the problem in front of him.

[![pooling.gif](https://d23f6h5jpj26xu.cloudfront.net/jlvadowzumttlg_small.gif)](http://img.svbtle.com/jlvadowzumttlg.gif)

This did introduce problems with initial spawns (Docker failing) and made our [introductory experience with a bit to be desired](https://github.com/jupyter/tmpnb/issues/87).

We learned this the hard way on boot up, having to code around responses from the API and making sure that we block on our own server instead of Docker.

## On to Nature

When Brian Granger (Cal Poly) and Richard Van Noorden (Nature) asked for a demo, it was quite open what that could mean. Do we have people log in to a [JupyterHub](https://github.com/jupyter/jupyterhub) installation? Refer them to [Wakari](https://wakari.io/) or [Sage Math Cloud](https://cloud.sagemath.com/)?

The goal that Richard stated was to provide at most 150 concurrent users. In the back of our minds, we (the IPython/Jupyter project) knew that the initial spike in traffic would be far greater and we should be able to handle the load.

< Note about MozFest, London, Nature offices >

### Redirection



## Closing up

We love IPython notebooks, the overall architecture that has been built out here, and hope to keep supporting Open Source projects do interesting things on the internet in a way that benefits community, technology, and the whole ecosystem.

We didn't expect to build a system that would let people create user environments they could use for teaching and tutorials. I'm incredibly humbled that [Nikolay Koldunov](https://twitter.com/koldunovn) used it for [a tutorial at YaC 2014](http://koldunov.net/?p=950) while it was still in its infancy.
