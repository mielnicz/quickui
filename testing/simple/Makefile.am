AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include

AM_LDFLAGS = \
    $(top_srcdir)/build/drivers/vncfb/libgfxvncfb.a \
    $(top_srcdir)/build/drivers/common/libgfxcommon.a \
    -ljpeg -lz -lpthread

bin_PROGRAMS = quicktest
quicktest_SOURCES = \
    quicktest.c

