.. _authenticate:

Authentication
==========================================
To use this service you have to authenticate first. To do this, you will need your Rackspace username and API key.
Your username is the one you use to login to the Cloud Control Panel at http://mycloud.rackspace.com/.

To find your API key, first navigate to the Cloud Control Panel, then click on your **username** at the top right corner, and then finally click on Account Settings. You will be taken to a page that shows your settings. Under Login Details, you can show or reset your **API key**.

You may specify a default **region** as well. Here is a list of available regions:

* DFW (Chicago, IL, US)
* HKG (Hong Kong, China)
* IAD (Blacksburg, VA, US)
* LON (London, England)
* SYD (Sydney, Australia)

Once you have these pieces of information, you can pass them into the SDK by replacing **{username}**, **{apiKey}**, and **{region}** with your info:
