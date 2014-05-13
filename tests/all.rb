#!/usr/bin/env ruby

require 'erb'
require 'fileutils'
require 'optparse'
require 'json'

require 'rainbow'

Language = Struct.new(:name, :syntax, :ext, :build, :executable)

# Collected knowledge about supported programming languages.
#
LANGUAGES = [
  Language.new('C#', 'csharp', 'cs', 'gcs', 'mono'),
  Language.new('Java', 'java', 'java', 'javac', 'java'),
  Language.new('JavaScript', 'javascript', 'js', nil, 'node'),
  Language.new('PHP', 'php', 'php', nil, 'php'),
  Language.new('Python', 'python', 'py', nil, 'python'),
  Language.new('Ruby', 'ruby', 'rb', nil, 'ruby'),
]

ROOT = File.join __dir__, '..'

# Data structure to capture output and progress.
#
Outcome = Struct.new(:service, :language, :output, :kind)

# Exceptions to catch.

class MissingError < RuntimeError ; end

# Enumerate the services that are documented.
#
SERVICES = begin
  entries = Dir[File.join ROOT, 'docs', '*']
  entries = entries.select { |e| File.directory? e }
  entries = entries.map { |e| File.basename e }
  entries = entries.reject { |e| e =~ /^_/ }
  entries
end

# Create a String that lists the available services and languages.
#
def summarize_available
  snames = SERVICES.map { |s| Rainbow(s).bright }
  lnames = LANGUAGES.map { |l| Rainbow(l.syntax).bright }

  "Known services: #{snames.join ', '}\n" \
  "Known languages: #{lnames.join ', '}"
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
    $stderr.puts "Missing template for #{@service} and #{@language.name}."
    $stderr.puts "Expected path: #{@template_path}"
    raise MissingError.new
  end

  template = File.read(@template_path)
  out_path = File.join(__dir__, 'assembled', "#{@service}.#{@language.ext}")

  ERB.new(template, 0, "", "@output").result b
  File.write(out_path, @output)
  out_path
end

# Execute the named file with the interpreter associated with it. Create and
# return an Outcome object.
#
def execute(service, language, path)
  outcome = Outcome.new(service, language)

  IO.popen([language.executable, path], err: [:child, :out]) do |io|
    outcome.output = io.read
  end
  outcome.kind = ($?.success? ? :success : :failure)

  outcome
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

      if block_given?
        todo = line[/TODO ?(.*)/, 1]
        relevant_lines << yield(todo) if todo
      end
    end
  end

  if relevant_lines.empty?
    $stderr.puts "The #{@service} sample for #{name} is missing a code block for #{@language.ext}."
    return ''
  end

  relevant_section = relevant_lines.join

  # Inject credentials into the rendered code.
  credentials.each { |key, value| relevant_section.gsub!("{#{key}}", value) }

  @output << relevant_section if block_given?

  relevant_section
end

## All together now.

@credentials = credentials
@languages, @services, @valid = [], [], true
@outcomes = []

OptionParser.new do |opts|
  opts.banner = "Usage: #{$0} [-s SERVICE] [-l LANGUAGE]"
  opts.separator ''

  opts.on('-s', '--service SERVICE', 'Include examples for the chosen service.') do |service|
    unless SERVICES.include? service
      $stderr.puts "Unrecognized service: #{service}"
      @valid = false
    end

    @services << service
  end

  opts.on('-l', '--language LANGUAGE', 'Include examples in the chosen language.') do |query|
    normalized = query.downcase
    lang = LANGUAGES.detect { |l| l.name.downcase == normalized || l.syntax.downcase == normalized }
    if lang
      @languages << lang
    else
      $stderr.puts "Unrecognized language: #{lang}"
      @valid = false
    end
  end

  opts.on('-h', '--help', "You're looking at it.") do
    puts opts
    exit 0
  end

  opts.separator ''
  opts.separator summarize_available
end.parse! ARGV

unless @valid
  $stderr.puts
  $stderr.puts summarize_available
  exit 1
end

@services = SERVICES if @services.empty?
@languages = LANGUAGES if @languages.empty?

@services.each do |service|
  @languages.each do |language|
    puts unless @outcomes.empty?

    puts Rainbow(" #{service} in #{language.name}".rjust 80, '>').bright

    begin
      path = assemble(@credentials, service, language)
      result = execute(service, language, path)
      @outcomes << result
      if result.kind == :success
        puts Rainbow('succeeded '.ljust 80, '<').green.bright
      else
        puts result.output
        puts Rainbow('failed '.ljust 80, '<').red.bright
      end
    rescue MissingError => e
      puts Rainbow('missing '.ljust 80, '<').yellow.bright

      @outcomes << Outcome.new(service, language, '', :missing)
    end
  end
end

last_service = nil
success_count, failure_count, missing_count = 0, 0, 0
@outcomes.each do |outcome|
  if outcome.service != last_service
    $stdout.flush
    print "\n#{outcome.service.rjust(15)}: "
    last_service = outcome.service
  end

  case outcome.kind
  when :success
    success_count += 1
    print Rainbow("[#{outcome.language.name} .] ").green.bright
  when :failure
    failure_count += 1
    print Rainbow("[#{outcome.language.name} x] ").red.bright
  when :missing
    missing_count += 1
    print Rainbow("[#{outcome.language.name} ?] ").yellow.bright
  else
    raise RuntimeError.new("Unexpected Outcome kind: #{outcome.kind.inspect}")
  end
end

puts
puts
print "Total: #{success_count + failure_count + missing_count} / "

parts = []
parts << "#{success_count} succeeded" if success_count > 0
parts << "#{failure_count} failed" if failure_count > 0
parts << "#{missing_count} missing" if missing_count > 0

puts parts.join(" / ")
