#!/usr/bin/env python
#----------------------------------------------------------------------------
# 01-Jun-2013 ShaneG
#
# This tool is used to generate image resources and corresponding palettes
# from image files.
#----------------------------------------------------------------------------
from sys import argv
from os.path import exists, basename, splitext
from quickui import *

#----------------------------------------------------------------------------
# Text file processing
#----------------------------------------------------------------------------

def chunk(seq, size):
  return [seq[i:i+size] for i in range(0, len(seq), size)]

""" Process an image file.
"""
def processImageFile(filename):
  # Open it as an image
  image = None
  try:
    image = Image.open(filename)
  except:
    print "ERROR: Could not open '%s' as an image." % filename
    exit(1)
  # Start processing the file
  print "Processing '%s' ..." % filename
  # Check the size
  if (image.size[0] > 256) or (image.size[1] > 256):
    print "  WARNING: Source image is greater than 256 is at least one dimension."
  # Make sure it is in RGB format
  if not image.mode in ("RGB", "RGBA"):
    image = image.convert("RGB")
  # Create a paletted version of the image
  image16 = image.convert("P", palette=Image.ADAPTIVE, colors=16)
  # Save the palette
  outfile = splitext(filename)[0] + EXTENSION_PALETTE
  print "  Writing palette to %s" % outfile
  pal = [map(ord, bytes) for bytes in chunk(image16.im.getpalette(), 3)][:16]
  writePalette(outfile, pal)
  # Save the image
  outfile = splitext(filename)[0] + EXTENSION_IMAGE
  print "  Writing image to %s" % outfile
  results = list()
  for y in range(min(image16.size[1], 256)):
    for x in range(min(image16.size[0], 256)):
      results.append(image16.getpixel((x, y)))
  writeImage(outfile, min(image16.size[0], 256), min(image16.size[1], 256), results)

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

    %s inputs

Description:

    This utility can generate image and palette resources from one or more
    image files. For each file specified on the command line a corresponding
    image (.qmg) and palette (.qpl) will be generated with the same base
    name as the input file.
"""

if __name__ == "__main__":
  # Have we been given command line arguments ?
  if len(argv) <= 1:
    print USAGE % argv[0]
    exit(1)
  # Process each command line argument as a single image file
  for arg in argv[1:]:
    processImageFile(arg)

