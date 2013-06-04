#!/usr/bin/env python
#----------------------------------------------------------------------------
# 01-Jun-2013 ShaneG
#
# This tool is used to generate system fonts from an existing QCO icon file.
# System fonts are a bit special - they are fixed width and they include all
# 256 character codes.
#----------------------------------------------------------------------------
from sys import argv
from os.path import exists, basename, splitext
from quickui import *

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

    %s [--width width] [--height height] input-file

Description:

    This utility will generate a system font from an Icon (.qco) resource file.

Options:

    --width width

      Specifies the width of each character in pixels. If not specified the
      default is 8 pixels.

    --height height

      Specifies the height of each character in pixels. If not specified the
      default is 8 pixels.
"""

if __name__ == "__main__":
  # Have we been given command line arguments ?
  if len(argv) <= 1:
    print USAGE % argv[0]
    exit(1)
  # Process the command line arguments
  chwidth = 8
  chheight = 8
  # Process the command line
  while (index < len(argv)) and (argv[index].startswith("--")):
    if argv[index] == "--width":
      chwidth = int(argv[index + 1])
      index = index + 2
    elif argv[index] == "--height":
      chheight = int(argv[index + 1])
      index = index + 2
  # Verify what we have
  if index >= len(argv):
    print "ERROR: No files have been specified on the command line."
    exit(1)
  # Load the input file
  icon = loadIcon(argv[index])
  # Build the character map
  charmap = list()
  for x in range(256):
    # Determine the co-ordinates of the icon portion we need
    x =
