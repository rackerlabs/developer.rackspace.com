#!/usr/bin/env ruby

require 'erb'
require 'fileutils'
require 'json'

# Execute assembled files with a certain extension with the matching executable.
#
LANGUAGES = {
  rb: 'ruby'
}

ROOT = File.join __dir__, '..'

# Enumerate the services that are documented.
#
def services
  entries = Dir[File.join ROOT, 'docs', '*']
  entries = entries.select { |e| File.directory? e }
  entries = entries.map { |e| File.basename e }
  entries = entries.reject { |e| e =~ /^_/ }
  entries
end

# Load an arbitrary set of credentials from a .json file in this directory. See
# `credentials.json.example` for the expected keys.
#
def credentials
  credentials_path = File.join __dir__, 'credentials.json'

  unless File.exists? credentials_path
    $stderr.puts "You don't have a credentials file!"
    $stderr.puts "cp #{credentials_path}.example #{credentials_path}"
    raise RuntimeError.new('Missing credentials')
  end

  JSON.load(File.read credentials_path)
end

# Assemble the code samples for a specific language, within a certain service,
# into a single file, ready to be built (if necessary) and executed. Return the
# path of the templated file.
#
def assemble(credentials, service, lang_ext)
  FileUtils.mkdir_p File.join(__dir__, 'assembled')

  # Initialize state that's used by #inject.
  @service, @lang_ext = service, lang_ext

  b = binding
  @template_path = File.join __dir__, 'templates', "#{@service}.#{@lang_ext}.erb"

  unless File.exist? @template_path
    $stderr.puts "Missing template for #{@service} and #{@lang_ext}."
    $stderr.puts "Expected path: #{@template_path}"
    return
  end

  template = File.read(@template_path)
  out_path = File.join(__dir__, 'assembled', "#{@service}.#{@lang_ext}")

  ERB.new(template, 0, "", "@output").result b
  File.write(out_path, @output)
  out_path
end

# Execute the named file with the interpreter associated with it. Return `true`
# if everything went well, or `false` if something is broken.
#
def execute(path)
  ext = File.extname(path)
  ext = ext[1..-1] if ext[0] == '.'
  executable = LANGUAGES[ext.to_sym]
  unless executable
    $stderr.puts "I don't know how to execute #{ext} source!"
    $stderr.puts "You'll need to add it to the LANGUAGES Hash in #{__FILE__}."
    raise RuntimeError.new('Missing executable')
  end

  system executable, path
  $?.success?
end

## Template utilities

# These methods are intended to be used within .erb templates in the templates/
# directory. They will inherit this file's scope, though.

# Inject the contents of a specific code sample from the current service
# documentation.
def inject(name)
  sample_root = File.join(__dir__, '..', 'docs', @service, 'samples')
  sample_path = File.join(sample_root, "#{name.to_s}.rst")

  unless File.exists?(sample_path)
    $stderr.puts "The template #{@template_file} references a missing code sample."
    $stderr.puts "  Sample name: #{name} path: #{sample_path}"
    return
  end

  sample = File.read(sample_path)
  ruby_section = sample[/.. code-block:: ruby\n(.+)$/m, 1]

  unless ruby_section
    $stderr.puts "The #{@service} sample for #{@lang_ext} is missing a code block for #{@lang_ext}."
    return
  end

  # Inject credentials into the rendered code.
  credentials.each do |key, value|
    ruby_section.gsub!("{#{key}}", value)
  end

  ruby_section
end

## All together now.

@credentials = credentials

services.each do |service|
  LANGUAGES.keys.each do |lang_ext|
    puts ">> beginning: #{service} @ #{lang_ext}"
    path = assemble(@credentials, service, lang_ext)
    if path
      result = execute(path)
      puts ".. #{result ? 'succeeded' : 'failed'}"
    else
      puts '!! failed'
    end
    puts "<< complete: #{lang_ext}"
  end
end
