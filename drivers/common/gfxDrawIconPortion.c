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

/** Draw a portion of an icon to the display
 */
GFX_RESULT gfx_common_DrawIconPortion(uint16_t x, uint16_t y, GFX_ICON *pIcon, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_COLOR color) {
  return GFX_RESULT_OK;
  }

