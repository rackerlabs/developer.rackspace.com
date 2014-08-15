#!/bin/bash

set -u

#
# This script builds the various parts of the site (Getting Started guides,
# blog, etc.) and combines them into a single directory, `site_html`.
#
PROJECT_ROOT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
BUILD_DIR=/tmp/developer.rackspace.com
WORK_DIR=$BUILD_DIR/_work
TARGET_DIR=$PROJECT_ROOT/_site

# Error out immediately unless a _config.yml has been templated in.
if [ ! -f ${PROJECT_ROOT}/src/site_source/_config.yml ]; then
  echo "No _config.yml found in ${PROJECT_ROOT}/src/site_source/ !"
  echo
  echo "The preferred way to build the site for local development is with Vagrant. Consult the"
  echo "README for installation details."
  echo
  echo "If you're an avid do-it-yourselfer and you insist on building locally, you can build"
  echo "one from the template:"
  echo
  echo " script/localconf"
  echo
  exit 1
fi

ALL_PIDS=$(pgrep build_site.sh)
NUM_ALL_PIDS=$(echo "$ALL_PIDS" | wc -l)

if (( $NUM_ALL_PIDS > 1 )); then

  # If there's clones of me already running, kill all of them
  # so I'm the only one running. This isn't perfect because
  # there is a chance that two of these processes could kill
  # each other but, short of implementing a semaphore, there
  # is no way to ensure that they don't.
  for pid in $ALL_PIDS; do
    if [ $pid != $$ ]; then
      echo "Killing my clone with PID = $pid"
      kill $pid
    fi
  done

fi

# Create temporary work directory
rm -rf $WORK_DIR
mkdir -p $WORK_DIR

# Copy the site source into the work directory
rsync -Ca $PROJECT_ROOT/src/site_source/ $WORK_DIR/

# Build the Getting Started guides in the `docs/` directory using Sphinx
cd $PROJECT_ROOT/src/docs
/usr/local/bin/sphinx-build . $WORK_DIR/docs

# Build the web site HTML
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
cd $WORK_DIR
/usr/local/bin/jekyll build --source . --destination $BUILD_DIR/_site

# Pre-GZIP all of our HTML
echo -n "Gzipping HTML files... "
for file in $( find $BUILD_DIR/_site -type f -name "*.html" )
do
	gzip -c $file > $file.gz
done
echo "done"

# Copy to target dir
rsync -Ca --delete $BUILD_DIR/_site/ $TARGET_DIR/
