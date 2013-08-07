AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include

AM_LDFLAGS = \
    -ljpeg -lz

lib_LTLIBRARIES = libjniquickgfx.la

libjniquickgfx_la_LIBADD = \
    $(top_srcdir)/build/drivers/vnc/libgfxvnc.la \
    $(top_srcdir)/build/drivers/common/libgfxcommon.la

libjniquickgfx_la_SOURCES = \
    jnidriver.c

