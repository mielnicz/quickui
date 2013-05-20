AC_INIT(quickui, 0.1, shane@thegaragelab.com)
AC_CONFIG_AUX_DIR(config)
AC_CONFIG_SRCDIR(drivers/common/gfxDrawBox.c)

AM_INIT_AUTOMAKE

AC_PROG_CC
AC_PROG_RANLIB

#----------------------------------------------------------------------------
# Allow driver selection
#
# TODO: Need to do a sanity check to make sure at least one driver has been
#       selected.
#----------------------------------------------------------------------------

AC_ARG_WITH([vncdriver],
  AS_HELP_STRING([--with-vncdriver], [Use the VNC based driver]))

#----------------------------------------------------------------------------
# Check for required libraries
#----------------------------------------------------------------------------

AS_IF([test "x$with_vncdriver" == "xyes"],
      [PKG_CHECK_MODULES([libpng], [libpng])])

#----------------------------------------------------------------------------
# Generate output files
#----------------------------------------------------------------------------

AC_OUTPUT(
  Makefile
  drivers/Makefile
  drivers/common/Makefile
  drivers/vncfb/Makefile
  )

