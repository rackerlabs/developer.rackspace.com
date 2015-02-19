developer.rackspace.com: docs section
======================================

This system uses the python sphinx tool for structure & building as part of the
static build process. Included is a Makefile & make.bat file for cross platform
building (for development/contribution).

Layout:

* **_static**: static assets unique to the documentation; this may include js,
  css, images, etc.
* **_templates**: custom html templates
* **conf.py**: Configuration, no touchy.
* **index.rst**: Main index / landing page for the docs
* **make.bat**: windows build script
* **Makefile**: linux/osx build

You will need [Sphinx](http://sphinx-doc.org/) installed to build - however to
contribute, all you need is a basic understanding of the project layout and
Restructured Text. [Here is an excellent ReST primer](http://sphinx-doc.org/rest.html).

Once you're all set up: in order to build run:

```
make html
```

You will see the build output and the static files will be dropped in:

```
_build/html
```

On OS/X you can run:

```
open _build/html/index.html
```

To pop open the doc index and preview.


Adding QuickStart guides
=========================

In the interest of time, here's a brief non-normative description of the guide-writing workflow:

1. Find a guide that is not assigned to anyone and assign it to yourself.
2. Fork and then clone the developers.rackspace.com repo (https://github.com/rackerlabs/developer.rackspace.com)
3. git remote add upstream https://github.com/rackerlabs/developer.rackspace.com.git
4. git pull upstream dev
5. Create a git branch named after the service you are going to write about.
6. cd into docs/ 
7. Create a directory of the service you are going to write about with the following nomenclature:
   - remove cloud from the service name
   - keep service name, in lower case
   - ex: Cloud DNS -> dns, Cloud Queues -> queues
8. cd into that directory and create a directory named 'samples'
9. then create a new blank file named 'quickstart.rst' ($ touch quickstart.rst, for instance)
10. follow the basic structure and ReST conventions present in other existing guides
11. in the samples directory create a new blank file for each code sample you cited in the guide (ideally one per API operation).
12. run jekyll, then Sphinx (as explained above).
13. If no errors, git commit[1], then git push origin branch-name
14. Submit PR from your branch, to upstream dev.

Note: Remember these are getting started guides, so we want to avoid deep delves or thorough treatment of the subjects. In essence, we want the equivalent of basic CRUD operations. All other use cases should be left to in-depth API/SDK docs.

[1] we're not committing and pushing the output html files. we're only going to commit and push the .rst files. The static pages will then be built, rendered and deployed automatically by the CI.



