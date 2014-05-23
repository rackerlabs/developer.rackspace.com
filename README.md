# developer.rackspace.com

Third gen of the developer.rackspace.com Portal

This repo represents a refactor of the developer.rackspace.com site.

The dev site currently builds from rackerlabs/devsite but only accepts new blog posts as of March 1 2014.

We are migrating to this repo by June-ish 2014. The areas now building are:
- `/src/docs/`: Getting Started Guides, built using Sphinx
- `/src/site_source`: Rest of the web site layout and content, built using Jekyll

In progress:
- /api/: maven, content maintained in rackerlabs/docs-api-reference
- elastic search

To do:
- assets to CDN
- feeds

## Development Setup

1. Fetch the git submodules if you haven't already.

   ```bash
   $ git submodule update --init
   ```

2. Download and install [Vagrant 1.6 or higher](http://www.vagrantup.com/downloads.html).

3. Download and install [VirtualBox](https://www.virtualbox.org/wiki/Downloads).

4. Download and install [Ansible](http://docs.ansible.com/intro_installation.html#installing-the-control-machine).
   * On Mac OSX machines with [Homebrew](http://brew.sh/) installed, you can simply run: `$ brew install ansible`

5. Run Vagrant to set up a VirtualBox VM running a development environment. Vagrant's rsync-auto command so edits you make on your development machine automatically trigger changes inside the VirtualBox VM.

    ```bash
    $ vagrant up && vagrant rsync-auto
    ```

6. That's it! Your development environment is setup in a VirtualBox VM! It's contents are:
   * A web server running Nginx, accessible at [http://localhost:8000](http://localhost:8000).
      * To access the Getting Started Guides, go to http://localhost:8000/docs/
   * If you have problems with the watcher process, view the logs with:
     ```bash
     sudo tail -F /tmp/watcher.log
     ```

