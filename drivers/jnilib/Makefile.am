AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include

AM_LDFLAGS = \
    $(top_srcdir)/build/drivers/common/libgfxcommon.a \
    $(top_srcdir)/build/drivers/vncfb/libgfxvncfb.a \
    -ljpeg -lz

noinst_LTLIBRARIES = libjniquickgfx.la
libjniquickgfx_la_SOURCES = \
    jnidriver.c

