AC_INIT(quickui, 0.1, shane@thegaragelab.com)
AC_CONFIG_AUX_DIR(config)
AC_CONFIG_SRCDIR(drivers/common/gfxDrawBox.c)
AC_CANONICAL_SYSTEM

AM_INIT_AUTOMAKE

AC_PROG_CC
AC_PROG_RANLIB

AC_PROG_TRY_JAVAC
AC_PROG_TRY_JAVAH
AC_PROG_TRY_JAVADOC
AC_PROG_TRY_JAR

#----------------------------------------------------------------------------
# Do some Java specific stuff
#----------------------------------------------------------------------------

have_java=yes
have_java=yes
if test -z "$JAVAC"; then have_java=no; fi
if test -z "$JAVAH"; then have_java=no; fi
if test -z "$JAR"; then have_java=no; fi
AM_CONDITIONAL([HAVE_JAVA],
  [test "x$have_java" = xyes])

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
      [PKG_CHECK_MODULES([libjpeg], [libjpeg])])

AS_IF([test "x$with_vncdriver" == "xyes"],
      [PKG_CHECK_MODULES([libz], [libz])])

#----------------------------------------------------------------------------
# Generate output files
#----------------------------------------------------------------------------

AC_OUTPUT(
  Makefile
  drivers/Makefile
  drivers/common/Makefile
  drivers/vncfb/Makefile
  testing/Makefile
  testing/simple/Makefile
  )

