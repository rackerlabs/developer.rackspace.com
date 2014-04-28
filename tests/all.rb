#!/usr/bin/env ruby

require 'erb'
require 'fileutils'
require 'json'

credentials_path = File.join __dir__, 'credentials.json'
raise RuntimeError.new("Set your credentials!") unless File.exists? credentials_path
credentials = JSON.load(File.read credentials_path)

def construct
  b = binding
  template = File.read(File.join __dir__, 'templates', 'files.rb.erb')
  out_path = File.join(__dir__, 'assembled', 'files.rb')

  ERB.new(template, 0, "", "@output").result b
  File.write(out_path, @output)
  out_path
end

def execute(path)
  system "ruby #{path}"
  $?.success?
end

def inject(name)
  sample_root = File.join(__dir__, '..', 'docs', 'files', 'samples')
  sample_path = File.join(sample_root, "#{name.to_s}.rst")
  raise RuntimeError.new(name) unless File.exists?(sample_path)
  sample = File.read(sample_path)
  ruby_section = sample[/.. code-block:: ruby\n(.+)$/m, 1]
  raise RuntimeError.new(name) unless ruby_section

  # Inject credentials.
  credentials.each do |key, value|
    ruby_section.gsub!(key, value)
  end

  ruby_section
end

FileUtils.mkdir_p File.join(__dir__, 'assembled')

path = construct
execute(path) or raise RuntimeError.new("Failed!")
