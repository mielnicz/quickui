AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include \
    -I$(top_srcdir)/drivers/vncfb/vncserver \
    -I$(top_srcdir)/drivers/vncfb/vncserver/common \
    -I$(top_srcdir)/drivers/vncfb/vncserver/rfb \
    $(libpng_CFLAGS)

lib_LIBRARIES = libgfxvncfb.a
libgfxvncfb_a_SOURCES = \
  vncfb.c \
  vncserver/main.c \
  vncserver/rfbserver.c \
  vncserver/rfbregion.c \
  vncserver/auth.c \
  vncserver/sockets.c \
  vncserver/stats.c \
  vncserver/corre.c \
  vncserver/hextile.c \
  vncserver/rre.c \
  vncserver/translate.c \
  vncserver/cutpaste.c \
  vncserver/cursor.c \
  vncserver/font.c \
  vncserver/draw.c \
  vncserver/selbox.c \
  vncserver/cargs.c \
  vncserver/scale.c \
  vncserver/zlib.c \
  vncserver/zrle.c \
  vncserver/zrleoutstream.c \
  vncserver/zrlepalettehelper.c \
  vncserver/tight.c

