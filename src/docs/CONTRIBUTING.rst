Documentation Conventions
=========================

These docs are written in a narrative format with the same use cases across all the SDKs. For an example of documentation that follows this literate flow, check out `examples from Mailgun's documention`_.

.. _examples from Mailgun's documention: http://documentation.mailgun.com/quickstart.html#sending-messages

Code blocks go in this order::

  .. code-block:: csharp

  .. code-block:: java

  .. code-block:: javascript

  .. code-block:: php

  .. code-block:: python

  .. code-block:: ruby

  .. code-block:: sh


If there's a new language you want to add code samples for, insert it where it fits alphabetically. 


If starting a new narrative/code section, be sure to add all the code block sections to each `.rst` you create for PR sanity.

Use neutral language instead of gerunds:

* **GOOD**: Set up your xxxx.
* **BAD**: Setting up your xxxx.

Use comments in code samples when each sample comprises multiple steps.

Use TODO in code samples instead of printing out strings.

Limit lines to 120 characters.

When using a value the developer needs to input, surround the value in curly brackets. The convention is to name them as lowercased with camelCasing.
Here are some that should be consistent in the different language examples:

Authentication - all services

``{username}``
``{apiKey}``
``{region}``

Databases

``{dbUsername}``
``{dbPassword}``
``{dbName}``
``{instanceId}``
``{instanceName}``
``{flavorId}``

Additionally, for curl commands, all env vars should be UPPERCASE (i.e. ENDPOINT, TOKEN, etc.) and all headers should be surrounded by double quotes.
