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

/** Draw a box
 */
GFX_RESULT gfx_common_DrawBox(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color) {
  uint16_t step, delta;
  // Wrap the operation in begin/end paint calls
  gfx_BeginPaint();
  // Do the horizontal lines
  delta = (x2>x1)?1:-1;
  for(step=x1; step!=(x2 + delta); step+=delta) {
    gfx_PutPixel(step, y1, color);
    gfx_PutPixel(step, y2, color);
    }
  // Do the vertical lines
  delta = (y2>y1)?1:-1;
  for(step=y1 + delta; step!=(y2 + delta); step+=delta) {
    gfx_PutPixel(x1, step, color);
    gfx_PutPixel(x2, step, color);
    }
  gfx_EndPaint();
  return GFX_RESULT_OK;
  }

