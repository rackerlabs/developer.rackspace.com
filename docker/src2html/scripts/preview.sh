#!/bin/bash
#
# Complete the Jekyll build and serve it.

source ${HOME}/scripts/common.sh

cd ${WORK_DIR}
exec bundle exec jekyll serve \
  --config ${CONFIG} \
  --source ${WORK_DIR} \
  --destination ${TARGET_DIR}
