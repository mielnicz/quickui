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
# Image processing
#----------------------------------------------------------------------------

""" Process an RGB file
"""
def imageToBits_RGB(image, oncolor):
  print "  Processing RGB graphics file."

""" Process an RGBA file
"""
def imageToBits_RGBA(image):
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
def imageToBits_Mono(image, oncolor):
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
        line = line + "1"
      else:
        line = line + "0"
    # Pad the line to a full number of bytes
    while (len(line) % 8) <> 0:
      line = line + "0"
    bits = bits + line
  # All done
  return width, height, bits

""" Process a single image file
"""
def imageToBits(image, oncolor = 255):
  # Check the size
  if (image.size[0] > 256) or (image.size[1] > 256):
    print "  WARNING: Source image is greater than 256 is at least one dimension."
  # Check the mode and process appropriately
  if image.mode == "RGB":
    return imageToBits_RGB(image, oncolor)
  if image.mode == "RGBA":
    return imageToBits_RGBA(image)
  if image.mode == "1":
    return imageToBits_Mono(image, oncolor)
  # If we get here, we failed
  print "  ERROR: Unsupported graphics mode '%s'." % image.mode
  exit(1)

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
  width, height = unpack("BB", input[:2])
  return input[2:], width + 1, height + 1

""" Write image data
"""
def writeImageData(output, width, height, bits):
  # Make sure we have enough bits
  size = width * height
  if len(bits) <> size:
    print "ERROR: Insufficient data provided for image (Wanted %i, got %i)" % (size, len(bits))
    exit(1)
  nwidth = int(width / 2)
  if (width % 2) > 0:
    nwidth = nwidth + 1
  index = 0
  nybbles = 0
  val = 0
  for y in range(height):
    for x in range(nwidth):
      val = (val & 0x0F) << 4
      if x < width:
        val = val | (bits[index] & 0x0F)
        index = index + 1
      nybbles = nyblles + 1
      if (nybbles % 2) == 0:
        output.write(pack("B", val & 0xFF))

""" Read image data
"""
def readImageData(input, width, height):
  # Calculate the number of bytes we should have
  size = int(width / 2)
  if (width % 2) > 0:
    size = size + 1
  size = size * height
  # Verify the size of the data we have been given
  if len(input) < size:
    print "ERROR: Not enough data for a %i x %i image." % (width, height)
    print "       Wanted %i bytes, got %i." % (size, len(input))
    exit(1)

""" Write icon data
"""
def writeIconData(output, width, height, bits):
  # Calculate the number of bytes we should have
  size = int(width / 8)
  if (width % 8) > 0:
    size = size + 1
  size = size * height
  # Make sure we have the right amount
  if len(bits) <> (size * 8):
    print "ERROR: Incorrect data provided for icon (Wanted %i, got %i)" % (size, len(bits))
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
      output.write(pack("B", byte & 0xFF))
      count = 0
      byte = 0

""" Read icon data
"""
def readIconData(input, width, height):
  # Calculate the number of bytes we should have
  size = int(width / 8)
  if (width % 8) > 0:
    size = size + 1
  size = size * height
  # Verify the size of the data we have been given
  if len(input) < size:
    print "ERROR: Insufficient data for a %i x %i icon." % (width, height)
    print "       Wanted %i bytes, got %i." % (size, len(input))
    exit(1)
  # Now read the data
  bits = ""
  for index in range(size):
    data = ord(input[index])
    for bit in range(8):
      if data & 0x80:
        bits = bits + "1"
      else:
        bits = bits + "0"
      data = data << 1
  # All done
  return input[index:], bits

""" Write a font header
"""
def writeFontHeader(output, numchars, height, default, charmap):
  # Write the header
  output.write(pack("BBBB", numchars - 1, height - 1, default, 0))
  # Write the character map
  for ch in charmap:
    output.write(pack("BBBB", ch[0], ch[1] - 1, ch[2], ch[3] ))

""" Read a font header
"""
def readFontHeader(input):
  return None

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
def readImage(filename):
  input = ""
  # Read the input
  with open(filename, "rb") as content:
    data = content.read()
    while data <> '':
      input = input + data
      data = content.read()
  # Read the image header
  input, width, height = readImageHeader(input)
  # Now read the data for the Image
  input, bits = readImageData(input, width, height)
  return width, height, bits

""" Write an image resource to a file
"""
def writeImage(filename, width, height, bits):
  # Open the file
  output = open(filename, "wb")
  # Write the header
  writeImageHeader(output, width, height)
  # Write the data
  writeImageData(output, width, height, bits)
  # All done
  output.close()

""" Read an icon image from a file
"""
def readIcon(filename):
  input = ""
  # Read the input
  with open(filename, "rb") as content:
    data = content.read()
    while data <> '':
      input = input + data
      data = content.read()
  # Read the image header
  input, width, height = readImageHeader(input)
  # Now read the data for the Icon
  input, bits = readIconData(input, width, height)
  return width, height, bits

""" Write an icon resource to a file
"""
def writeIcon(filename, width, height, bits):
  # Open the file
  output = open(filename, "wb")
  # Write the header
  writeImageHeader(output, width, height)
  # Write the data
  writeIconData(output, width, height, bits)
  # All done
  output.close()

""" Read a font resource from a file
"""
def readFont(filename):
  return None

""" Write a font resource to a file
"""
def writeFont(filename, numchars, height, default, charmap, imgwidth, imgheight, bits):
  # Open the file
  output = open(filename, "wb")
  # Write the font header
  writeFontHeader(output, numchars, height, default, charmap)
  # Write the icon header
  writeImageHeader(output, imgwidth, imgheight)
  # Write the icon data
  writeIconData(output, imgwidth, imgheight, bits)
  # All done
  output.close()

#----------------------------------------------------------------------------
# Guards
#----------------------------------------------------------------------------

#--- Make sure we aren't invoked as a script
if __name__ == "__main__":
  print "ERROR: This is a support library, not a script."
  exit(1)
