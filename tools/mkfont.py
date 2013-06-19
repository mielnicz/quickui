#!/usr/bin/env python
#----------------------------------------------------------------------------
# 19-Jun-2013 ShaneG
#
#----------------------------------------------------------------------------
from sys import argv
from os.path import exists, basename, splitext
from quickui import *

#----------------------------------------------------------------------------
# Font manipulation
#----------------------------------------------------------------------------

""" Select the appropriatly sized font
"""
def selectFont(fontname, height, charset):
  # Start with a base size so we can scale later
  font = ImageFont.truetype(fontname, 32)
  # Find the largest character in the map requested
  mh = 0
  mw = 0
  for ch in charset:
    size = font.getsize(ch)
    mw = max(size[0], mw)
    mh = max(size[1], mh)
  # Find the scale to apply and reload the font
  size = int(32.0 * height / mh) + 1
  font = ImageFont.truetype(fontname, size)
  while mh > height:
    # Recalculate width and height
    mh = 0
    mw = 0
    for ch in charset:
      size = font.getsize(ch)
      mw = max(size[0], mw)
      mh = max(size[1], mh)
    if mh > height:
      size = size - 1
      font = ImageFont.truetype(fontname, size)
  # All done
  return mw, font

""" Create a single character map
"""
def createCharacter(image, width, height):
  global OPTIONS
  bitmap = ""
  for y in range(height):
    for x in range(width):
      if image.getpixel((x, y)) == 0:
        bitmap = bitmap + "0"
      else:
        bitmap = bitmap + "1"
    bitmap = bitmap + "\n"
  return (width, height, bitmap.strip())

""" Generate the character maps
"""
def generateCharacters(fontname, height, monospace, charset):
  chars = dict()
  width, font = selectFont(fontname, height, charset)
  # First pass - get width, height and location for character images
  imw = 0
  imh = 0
  xpos = 0
  ypos = 0
  for ch in charset:
    # Get the size for this character
    size = font.getsize(ch)
    if monospace:
      size = (width, size[1])
    # Figure out where to put it
    if (xpos + size[0]) > 255:
      xpos = 0
      ypos = ypos + height
    if (ypos + size[1]) > 255:
      print "ERROR: Cannot fit font into icon image."
      exit(1)
    chars[ch] = (xpos, ypos, size[0])
    xpos = xpos + size[0]
    # Update image size required
    imw = max(imw, xpos)
    imh = max(imh, ypos + size[1])
  # Now create the icon image
  image = Image.new("1", (imw, imh))
  draw = ImageDraw.Draw(image)
  draw.rectangle((0, 0, imw + 1, imw + 1), fill = 0)
  for ch in charset:
    xoff = 0
    size = font.getsize(ch)
    if monospace:
      xoff = int((width - size[0]) / 2)
    draw.text((chars[ch][0] + xoff, chars[ch][1]), ch, font = font, fill = 1)
  # Save the final image for visual checking
  imageout = splitext(fontname)[0] + ".png"
  print "Writing sample image to '%s'" % imageout
  image.save(imageout)
  # Done
  return chars, image

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

%s [options] input-file

Description:

    This utility will generate a font resource from a TrueType font file.

Options:

    --chars included-chars

      Specifies the characters to include in the font. The default is all
      printable ASCII characters (0x20 to 0x7F inclusive).

    --height height

      Specifies the height of each character in pixels. If not specified the
      default is 8 pixels.

    --monospace

      If set the font will be monospaced (all characters will be the width of
      the widest specified character). Default is false.
"""

if __name__ == "__main__":
  # Process command line arguments
  if len(argv) == 1:
    print USAGE % argv[0]
    exit(1)
  # Process the command line
  index = 1
  height = 8
  monospace = False
  charset = "".join([ chr(ch) for ch in range(0x20, 0x80) ])
  while (index < len(argv)) and (argv[index].startswith("--")):
    if argv[index] == "--height":
      height = int(argv[index + 1])
      index = index + 2
    elif argv[index] == "--monospace":
      monospace = True
      index = index + 1
    elif argv[index] == "--charset":
      charset = argv[index + 1]
      index = index + 2
    else:
      print "ERROR: Unsupported option '%s'" % argv[index]
      exit(1)
  # Make sure we have an input file
  if index >= len(argv):
    print "ERROR: No font file specified."
    exit(1)
  fontname = argv[index]
  # Generate the character maps and raw image
  chars, image = generateCharacters(fontname, height, monospace, charset)
  # Create the font header information
  fontchars = list()
  for ch in sorted(chars.keys()):
    fontchars.append((ord(ch), chars[ch][2], chars[ch][0], chars[ch][1]))
  # Generate the icon information
  imwidth, imheight, bits = imageToBits(image, oncolor = 1)
  # Now write the font file
  fontout = splitext(fontname)[0] + EXTENSION_FONT
  print "Writing font to '%s'" % fontout
  writeFont(fontout, len(fontchars), height, 32, fontchars, imwidth, imheight, bits)


