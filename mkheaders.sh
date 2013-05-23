#!/bin/sh
#-------------------------------------------------------------------
# Creates the JNI headers.
#-------------------------------------------------------------------
THIS_DIR=`dirname $0`
BASE_DIR=`readlink -f ${THIS_DIR}`

# Set auxillary directories
CLASS_DIR=${BASE_DIR}/framework/java/bin
JNI_DIR=${BASE_DIR}/drivers/jnilib

# TODO: Make sure the class directory exists, compile if it doesn't
if [ ! -d ${CLASS_DIR} ]; then
  echo "Java classes are not available. Trying to rebuild them."
  exit 1
fi

# Generate the JNI header file
javah -classpath ${CLASS_DIR} -o ${JNI_DIR}/jnidriver.h com.thegaragelab.quickui.Driver
