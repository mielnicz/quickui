AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include \
    -I$(top_srcdir)/drivers/vncfb/vncserver \
    -I$(top_srcdir)/drivers/vncfb/vncserver/rfb \
    -I$(top_srcdir)/drivers/vncfb/vncserver/libvncserver \
    $(libz_CFLAGS) \
    $(libjpeg_CFLAGS)

noinst_LTLIBRARIES = libgfxvncfb.la

libgfxvncfb_la_LIBADD = \
    -ljpeg \
    -lpthread \
    -lz

libgfxvncfb_la_SOURCES = \
    vncfb.c \
    vncserver/libvncserver/main.c \
    vncserver/libvncserver/rfbserver.c \
    vncserver/libvncserver/rfbregion.c \
    vncserver/libvncserver/auth.c \
    vncserver/libvncserver/sockets.c \
    vncserver/libvncserver/stats.c \
    vncserver/libvncserver/corre.c \
    vncserver/libvncserver/hextile.c \
    vncserver/libvncserver/rre.c \
    vncserver/libvncserver/translate.c \
    vncserver/libvncserver/cutpaste.c \
    vncserver/libvncserver/httpd.c \
    vncserver/libvncserver/cursor.c \
    vncserver/libvncserver/font.c \
    vncserver/libvncserver/draw.c \
    vncserver/libvncserver/selbox.c \
    vncserver/libvncserver/d3des.c \
    vncserver/libvncserver/vncauth.c \
    vncserver/libvncserver/cargs.c \
    vncserver/libvncserver/minilzo.c \
    vncserver/libvncserver/ultra.c \
    vncserver/libvncserver/scale.c \
    vncserver/libvncserver/zlib.c \
    vncserver/libvncserver/zrle.c \
    vncserver/libvncserver/zrleoutstream.c \
    vncserver/libvncserver/zrlepalettehelper.c \
    vncserver/libvncserver/zywrletemplate.c \
    vncserver/libvncserver/tight.c

