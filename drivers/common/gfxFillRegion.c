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

/** Fill a region with the specified color
 */
GFX_RESULT gfx_common_FillRegion(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color) {
  uint16_t x, y;
  int16_t dx, dy;
  // Set the deltas
  dx = (x2>x1)?1:-1;
  dy = (y2>y1)?1:-1;
  // Fill the region one pixel at a time
  gfx_BeginPaint();
  for(y=y1; y!=(y2 + dy); y+=dy)
    for(x=x1; x!=(x2 + dx); x+=dx)
      gfx_PutPixel(x, y, color);
  gfx_EndPaint();
  // All done
  return GFX_RESULT_OK;
  }

