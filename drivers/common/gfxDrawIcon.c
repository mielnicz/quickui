/*---------------------------------------------------------------------------*
* Common implementation of graphics functions
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This file implements a common version of the function that can be used by
* any graphics driver.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <quickgfx.h>
#include <gfxdriver.h>

/** Draw an icon to the display
 */
GFX_RESULT gfx_common_DrawIcon(uint16_t x, uint16_t y, GFX_ICON *pIcon, GFX_COLOR color) {
  return GFX_RESULT_OK;
  }

