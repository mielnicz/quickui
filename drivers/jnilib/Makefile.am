AM_CPPFLAGS = \
    -I/System/Library/Frameworks/JavaVM.framework/Headers \
    -I$(top_srcdir)/drivers/include

AM_LDFLAGS = \
    $(top_srcdir)/build/drivers/common/libgfxcommon.a \
    $(top_srcdir)/build/drivers/vncfb/libgfxvncfb.a \
    -ljpeg -lz

lib_LTLIBRARIES = libjniquickgfx.la
libjniquickgfx_la_SOURCES = \
    jnidriver.c

