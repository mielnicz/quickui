#!/usr/bin/env python
#----------------------------------------------------------------------------
# 01-Jun-2013 ShaneG
#
# This tool is used to generate system fonts from an existing QCO icon file.
# System fonts are a bit special - they are fixed width and they should
# include all 256 character codes, at the very least they must have a
# contiguous sequence of characters.
#----------------------------------------------------------------------------
from sys import argv
from os.path import exists, basename, splitext
from quickui import *

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

%s [options] input-file

Description:

    This utility will generate a system font from an Icon (.qco) resource file.

Options:

    --output output

      The name of the output file to generate. If not specified the output
      file will have the same name as icon file with the extensions replaced
      by '.qfn'.

    --width width

      Specifies the width of each character in pixels. If not specified the
      default is 8 pixels.

    --height height

      Specifies the height of each character in pixels. If not specified the
      default is 8 pixels.

    --start start

      The ASCII value of the first character image in the icon. Default is
      0x00.

    --end end

      The ASCII value of the last character image in the icon. Default is
      0xFF.
"""

if __name__ == "__main__":
  # Have we been given command line arguments ?
  if len(argv) <= 1:
    print USAGE % argv[0]
    exit(1)
  # Process the command line arguments
  chwidth = 8
  chheight = 8
  chstart = 0
  chend = 255
  outname = None
  # Process the command line
  index = 1
  while (index < len(argv)) and (argv[index].startswith("--")):
    if argv[index] == "--width":
      chwidth = int(argv[index + 1])
      index = index + 2
    elif argv[index] == "--height":
      chheight = int(argv[index + 1])
      index = index + 2
    elif argv[index] == "--start":
      chstart = int(argv[index + 1])
      index = index + 2
    elif argv[index] == "--end":
      chend = int(argv[index + 1])
      index = index + 2
    elif argv[index] == "--output":
      outname = argv[index + 1]
      index = index + 2
    else:
      print "ERROR: Unsupported option '%s'" % argv[index]
      exit(1)
  # Verify what we have
  if index >= len(argv):
    print "ERROR: No files have been specified on the command line."
    exit(1)
  # Check the parameters we have been given
  if (chstart < 0) or (chstart > 255):
    print "ERROR: Start value is out of range."
    exit(1)
  if (chend < 0) or (chend > 255):
    print "ERROR: End value is out of range."
    exit(1)
  if chstart >= chend:
    print "ERROR: Character range must go from lowest to highest value."
    exit(1)
  if outname is None:
    outname = splitext(argv[index])[0]
  outname, ext = splitext(outname)
  if ext == "":
    ext = EXTENSION_FONT
  outname = outname + ext
  # Determine the input file name
  iconfile, ext = splitext(argv[index])
  if ext == "":
    ext = EXTENSION_ICON
  iconfile = iconfile + ext
  # Load the input file
  icon = readIcon(iconfile)
  if icon is None:
    print "ERROR: Unable to load icon '%s'" % argv[index]
    exit(1)
  # Can all the characters fit in the icon provided ?
  chmax = (icon[0] / chwidth) * (icon[1] / chheight)
  if (chend - chstart) >= chmax:
    print "ERROR: The specified characters would not fit in the icon."
    print "       Icon size is %i x %i" % (icon[0], icon[1])
    exit(1)
  # Build the character map
  charmap = list()
  chline = int(icon[0] / chwidth)
  for ch in range(chstart, chend + 1):
    # Determine the co-ordinates of the icon portion we need
    x = chwidth * ((ch - chstart) % chline)
    y = chheight * ((ch - chstart) / chline)
    # Now add the entry
    charmap.append((ch, chwidth, x, y))
  # Now write out the font
  print "Writing font to '%s'" % outname
  writeFont(outname, chend - chstart + 1, chheight, 32, charmap, icon[0], icon[1], icon[2])

