# Production Setup

Before you begin, you'll need to collect a number of private keys and passphrases. Ask @ycombinator for them.

 * `~/.ssh/drg.pem`
 * `publisher.id_rsa`
 * The `ansible-vault` password.
 * The canonical IP whitelist for `ip_access.yml`

0. Initialize and update git submodules

  ```bash
  git submodule update --init
  ```

1. Download and install [Ansible](http://docs.ansible.com/intro_installation.html#installing-the-control-machine).
   * On Mac OSX machines with [Homebrew](http://brew.sh/) installed, you can simply run: `$ brew install ansible`


2. Install pyrax.

  ```bash
  $ sudo pip install pyrax
  ```

3. Create a file named `~/.rackspace_cloud_credentials` with the following contents:

  ```
  [rackspace_cloud]
  username = <REPLACE WITH YOUR RACKSPACE CLOUD USERNAME>
  api_key = <REPLACE WITH YOUR RACKSPACE CLOUD API KEY>
  ```

4. Make sure the `drg.pem` file is in your `~/.ssh` directory. The corresponding public key needs to be uploaded as the `drg` public key in the "SSH Keys" section of your Rackspace Cloud Control Panel for the region(s) where you wish to setup production infrastructure. Modify file permissions:

  ```bash
  $ chmod 600 ~/.ssh/drg.pem
  ```

5. Change to this directory on your development machine.

  ```bash
  $ cd /path/to/developer.rackspace.com/deploy
  ```

6. Make sure the `publisher.id_rsa` file is in the `roles/jenkins_masters/files/var/lib/jenkins/` directory. This is the private key used by Jenkins jobs to publish the generated web site to the various web servers serving developer.rackspace.com. Modify file permissions:

  ```bash
  $ chmod 600 roles/jenkins_masters/files/var/lib/jenkins/publisher.id_rsa
  ```
7. Create `ip_access.yml`. The format is specified in `ip_access.yml.example`. Don't use those IPs though, they're completely hokey. Replace them with the canonical list.

8. Run the Ansible playbook to set up the production and staging environments and Jenkins within a region. Enter the vault password when prompted to do so.

  ```bash
  $ RAX_REGION=DFW ansible-playbook -i inventory/site site.yml --ask-vault-pass
  ```

9. Each cloud server has nginx installed, configured and running.

10. Each cloud server has a `publisher` user. The Jenkins jobs `build_prod_site` and `build_staging_site` use this key to publish content to the production and staging web sites respectively. Content is published over rsync + ssh.

## Folder Layout

* **Vagrantfile**: Vagrant file used to setup a development environment VM.
* **inventory**:
  * **dev**:
   * **hosts**: Ansible inventory file for development environment.
  * **site**:
   * **hosts**: Ansible inventory file for production and staging environments.
   * **rax.py**: Ansible dynamic inventory file (since we are working with the Rackspace cloud). See http://docs.ansible.com/guide_rax.html#host-inventory.
* **site.yml**:
* **prod_web.yml**: Ansible playbook to setup and configure developer.rackspace.com infrastructure in a given region.
* **jenkins.yml**: Ansible playbook to setup and configure Jenkins infrastructure in a given region.
* **roles/**: Various Ansible roles referenced in the playbooks.
