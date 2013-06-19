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
# Image processing
#----------------------------------------------------------------------------

""" Process an RGB file
"""
def processRGB(image, oncolor):
  print "  Processing RGB graphics file."

""" Process an RGBA file
"""
def processRGBA(image):
  width, height = image.size
  width = min(width, MAX_IMAGE_WIDTH)
  height = min(height, MAX_IMAGE_HEIGHT)
  print "  Processing %i x %i image with alpha channel." % (width, height)
  bits = ""
  for y in range(height):
    line = ""
    for x in range(width):
      color = image.getpixel((x, y))
      if color[3] == 0:
        line = line + "0"
      else:
        line = line + "1"
    # Pad the line to a full number of bytes
    while (len(line) % 8) <> 0:
      line = line + "0"
    bits = bits + line
  # All done
  return width, height, bits

""" Process a monochrome image file
"""
def processMono(image, oncolor):
  width, height = image.size
  width = min(width, MAX_IMAGE_WIDTH)
  height = min(height, MAX_IMAGE_HEIGHT)
  print "  Processing %i x %i monochrome image." % (width, height)
  bits = ""
  for y in range(height):
    line = ""
    for x in range(width):
      color = image.getpixel((x, y))
      if color == oncolor:
        bits = bits + "1"
      else:
        bits = bits + "0"
    # Pad the line to a full number of bytes
    while (len(line) % 8) <> 0:
      line = line + "0"
    bits = bits + line
  # All done
  return width, height, bits

""" Process a single image file
"""
def processImage(filename, oncolor):
  # Make sure the file exists
  if not exists(filename):
    print "ERROR: The file '%s' does not exist." % filename
    exit(1)
  # Open it as an image
  image = None
  try:
    image = Image.open(filename)
  except:
    print "ERROR: Could not open '%s' as an image." % filename
    exit(1)
  # Start processing the file
  print "Processing '%s' ..." % filename
  return imageToBits(image, oncolor)

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

%s [options] input-files

Description:

    This utility will generate icon resources from a set of image files. The
    input files may be in a wide range of formats (jpg, png, bmp, etc) and
    the generated icon resource will have the same name as the input with the
    extension changed to '.qco'.

Options:

    --invert

      This option only applies to monochrome images. It inverts the the meaning
      of the foreground color. Without the option any white pixels are set as
      the foreground, with the option applied black pixels are considered the
      foreground.
"""

if __name__ == "__main__":
  # Have we been given command line arguments ?
  if len(argv) <= 1:
    print USAGE % argv[0]
    exit(1)
  # Collect options
  oncolor = 255
  index = 1
  while argv[index].startswith("--"):
    if argv[index] == "--invert":
      oncolor = 0
      index = index + 1
    else:
      print "ERROR: Unrecognised option '%s'" % argv[index]
      exit(1)
  # Process the input files
  for filename in argv[index:]:
    width, height, bits = processImage(filename, oncolor)
    outname = splitext(filename)[0] + EXTENSION_ICON
    print "  Writing resource '%s'" % outname
    writeIcon(outname, width, height, bits)
