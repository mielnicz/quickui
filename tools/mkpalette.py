#!/usr/bin/env python
#----------------------------------------------------------------------------
# 01-Jun-2013 ShaneG
#
# This tool is used to generate palette resources from text definitions or
# from image files.
#----------------------------------------------------------------------------
from sys import argv
from os.path import exists, basename, splitext
from quickui import *

#----------------------------------------------------------------------------
# Text file processing
#----------------------------------------------------------------------------

""" Process a single text file.
"""
def processTextFile(filename):
  # Make sure the file exists and we can open it
  if not exists(filename):
    print "ERROR: Could not open file '%s'" % filename
    exit(1)
  input = open(filename, "r")
  # Determine the output file name
  outname = splitext(filename)[0] + EXTENSION_PALETTE
  # Now process the input file
  print "Processing file '%s'" % filename
  palette = list()
  for line in input:
    line = line.strip()
    # Skip empty lines or comments
    if (len(line) == 0) or (line[0] == '#'):
      continue
    # The line should now be a comma separated sequence of values
    # indicating the red, green and blue components of the color.
    parts = line.split(",")
    parts = [ int(val, 0) for val in parts ]
    # Now verify the values we got back
    if len(parts) <> 3:
      print "ERROR: Expected 3 numerical components in line '%s'" % line
      exit(1)
    # Make sure the values are in range
    for index in range(3):
      if parts[index] < 0:
        parts[index] = 0
      if parts[index] > 255:
        parts[index] = 255
    # And add them to the palette
    palette.append(tuple(parts))
  # All done, save the palette
  print "  Writing output to '%s'" % outname
  writePalette(outname, palette)

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

    %s [--from-image] [--output output-file] inputs

Description:

    This utility can generate palette resources from text descriptions or from
    image files. A palette resource contains 16 entries where each entry is
    mapped to a 16 bit color. It is generally used to render image resources
    although it can also be used as a handy way to define colors for your
    program to use.

Options:

    --from-image

        This specifies that the input files are images. A wide range of image
        formats are supported (jpg, png, bmp, etc). In this case the colors
        in the image will be reduced to a maximum of 15 colors (based on usage
        counts) and those values will be used in the palette.

    --output output-file

        This option is only valid if --from-image has been specified. In this
        case the colors used in all input files will be combined to generate
        a single output palette.

Output:

    Unless --output has been specified a new palette resource will be generated
    for each input file. This file will have the same name as the input file
    but have the extension '.qpl'.
"""

if __name__ == "__main__":
  # Have we been given command line arguments ?
  if len(argv) <= 1:
    print USAGE % argv[0]
    exit(1)
  # If so, process them
  images = False
  output = None
  index = 1
  while (index < len(argv)) and (argv[index].startswith("--")):
    if argv[index] == "--from-image":
      images = True
      index = index + 1
    elif argv[index] == "--output":
      output = argv[index + 1]
      index = index + 2
  # Verify what we have
  if index >= len(argv):
    print "ERROR: No files have been specified on the command line."
    exit(1)
  if (output is not None) and not images:
    print "ERROR: Combined output is only available for image files."
    exit(1)
  # Now do the processing
  if not images:
    # Process one or more text files
    for filename in argv[index:]:
      processTextFile(filename)
