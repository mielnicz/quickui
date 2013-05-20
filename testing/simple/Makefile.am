AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include \
    $(libpng_CFLAGS)

AM_LDFLAGS = \
    $(top_srcdir)/build/drivers/common/libgfxcommon.a \
    $(top_srcdir)/build/drivers/vncfb/libgfxvncfb.a \
    $(libpng_LDFLAGS)

bin_PROGRAMS = quicktest
quicktest_SOURCES = \
    quicktest.c

