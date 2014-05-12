api_ref
=======

API reference pages for Rackspace APIs

Prerequisites
=============

To build the documentation, you must install `Apache Maven <http://maven.apache.org/>`

To install Maven 3 for Ubuntu 12.04 and later or Debian wheezy and later:

apt-get install maven

On Fedora 15 and later::

yum install maven3

Build, optionally update, and test API pages
============================================

To build, optionally update, and test the API pages:
        
#. If you want to make updates, create a branch for your developer.rackspace.com fork. 

   Otherwise, skip to the next step.

   Do all your work on your branch and NOT on rackerlabs/developer.rackspace.com. 
                
   "*mybranch*" is an arbitrary branch name where you will do your work.
                
        $ git checkout -b "*mybranch*"                       
                        
#. Change into the this directory in your forked repository.
                        
        $ cd api_ref
                        
#. Do a remote build to make sure that things work okay.
                        
        $ mvn clean generate-sources

#. When you are ready to commit, and periodically if you want the latest stuff, merge changes from remote project into your local fork.
                        
#. Push your branch to your fork.
         
         $ git push origin "*mybranch*"

The root of the generated HTML (API site) documentation is::

         developer.rackspace.com/api_ref/target/docbkx/html/api-ref.html

Test
====

Install the python tox package and run ``tox`` from the top-level
directory to use the same tests that are done as part of our Jenkins
gating jobs.

If you like to run individual tests, run:

* ``tox -e checkniceness`` - to run the niceness tests
* ``tox -e checksyntax`` - to run syntax checks
* ``tox -e checkdeletions`` - to check that no deleted files are referenced
* ``tox -e checkbuild`` - to actually build the manual

tox uses the `openstack-doc-tools package
<https://github.com/openstack/openstack-doc-tools>`_ for execution of
these tests. openstack-doc-tools has a requirement on maven for the
build check.