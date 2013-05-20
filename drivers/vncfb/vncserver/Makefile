# Makefile for the VNC server library
#----------------------------------------------------------------------------
include $(realpath ../../rules.make)

# Set global locations
LIBDIR := $(abspath ../../lib)
OBJDIR := $(abspath out/$(TARGET))

# Some global operations first
$(shell mkdir -p $(OBJDIR))
$(shell mkdir -p $(LIBDIR)/$(TARGET))

# Object files
OBJ = \
  $(OBJDIR)/main.o \
  $(OBJDIR)/rfbserver.o \
  $(OBJDIR)/rfbregion.o \
  $(OBJDIR)/auth.o \
  $(OBJDIR)/sockets.o \
  $(OBJDIR)/stats.o \
  $(OBJDIR)/corre.o \
  $(OBJDIR)/hextile.o \
  $(OBJDIR)/rre.o \
  $(OBJDIR)/translate.o \
  $(OBJDIR)/cutpaste.o \
  $(OBJDIR)/cursor.o \
  $(OBJDIR)/font.o \
  $(OBJDIR)/draw.o \
  $(OBJDIR)/selbox.o \
  $(OBJDIR)/cargs.o \
  $(OBJDIR)/scale.o \
  $(OBJDIR)/zlib.o \
  $(OBJDIR)/zrle.o \
  $(OBJDIR)/zrleoutstream.o \
  $(OBJDIR)/zrlepalettehelper.o \
  $(OBJDIR)/tight.o

# Main target
all: $(LIBDIR)/$(TARGET)/libvncserver.a

# Clean all generated files
clean:
	$(shell rm -f $(OBJDIR)/*.o)
	$(shell rm -f $(LIBDIR)/$(TARGET)/libvncserver.a)

# How to build the library
$(LIBDIR)/$(TARGET)/libvncserver.a: $(OBJ)
	$(AR) rvs $@ $^

# How to build the objects
$(OBJDIR)/%.o:%.c
	$(TARGET_CC) $(TARGET_CFLAGS) -I$(abspath .) -I$(abspath common) -c $< -o $@
