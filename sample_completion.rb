#!/usr/bin/env ruby
#
# Create a report on the current state of the Getting Started samples.
#
# Usage: vagrant ssh -c 'ruby /vagrant_data/sample_completion.rb'

require 'set'

DOCROOT = File.join(__dir__, 'src', 'docs')

SERVICES = begin
  entries = Dir[File.join DOCROOT, '*']
  entries = entries.select { |e| File.directory? e }
  entries = entries.map { |e| File.basename e }
  entries = entries.reject { |e| e[0] == '_' }
  entries.sort
end

# Pygments syntax names for languages with supported SDKs.
#
LANGUAGES = %w(csharp java javascript php python ruby sh)

# Counts to keep.
#
total, completed, missing = 0, 0, 0

# Check each service and sample.
#
SERVICES.each do |service|
  sample_files = Dir[File.join DOCROOT, service, 'getting-started', 'samples', '*.rst']

  sample_files.each do |sample|
    sample_name = File.basename(sample, '.rst')

    File.open(sample) do |inf|
      nonempty, current = Set.new, nil

      inf.each_line do |line|
        case line
        when /^\s*.. code-block::\s+(\w+)\s*/
          # Pardon my Perl. $1 is the first capture group from the match.
          current = $1
        when /\S/
          # A line that contains something other than whitespace.
          nonempty << current if current
        end
      end

      # Report current coverage for this sample as a GitHub task list entry.
      LANGUAGES.each do |lang|
        total += 1
        if nonempty.include? lang
          completed += 1
        else
          missing += 1
          puts "#{service.ljust 20} / #{sample_name.ljust 30} / #{lang.ljust 12}: missing"
        end
      end
    end
  end
end

puts "summary: #{completed} complete / #{missing} missing / #{total} total"
