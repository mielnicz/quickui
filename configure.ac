AC_INIT(quickui, 0.1, shane@thegaragelab.com)
AC_CONFIG_AUX_DIR(config)
AC_CONFIG_SRCDIR(drivers/common/gfxDrawBox.c)

AM_INIT_AUTOMAKE

AC_PROG_CC
AC_PROG_RANLIB

# Check for required libraries
PKG_CHECK_MODULES([libpng], [libpng])

AC_OUTPUT(
  Makefile
  drivers/Makefile
  drivers/common/Makefile
  drivers/vncfb/Makefile
  )

