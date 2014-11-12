.. _quickstart:
=============================
Quickstart for Cloud Identity
=============================

The Rackspace Cloud Identity service provides authentication and authorization
functionality for other Rackspace Cloud services. The primary authentication
operation is the generation of tokens: when user credentials (username and API
key) are successfully validated, a "token" is returned; this token is then sent
for all subsequent API operations used in other services.

Based on the OpenStack Identity service (codenamed Keystone), Rackspace Cloud
Identity also offers user manager and basic RBAC functionality for fine-grained
access control.

Setup
=====

In order to interact with the Identity API, various SDKs require some form of
client setup:

.. include:: samples/setup.rst

Tokens
======

As mentioned above, a token is the primary resource needed for authentication.
To use other Rackspace services, you need to send a token in every API
operation (as an `X-Auth-Token` HTTP header) - so authenticating will be your
first operation.

A token is composed of a unique ID and an expiry date. Tokens are valid for 24
hours and may not be used after their expiry date.

Authenticate
------------

Rackspace allows you to authenticate with either your account password or your
account API key. You can also, optionally, specify tenant details for the user
you are authenticating. A "tenant" is a type of user categorization - similar to
a group, project, or organization - that provides additional isolation. If a
tenantId or tenantName are provided, the token generated will be scoped to that
tenant only.

To generate a token with your API key:

.. include:: samples/create_token_api_key.rst

To generate a token with your account password:

.. include:: samples/create_token_password.rst

Users
=====

A user is a representation of a person or system that is allowed authenticate
and consume other Rackspace API services. Users have their own set of
credentials and can be assigned tokens. They can also be assigned to a tenant
or region so that they inherit a set of access rights and privileges
automatically, based on the tenant or region configuration.

Add user
--------

Admins may add up to 100 users to an account. The following values are required:

* ``username`` - the name to assign to the user. It must start with an
alphabetical char and have a length of at least 1 char. It may contain upper
and lower case characters, and any of the following special chars: ``. - @ _``.

- ``email`` - the email address

- ``enabled`` - indicates whether the user can authenticate after their account is
created.

You can also specify a password for the user to use. If no password is supplied,
the API will automatically generate one and return it in the response.

.. include:: samples/create_user.rst

Update user
-----------

To update a user, you need the user's unique ID.

.. include:: samples/update_user.rst

List users
----------

If an admin executes this operation (i.e. a user with the `identity:user-admin`
role), it will return _all_ users for the tenant. To refine this collection,
you have the option of passing in a `name` or `email`.

If a normal user executes this operation (a user with the `identity:default`
role), it will only return information about the user's own account.

.. include:: samples/list_users.rst

Delete user
-----------

.. include:: samples/delete_users.rst

Reset API key
-------------

It is recommended that you routinely reset API keys. This operation will return
a new API key for the user, but will not revoke existing tokens.

.. include:: samples/reset_api_key.rst

Roles
=====

A role is a logical grouping of access rights that allow users to do things.
There are two _types_ of role for the Rackspace Cloud Identity service: global
roles and product roles. Global roles define access and permissions across _all_
Rackspace systems; product roles are more granular: they define access and
permissions for one service only (e.g. Cloud Servers).

List all roles
--------------

.. include:: samples/list_all_roles.rst

Add role to user
----------------

.. include:: samples/add_role_to_user.rst

Delete role from user
---------------------

.. include:: samples/delete_role_from_user.rst
