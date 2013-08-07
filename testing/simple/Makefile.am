AM_CPPFLAGS = \
    -I$(top_srcdir)/drivers/include

AM_LDFLAGS = \
    -ljpeg -lz -lpthread

bin_PROGRAMS = quicktest

quicktest_LDADD = \
    $(top_srcdir)/build/drivers/vnc/libgfxvnc.la \
    $(top_srcdir)/build/drivers/common/libgfxcommon.la

quicktest_SOURCES = \
    quicktest.c

