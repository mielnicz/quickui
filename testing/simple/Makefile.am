AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include

AM_LDFLAGS = \
    $(top_srcdir)/build/drivers/common/libgfxcommon.a \
    $(top_srcdir)/build/drivers/vncfb/libgfxvncfb.a \
    -ljpeg -lz

bin_PROGRAMS = quicktest
quicktest_SOURCES = \
    quicktest.c

