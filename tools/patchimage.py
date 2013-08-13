#!/usr/bin/env python
#----------------------------------------------------------------------------
# 13-Aug-2013 ShaneG
#
# This tool is used to patch QCO/QMG/QFN files to the new format.
#----------------------------------------------------------------------------
from sys import argv
from os.path import exists, basename, splitext
from struct import pack, unpack

#----------------------------------------------------------------------------
# File handling
#----------------------------------------------------------------------------

""" Determine if the QCO is in the new format
"""
def isNewIcon(data):
  width, height, bpp, reserved = unpack("BBBB", data[:4])
  expected = int((width + 1) / 8)
  if ((width + 1) % 8) <> 0:
    expected = expected + 1
  expected = (expected * (height + 1)) + 4
  print "  Found %i x %i icon." % (width + 1, height + 1)
  print "  Expected size is %i, actual size is %i" % (expected, len(data))
  return len(data) == expected

""" Convert the icon data to the new format
"""
def createNewIcon(data):
  width, height = unpack("BB", data[:2])
  output = pack("BBBB", width, height, 1, 0)
  return output + data[2:]

""" Determine if the QMG is in the new format
"""
def isNewImage(data):
  width, height, bpp, reserved = unpack("BBBB", data[:4])
  expected = int((width + 1) / 2)
  if ((width + 1) % 2) <> 0:
    expected = expected + 1
  expected = (expected * (height + 1)) + 4
  print "  Found %i x %i image." % (width + 1, height + 1)
  print "  Expected size is %i, actual size is %i" % (expected, len(data))
  return len(data) == expected

""" Convert the image data to the new format
"""
def createNewImage(data):
  width, height = unpack("BB", data[:2])
  output = pack("BBBB", width, height, 4, 0)
  return output + data[2:]

""" Determine if the QFN is in the new format
"""
def isNewFont(data):
  numchars, height, default = unpack("BBB", data[:3])
  index = (numchars + 2) * 4
  width, height, bpp, reserved = unpack("BBBB", data[index:index + 4])
  expected = int((width + 1) / 8)
  if ((width + 1) % 8) <> 0:
    expected = expected + 1
  expected = index + (expected * (height + 1)) + 4
  print "  Found %i x %i font with %i characters." % (width + 1, height + 1, numchars + 1)
  print "  Expected size is %i, actual size is %i" % (expected, len(data))
  return len(data) == expected

""" Convert the font data to the new format
"""
def createNewFont(data):
  numchars, height, default = unpack("BBB", data[:3])
  index = (numchars + 2) * 4
  width, height = unpack("BB", data[index:index + 2])
  return data[:index] + pack("BBBB", width, height, 1, 0) + data[index + 2:]

#----------------------------------------------------------------------------
# Main program
#----------------------------------------------------------------------------

USAGE = """
Usage:

%s input-files

Description:

    This utility will update existing .qco, .qmg and .qfn files from the old
    format to the new.
"""

if __name__ == "__main__":
  # Have we been given command line arguments ?
  if len(argv) <= 1:
    print USAGE % argv[0]
    exit(1)
  # Process the input files
  for filename in argv[1:]:
    name, ext = splitext(filename)
    if ext == "":
      ext = ".qco" # Assume icon image
    filename = name + ext
    # Now process the file
    print "Processing '%s'" % filename
    if not exists(filename):
      print "  ERROR: File does not exist or cannot be opened."
    else:
      # Read the input
      input = ""
      with open(filename, "rb") as content:
        data = content.read()
        while data <> '':
          input = input + data
          data = content.read()
      # Handle file by it's type
      if ext == ".qco":
        if not isNewIcon(input):
          input = createNewIcon(input)
        else:
          print "  File is already in the correct format."
      elif ext == ".qmg":
        if not isNewImage(input):
          input = createNewImage(input)
        else:
          print "  File is already in the correct format."
      elif ext == ".qfn":
        if not isNewFont(input):
          input = createNewFont(input)
        else:
          print "  File is already in the correct format."
      else:
        input = None
      # Now write out the data
      if input is None:
        print "  ERROR: Could not convert image file."
      else:
        output = open(filename, "wb")
        output.write(input)
        output.close()
