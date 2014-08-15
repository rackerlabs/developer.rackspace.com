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

## Blog Contributor Setup

__Note__: You should use this setup only if you are looking to contribute blog posts and do not require the Full Site Setup

1. Install Ruby 2.1.2 with RVM

    ```bash
    $ curl -L https://get.rvm.io | bash -s stable --autolibs=enabled --ruby=2.1.2
    ```

2. Fork devblog

    [https://github.com/rackerlabs/developer.rackspace.com/fork](https://github.com/rackerlabs/developer.rackspace.com/fork)

3. Clone your fork

    ```bash
    $ git clone git@github.com:<your_github_username>/developer.rackspace.com.git
    ```

    ```bash
    $ cd developer.rackspace.com.git
    ```

4. Blog setup
    * Install blog gems

        ```bash
        $ bundle install
        ```
    * Install sphinx-build

        Ubuntu:

        ```bash
        $ sudo apt-get update && sudo apt-get install python-setuptools -y ; sudo easy_install pip; sudo pip install sphinx
        ```
    * Add default & required config.yml

        ```bash
        $ script/localconf
        ```
    * Create softlink for jekyll as the build_script.sh (below) expects it in a specific location

        ```bash
        $ ln -s /usr/local/rvm/gems/ruby-1.9.3-p547/bin/jekyll /usr/local/bin/jekyll
        ```

5. Set upstream & pull devblog (the branch you'll be PR'ing to / working off of)

    ```bash
    $ git remote add upstream https://github.com/rackerlabs/developer.rackspace.com.git
    $ git fetch upstream
    $ git checkout -b devblog upstream/devblog
    ```

6. Begin blogging
    * Create a topic branch off of the devblog branch

        ```bash
        $ git checkout -b newpost
        ```
    * Create your new post in src/site\_source/\_posts/. The post must be in markdown & follow these guidelines

        [https://github.com/rackerlabs/developer.rackspace.com/blob/dev/src/site_source/CONTRIBUTING.md](https://github.com/rackerlabs/developer.rackspace.com/blob/dev/src/site_source/CONTRIBUTING.md)
    * Tips
        * Embedding local images
            * Create a directory for your images in src/site\_source/\_assets/img/
            * Put all your images in this new directory
            * Once the images are in the directory, you can reference it in your post with markdown as such:

                ```
                {% img <new_directory_name>/<img.png> %}
                ```

7. To preview the generated blog locally
    * You must set the metadata field in your post for "published" to be "true" so that build\_site.sh will generate its content
    * Generates the site via jekyll

        ```bash
        ./build_site.sh
        ```
    * If build\_site.sh generates the site successfully, its time to serve it the contents generated by jekyll in \_site/. Using something such as python's SimpleHTTPServer will suffice

        ```bash
        $ pushd _site/ ; python -m SimpleHTTPServer ; popd
        ```
    * Point your browser at [http://IPADDR:8000/blog](http://IPADDR:8000/blog) to view the blog

8. Once you're done with your blog post, it's time to create a pull request
    * Before submitting the PR, do the following cleanup & prep:
        * Set the metadata field for "published" back to being "false" in your post (since you would've set it to "true" to preview the blog locally)
        * Remove all trailing whitespace from the post
        * Spellcheck
        * Squash / rebase your commits down to 1 single commit
        * Append to following info to your commit message
            * author(s) mini bio, soft capped to 75 words
            * author(s) social media contact info
            * accept/decline to allow redaction, editing or structure changes by technical writer staff
                * Ie. "I accept redaction/editing/changes"
            * desired date of publishing
    * Check the devblog branch for updates and rebase your topic branch, if necessary

        ```bash
        $ git checkout devblog
        $ git fetch upstream
        $ git merge upstream/devblog
        $ git checkout newpost
        $ git rebase devblog
        ```

        * Take care of conflicts, if applicable

9. Push the commit to your fork

    ```bash
    $ git push origin newpost
    ```

10. Create a PR to the developer.rackspace.com repo under the __devblog__ branch from your forked __newpost__ branch

11. For quick processing, send an email/message to Ruben Orduz with a link to your PR so that he can review & merge it in
