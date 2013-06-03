#!/usr/bin/env python
#----------------------------------------------------------------------------
# 01-Jun-2013 ShaneG
#
# This is the support library for the QuickUI tools. It contains common
# definitions and operations used by a number of the tools.
#----------------------------------------------------------------------------
from struct import pack, unpack

try:
  from PIL import Image, ImageDraw, ImageFont
except:
  print "ERROR: This utility requires the Python Imaging Library."
  exit(1)

#--- Extensions for various resource file types
EXTENSION_ICON    = ".qco"
EXTENSION_IMAGE   = ".qmg"
EXTENSION_PALETTE = ".qpl"
EXTENSION_FONT    = ".qfn"

#--- Limits on image sizes
MAX_IMAGE_WIDTH  = 256
MAX_IMAGE_HEIGHT = 256

#----------------------------------------------------------------------------
# Helper functions
#----------------------------------------------------------------------------

""" Create a native 16 bit (5-6-5) color from RGB values
"""
def createColor(red, green, blue):
  result = (((int(red) & 0xF8) << 8)   & 0xF800)
  result = (((int(green) & 0xFC) << 3) & 0x07E0) | result
  result = (((int(blue) & 0xF8) >> 3)  & 0x001F) | result
  return result

""" Write an image header
"""
def writeImageHeader(output, width, height):
  output.write(pack("BB", width - 1, height - 1))

""" Read an image header
"""
def readImageHeader(input):
  pass

#----------------------------------------------------------------------------
# Read/Write operations for resources
#----------------------------------------------------------------------------

""" Read a palette resource from a file
"""
def readPalette(filename):
  return None

""" Write a palette resource to a file
"""
def writePalette(filename, palette):
  # Open the file
  output = open(filename, "wb")
  # Write the data
  for index in range(16):
    # Get the raw color value from the palette (default to black)
    value = 0
    if index < len(palette):
      value = createColor(*palette[index])
    # Write it to the output
    output.write(pack("<H", value))
  # All done
  output.close()

""" Read an icon image from a file
"""
def readIcon(filename):
  return None

""" Write an icon resource to a file
"""
def writeIcon(filename, width, height, bits):
  # Open the file
  output = open(filename, "wb")
  # Write the header
  writeImageHeader(output, width, height)
  # Make sure we have enough bits
  size = width * height
  if len(bits) <> size:
    print "ERROR: Insufficient data provided for icon (Wanted %i, got %i)" % (size, len(bits))
    exit(1)
  # Write out the data
  byte = 0
  count = 0
  for ch in bits:
    # Update the data in the byte
    byte = byte << 1
    if ch == "0":
      byte = byte & 0xFE
    else:
      byte = byte | 0x01
    # Move to the next bit
    count = count + 1
    if count == 8:
      print "%02x" % byte
      output.write(pack("B", byte & 0xFF))
      count = 0
      byte = 0
  # All done
  output.close()

#----------------------------------------------------------------------------
# Guards
#----------------------------------------------------------------------------

#--- Make sure we aren't invoked as a script
if __name__ == "__main__":
  print "ERROR: This is a support library, not a script."
  exit(1)
