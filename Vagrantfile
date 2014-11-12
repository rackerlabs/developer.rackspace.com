# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

MINIMUM_ANSIBLE_VERSION = '1.6.3'

if /ansible (?<ansible_version>\d+\.\d+\.\d+)/ =~ `ansible --version`
  fail "Ansible #{MINIMUM_ANSIBLE_VERSION} or later required; you're running #{ansible_version}" unless Gem::Version.new(ansible_version) > Gem::Version.new(MINIMUM_ANSIBLE_VERSION)
else
  fail 'Could not detect your ansible version, please check the output of `ansible --version`'
end

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  ## Box config

  config.vm.box = "precise64"
  config.vm.box_url = "http://files.vagrantup.com/precise64.box"
  config.vm.provider :virtualbox do |vb|
    vb.name = "developer.rackspace.com"
  end

  ## Network config

  config.vm.network :forwarded_port, guest: 9200, host: 9200, auto_correct: true
  config.vm.network :forwarded_port, guest: 80,   host: 8000, auto_correct: true
  config.vm.network :private_network, ip: "192.168.42.11"

  ## Shared folder for build

  config.vm.synced_folder "./", "/vagrant_data", type: "rsync",
    rsync__args: [
      "--verbose", "--archive", "-z",
      "--chmod=Dug+rwx,Fug+rw",
      "--exclude=.git/"
    ]

  ## Ansible Config

  config.vm.provision "ansible" do |ansible|
    ansible.playbook = "deploy/dev_web.yml"
    ansible.inventory_path = "deploy/inventory/dev/hosts"
    ansible.limit = 'all'
  end
end
