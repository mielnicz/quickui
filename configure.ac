AC_INIT(quickui, 0.1, shane@thegaragelab.com)
AC_CONFIG_AUX_DIR(config)
AC_CONFIG_MACRO_DIR(m4)
AC_CONFIG_SRCDIR(drivers/common/gfxDrawBox.c)
AC_CANONICAL_SYSTEM

AM_INIT_AUTOMAKE([subdir-objects])
LT_INIT

AC_PROG_CC

AC_PROG_TRY_JAVAC
AC_PROG_TRY_JAVAH
AC_PROG_TRY_JAVADOC
AC_PROG_TRY_JAR

#----------------------------------------------------------------------------
# Do some Java specific stuff
#----------------------------------------------------------------------------

have_java=yes
if test -z "$JAVAC"; then have_java=no; fi
if test -z "$JAVAH"; then have_java=no; fi
if test -z "$JAR"; then have_java=no; fi
AM_CONDITIONAL([HAVE_JAVA],
  [test "x$have_java" = xyes])

# Set include directories for JNI
AX_JNI_INCLUDE_DIR

for JNI_INCLUDE_DIR in $JNI_INCLUDE_DIRS
do
    CPPFLAGS="$CPPFLAGS -I$JNI_INCLUDE_DIR"
done

#----------------------------------------------------------------------------
# Allow driver selection
#
# TODO: Need to do a sanity check to make sure at least one driver has been
#       selected.
#----------------------------------------------------------------------------

AC_ARG_WITH([driver],
  AS_HELP_STRING([--with-driver], [Specify the driver to build.]))

# Make sure the driver is valid
if test "x$with_driver" == "xvnc"; then
  AC_MSG_NOTICE([Using VNC driver.])
  GFX_DRIVER=vnc
elif test "x$with_driver" == "xlinuxfb"; then
  AC_MSG_NOTICE([Using Linux framebuffer driver.])
  GFX_DRIVER=linuxfb
else
  AC_MSG_ERROR([
** You must specify a valid graphics driver to use. Options are:
**   vnc     - use VNC for graphics and input.
**   linuxfb - Linux framebuffer])
fi
AC_SUBST(GFX_DRIVER)

#----------------------------------------------------------------------------
# Check for required libraries
#----------------------------------------------------------------------------

# Check for libjpeg
if test "x$with_vncdriver" == "xyes" && test -z "$LIBJPEG"; then
  AC_CHECK_LIB(jpeg, jpeg_destroy_decompress,
               jpeg_ok=yes,
               jpeg_ok=no)
  if test "$jpeg_ok" = yes; then
    AC_MSG_CHECKING([for jpeglib.h])
    AC_TRY_CPP(
[#include <stdio.h>
#undef PACKAGE
#undef VERSION
#undef HAVE_STDLIB_H
#include <jpeglib.h>],
        jpeg_ok=yes,
        jpeg_ok=no)
    AC_MSG_RESULT($jpeg_ok)
    if test "$jpeg_ok" = yes; then
      LIBJPEG='-ljpeg'
    fi
  fi

  if test x$with_libjpeg != xno && test -z "$LIBJPEG"; then
     AC_MSG_ERROR([Could not find JPEG library.])
  else
    AC_DEFINE(HAVE_LIBJPEG, [1], [Have JPEG library])
  fi
fi

AM_CONDITIONAL(HAVE_LIBJPEG, test ! -z "$LIBJPEG")
AC_SUBST(LIBJPEG)

AS_IF([test "x$with_vncdriver" == "xyes"],
      [PKG_CHECK_MODULES([zlib], [zlib])])

#----------------------------------------------------------------------------
# Generate output files
#----------------------------------------------------------------------------

AC_OUTPUT(
  Makefile
  drivers/Makefile
  drivers/common/Makefile
  drivers/vnc/Makefile
  drivers/jnilib/Makefile
  testing/Makefile
  testing/simple/Makefile
  )

