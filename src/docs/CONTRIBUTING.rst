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

If starting a new narrative/code section, be sure to add all the code block sections to each `.rst` you create, to help ``git`` merge them later. You can copy and paste the section above into each sample file.

If your SDK does not support an API, insert a comment noting that fact. For example:

  // This is not supported through the .NET SDK at this time

Language
--------

Use active rather than passive voice.

    After you create a new record:

is much better than:

    After the record is created.

Consistently use second-person `you` rather than first-person `we`.

    You create a record by ...

rather than:

    We create a record by ...

For commands, use simple instructions.

    To create a record:

is better than

    You'll need to create a record. You do this as follows:

Use neutral language instead of gerunds:

* **GOOD**: Set up your xxxx.
* **BAD**: Setting up your xxxx.

Use comments in code samples when each sample comprises multiple steps, to clarify the use of parameters that aren't obvious by name, or whenever something in your SDK behaves differently than the others.

Use TODO comments in code samples instead of printing out strings or handling errors.

Limit lines in sample files to 120 characters.

Placeholders
------------

When using a value the developer needs to insert by referencing some out-of-band information, use a ``{{placeholder}}``.

Our convention is to name these in lowercase with camelCasing, regardless of the underlying language idioms. Consistency among samples is important for us to be able to automate later with a simple replacement via [Mustache](http://mustache.github.io/). Also, we *want* them to stand out, so it's obvious at a glance that a user needs to replace them with a real value.

Don't use ``{{placeholders}}`` for every parameter! A good rule of thumb is to use a ``{{placeholder}}`` if the developer is going to need to look something up elsewhere, like the web UI, to find a correct value. Authentication credentials, addresses, or UUIDs are good examples of appropriate ``{{placeholder}}`` usage. Otherwise, consider using a literal_ or a variable_ instead.

Try to use consistent placeholder names throughout the guides. Here are some placeholders that should be consistent in the different language examples:

Authentication - all services

 * ``{{username}}``
 * ``{{apiKey}}``
 * ``{{region}}``

Databases

 * ``{{dbUsername}``
 * ``{{dbPassword}}``
 * ``{{dbName}}``
 * ``{{instanceId}}``
 * ``{{instanceName}}``
 * ``{{flavorId}}``

.. _literal:

Literals
--------

If an SDK call has parameters that could be hardcoded as sensible values, use a real, but obviously temporary, literal value instead of a ``{{placeholder}}``. For example, ``"my_server"`` as a server name, ``10`` as an alerting threshold, or ``{ "some-key" => "another-value" }`` as example metadata.

As much as possible, try to be consistent with the values chosen by other languages in that example. We don't want it to be too jarring when you're flipping between languages.

Make sure that you clearly document what the literal values that you choose mean, especially if you don't have keyword parameters to clarify. Units are especially important. Also, be considerate, and don't use defaults that are going to rack someone up a high bill if they copy and paste without paying attention!

Don't use literal values if there is a specific value that's needed for the call to succeed, like an API key or a valid server UUID! Use ``{{placeholders}}`` or variable references for those situations, instead.

.. _variable:

Variables
---------

For the most part, assume that the snippets you use within the samples of a single guide share some scope. This means that you can save a server to a ``server`` variable and then reference ``server.id`` in a later sample, because each snippet fits into a larger narrative flow.

Variable names should also be made consistent across a sample's languages, but made to fit within the native language's prevailing idioms. For example, if Ruby introduces a ``@load_balancer``, Python can use ``self.load_balancer``, and Java could use ``loadBalancer``.

Always make sure that you don't accidentally use a variable before it's declared, so a reader can use ctrl-f to discover where it came from, if they forget.

If it's possible, try to distinguish in some way between variables that are "local" to the current snippet, and ones that are "shared" among many snippets, to provider readers a clue that this return value is something that should be remembered. In Ruby examples, I use ``@instance_variables`` for "shared" variables and ``temp_variables`` for "local" ones.

For shell snippets, use ``UPPERCASE`` names for environment variables so they stand out clearly, and enclose all headers in double quotes.

Language Specific Code Conventions
----------------------------------

**Java**

* Comment all references to regions and zones with::

    // jclouds refers to "regions" as "zones"
    VolumeApi volumeApi = cinderApi.getVolumeApiForZone(REGION);


* Pass the appropriate API to all static methods::

    public static Volume showVolume(VolumeApi volumeApi, String volumeId) {
        Volume volume = volumeApi.get(volumeId);

        return volume;
    }

* Always return a temporary variable when invoking a particular API. In the previous code example, it is clear that the API returns a  ``Volume`` object.

* Always close the jclouds ``Context``::

    Closeables.close("{{exampleApi}}", true);

* Always match the Getting Started sample file names (snake case) to Java method names (lower CamelCase). For example::

    list_volumes.rst -> listVolumes(VolumeApi volumeApi);

