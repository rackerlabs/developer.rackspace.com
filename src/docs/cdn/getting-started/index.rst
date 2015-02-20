.. _quickstart:
============================
Quickstart for Rackspace CDN
============================
Rackspace CDN allows you to CDN enable your website.  It offers features
such as pulling content from your website's origin servers, caching rules,
access restrictions, and purging content from the CDN's edge servers.

Differences from Cloud Files
============================

* Users of Cloud Files can CDN-enable a container, thereby distributing the contents 
  of that container to the CDN's edge nodes. In comparison, users of Rackspace CDN
  specify the origins that host the content, and the CDN pulls the content from these
  origins. Origins may refer to Dedicated Servers, Cloud Servers, Cloud Load Balancers,
  or even servers hosted outside of Rackspace. It is not yet possible to specify a
  Cloud Files container as an origin.

* Cloud Files limits the number of purges per account, per day to 25. Rackspace CDN
  has no such limit on purges.

* Cloud Files supports streaming video from CDN-enabled containers as well as serving
  CDN-enabled content over SSL/TLS. Rackspace CDN does not yet support either of these
  features.

Concepts
========

CDN
    A content delivery network (CDN) is a system of multiple computers that
    contains copies of data stored at various network nodes. A CDN is designed
    to improve performance of publicly distributed assets. Assets can be anything
    from website content, to web application components, to media such as videos,
    ads, and interactive experiences.  CDNs decrease the load time of these assets
    by caching them on edge servers, also called points of presence (PoPs).  Edge
    servers are distributed around the globe, meaning requests only travel to a
    local location to grab assets, rather than to and from a data center based far
    from the end user.

Edge node
    CDN providers have many points of presence (PoP) servers around the world.
    These servers are known as edge nodes. These edge nodes cache the content and
    serve it directly to customers, thus reducing transit time to a customers
    location.

Edge server
    An edge server is the same as an edge node.

Origin
    An origin is an address (IP or domain) from which the CDN provider pulls
    content. A service can have multiple origins.

Flavor
    A flavor is a configuration option. A flavor enables you to choose from a
    generic setting that is powered by one or more CDN providers.

Service
    A service represents your web application that has its content cached to the
    edge nodes.

Status
    The status indicates the current state of the service. The time it takes for
    a service configuration to be distributed amongst a CDN provider cache can
    vary.

Purge
    Purging removes content from the edge servers - thus invalidating the
    content - so that it can be refreshed from your origin servers.
        
Caching rule
    A caching rule provides you with fine-grained control over the time-to-live
    (TTL) of an object. When the TTL expires for an object, the edge node pulls
    the object from the origin again.

Restriction
    A restriction enables you to define rules about who can or cannot access
    content from the cache. Examples of a restriction are allowing requests only
    from certain domains, geographies, or IP addresses.

Authenticate to gain access to the service
==========================================

To use this service you have to authenticate first. To do this, you will need your
Rackspace username, and one of the following:

* your Rackspace account password
* your Rackspace API key

Your username and password are the ones you use to login to the Cloud Control
Panel at http://mycloud.rackspace.com/.

To find your API key, first navigate to the Cloud Control Panel, then click on
your username at the top right corner, and then finally click on Account Settings.
You will be taken to a page that shows your settings. Under Login Details, you can
show or reset your API key.

Once you have these pieces of information, you can pass them into the SDK:

.. include:: samples/authentication.rst

Use the API
===========
Some of the basic operations you can perform with this API are described below.

Get flavors
-----------
First you must determine which flavors (CDN configurations) are available to you
for creating a new service (CDN-enabling your web site):

.. include:: samples/list_flavors.rst

Get flavor
----------
You can retrieve a specific flavor's details using its ID:

.. include:: samples/get_flavor.rst

Create service
--------------
Once you have chosen a flavor, you can create a service to CDN-enable your web
site:

.. include:: samples/create_service.rst

List services
-------------
To see the list of services you have created:

.. include:: samples/list_services.rst

Get service
-----------
You can retrieve a specific service's details using its ID:

.. include:: samples/get_service.rst

The service's details include a set of links. Look for links with their "rel"
set to "access_url". There will be as many such links as there are domains for
your web site. You can setup CNAMEs to the domains specified by the "href" values
of these links, from each of your web site's domains.

Once your DNS provider has propagated these CNAMEs across the Internet, visiting
any of your web site's domains will ensure that your web site's content is being
served from the CDN.

Purge all service assets
------------------------
You can purge all assets for your service:

.. include:: samples/purge_all_service_assets.rst

Purge a specific service asset
------------------------------
You can purge a specific asset for your service using the asset's URL:

.. include:: samples/purge_specific_service_asset.rst

Update service
--------------
To update an existing service:

.. include:: samples/update_service.rst

Delete service
--------------
To delete a service and purge all its assets in the process:

.. include:: samples/delete_service.rst
