#!/bin/bash
#
# Perform the Jekyll build.

source ${HOME}/scripts/common.sh

cd ${WORK_DIR}
exec bundle exec jekyll build \
  --config ${CONFIG} \
  --source ${WORK_DIR} \
  --destination ${TARGET_DIR}
