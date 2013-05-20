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

/** Draw a line from one point to another
 */
GFX_RESULT gfx_common_DrawLine(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color) {
  int16_t dx, dy, err, swap, x, y;
  // Do special cases
  if((x1==x2)||(y1==y2))
    return gfx_FillRegion(x1, y1, x2, y2, color);
  // Make sure X moves in a positive direction
  if(x2<x1) {
    // Swap the X co-ordinates
    swap = x1;
    x1 = x2;
    x2 = swap;
    // Swap the Y co-ordinates
    swap = y1;
    y1 = y2;
    y2 = swap;
    }
  // Start the drawing
  gfx_BeginPaint();
    dx = (int16_t)x2 - (int16_t)x1;
  // Handle an increasing Y value
  if(y2>y1) {
    // Set up variables
    dy = (int16_t)y2 - (int16_t)y1;
    err = 2 * dy - dx;
    y = (int16_t)((y2<y1)?y2:y1);
    // Start the drawing
    gfx_PutPixel(x1, y1, color);
    for(x=x1 + 1; x<=x2; x++) {
      if(err>0) {
        y = y + 1;
        gfx_PutPixel(x, y, color);
        err = err + (2 * dy) - (2 * dx);
        }
      else {
        gfx_PutPixel(x, y, color);
        err = err + (2 * dy);
        }
      }
    }
  else {
    // Set up variables
    dy = (int16_t)y1 - (int16_t)y2;
    err = 2 * dy - dx;
    y = (int16_t)((y2<y1)?y2:y1);
    // Start the drawing
    gfx_PutPixel(x1, y1, color);
    for(x=x1 + 1; x<=x2; x++) {
      if(err>0) {
        y = y + 1;
        gfx_PutPixel(x, g_GfxDriver.m_height - y - 1, color);
        err = err + (2 * dy) - (2 * dx);
        }
      else {
        gfx_PutPixel(x, g_GfxDriver.m_height - y - 1, color);
        err = err + (2 * dy);
        }
      }
    }
  // Finished drawing
  gfx_EndPaint();
  return GFX_RESULT_OK;
  }

