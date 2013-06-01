#!/usr/bin/env python
#----------------------------------------------------------------------------
# 01-Jun-2013 ShaneG
#
# This is the support library for the QuickUI tools. It contains common
# definitions and operations used by a number of the tools.
#----------------------------------------------------------------------------
from struct import pack, unpack

#--- Extensions for various resource file types
EXTENSION_ICON    = ".qco"
EXTENSION_IMAGE   = ".qmg"
EXTENSION_PALETTE = ".qpl"
EXTENSION_FONT    = ".qfn"

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

#----------------------------------------------------------------------------
# Guards
#----------------------------------------------------------------------------

#--- Make sure we aren't invoked as a script
if __name__ == "__main__":
  print "ERROR: This is a support library, not a script."
  exit(1)
