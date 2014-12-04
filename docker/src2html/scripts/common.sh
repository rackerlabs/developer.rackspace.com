#!/bin/bash
#
# Assemble the site into a working directory for Jekyll to use.

set -e

export WORK_DIR=${HOME}/_work
export TARGET_DIR=${HOME}/_site
export CONFIG=${HOME}/baseconfig.yml

# Append additional (volume-mounted) configuration files.
for CONFIGFILE in ${HOME}/config/*.yml; do
  export CONFIG=${CONFIG},${CONFIGFILE}
done

# Create the working directory.
mkdir -p ${WORK_DIR}

# Copy the Jekyll source into the work directory.
rsync -Ca ${HOME}/src-jekyll/ ${WORK_DIR}/

# Use Sphinx to build the API docs into a subdirectory of the work directory.
cd ${HOME}/src-sphinx
sphinx-build . ${WORK_DIR}/docs
