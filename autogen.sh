#!/bin/sh
if [ ! -d config ]; then
  mkdir config
fi
autoreconf --force --install -I config -I m4
