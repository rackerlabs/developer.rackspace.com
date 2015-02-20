# developer.rackspace.com

## Content Setup

Please see [the contributing document](/src/site_source/CONTRIBUTING.md) for guidance on submitting blog posts.

To preview local changes made to the site's static content, including the Getting Started guides or the blog, run:†

```
script/preview
```

After the build completes, your browser will launch on the resulting site. Use **Ctrl-C** to terminate the process when you're done.

† the command assumes you are running a modern version of OS X

## Full Site Setup

__Note__: You should use this setup if you work on the entire d.r.c. site, its infrastructure, etc.

1. Download and install [Vagrant 1.6 or higher](http://www.vagrantup.com/downloads.html).

2. Download and install [VirtualBox](https://www.virtualbox.org/wiki/Downloads).

3. Download and install [Ansible 1.6.3 or higher](http://docs.ansible.com/intro_installation.html#installing-the-control-machine).
    * On Mac OSX machines with [Homebrew](http://brew.sh/) installed, you can simply run:

        ```bash
        brew update && brew install ansible
        ```
    * To check your Ansible version, you can run:

        ```bash
        ansible --version
        ```
    * If the version of Ansible from your package manager is too out of date, you can always find the most recent version from `pip`:

        ```bash
        sudo pip install --upgrade ansible
        ```

4. Ensure that you are in the root directory of this repo; e.g., `cd ~/src/developer.rackspace.com` (or wherever you've cloned it). If you haven't cloned the repo, fork it via the Github web interface and then

    ```bash
    $ git clone {your-repo-path}/developer.rackspace.com
    ```

5. Then, fetch the git submodules if you haven't already.

    ```bash
    $ git submodule update --init
    ```

6. Run Vagrant to set up a VirtualBox VM running a development environment and automatically publish changes to local files to the development VM. **Note: This command will run in the foreground and will not allow this terminal window to be used for anything else. If you need to do more commandline work, please open a new window and continue there.**

    ```bash
    $ vagrant up && vagrant rsync-auto
    ```

7. That's it! Your development environment is setup in a VirtualBox VM! It's contents are:
    * A web server running Nginx, accessible at [http://localhost:8000](http://localhost:8000).
        * To access the Getting Started Guides, go to http://localhost:8000/docs/
    * If you have problems with the watcher process, view the logs with:
        ```bash
        sudo tail -F /var/log/upstart/watcher.log
        ```

## Branches

Our repo has two main branches (`dev` and `master`) contributors should care about.

`dev` branch should be used only for code (html, js, css, etc.) changes and infrastructure automation changes PRs.

PRs against `master` should only be for docs, blog posts or other text content.
