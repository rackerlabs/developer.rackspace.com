#!/usr/bin/env ruby

require 'erb'
require 'fileutils'
require 'json'

Language = Struct.new(:syntax, :ext, :build, :executable)

# Collected knowledge about supported programming languages.
#
LANGUAGES = {
  'C#' => Language.new('csharp', 'cs', 'gcs', 'mono'),
  'Java' => Language.new('java', 'java', 'javac', 'java'),
  'JavaScript' => Language.new('javascript', 'js', nil, 'node'),
  'PHP' => Language.new('php', 'php', nil, 'php'),
  'Python' => Language.new('python', 'py', nil, 'python'),
  'Ruby' => Language.new('ruby', 'rb', nil, 'ruby'),
}

ROOT = File.join __dir__, '..'

# Exceptions to catch.

class MissingError < RuntimeError ; end

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
def assemble(credentials, service, language)
  FileUtils.mkdir_p File.join(__dir__, 'assembled')

  # Initialize state that's used by #inject.
  @service, @language = service, language

  b = binding
  @template_path = File.join __dir__, 'templates', "#{@service}.#{@language.ext}.erb"

  unless File.exist? @template_path
    $stderr.puts "Missing template for #{@service} and #{@language.ext}."
    $stderr.puts "Expected path: #{@template_path}"
    raise MissingError.new
  end

  template = File.read(@template_path)
  out_path = File.join(__dir__, 'assembled', "#{@service}.#{@language.ext}")

  ERB.new(template, 0, "", "@output").result b
  File.write(out_path, @output)
  out_path
end

# Execute the named file with the interpreter associated with it. Return `true`
# if everything went well, or `false` if something is broken.
#
def execute(path, settings)
  system settings.executable, path
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
    return ''
  end

  relevant_lines = []
  in_section, indent = false, nil

  File.readlines(sample_path).each do |line|
    if line =~ /^.. code-block:: #{@language.syntax}$/
      in_section = true
    elsif line =~ /^.. code-block::.*$/
      in_section = false
    elsif in_section
      indent = line.index /\S/ if indent.nil?
      relevant_lines << (indent.nil? ? line : line[indent..-1])
    end
  end

  if relevant_lines.empty?
    $stderr.puts "The #{@service} sample for #{name} is missing a code block for #{@language.ext}."
    return ''
  end

  relevant_section = relevant_lines.join

  # Inject credentials into the rendered code.
  credentials.each { |key, value| relevant_section.gsub!("{#{key}}", value) }

  relevant_section
end

## All together now.

@credentials = credentials

services.each do |service|
  LANGUAGES.each do |lname, language|
    puts ">> beginning: #{service} @ #{lname}"
    begin
      path = assemble(@credentials, service, language)
      result = execute(path, language)
      puts ".. #{result ? 'succeeded' : 'failed'}"
    rescue MissingError => e
      puts '!! failed'
    end
    puts "<< complete: #{lname}"
  end
end
