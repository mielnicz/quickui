AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include \
    -I$(top_srcdir)/drivers/vncfb/vncserver \
    -I$(top_srcdir)/drivers/vncfb/vncserver/common \
    -I$(top_srcdir)/drivers/vncfb/vncserver/rfb \
    $(libpng_CFLAGS)

lib_LIBRARIES = libgfxvncfb.a
libgfxvncfb_a_SOURCES = \
  vncfb.c \
  vncserver/auth.c \
  vncserver/cargs.c \
  vncserver/corre.c \
  vncserver/cursor.c \
  vncserver/cutpaste.c \
  vncserver/draw.c \
  vncserver/font.c \
  vncserver/hextile.c \
  vncserver/httpd.c \
  vncserver/main.c \
  vncserver/rfbregion.c \
  vncserver/rfbserver.c \
  vncserver/rre.c \
  vncserver/scale.c \
  vncserver/selbox.c \
  vncserver/sockets.c \
  vncserver/stats.c \
  vncserver/translate.c \
  vncserver/ultra.c \
  vncserver/zlib.c \
  vncserver/zrle.c \
  vncserver/zrleoutstream.c \
  vncserver/zrlepalettehelper.c \
  vncserver/common/vncauth.c

