/*---------------------------------------------------------------------------*
* Common implementation of graphics functions
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This file implements a common version of the function that can be used by
* any graphics driver.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <stdbool.h>
#include <quickgfx.h>
#include <gfxdriver.h>

/** Set the clipping region
 */
GFX_RESULT gfx_common_SetClip(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2) {
  if((x1>=0)&&(x1<g_GfxDriver.m_width))
    g_GfxDriver.m_clipX1 = x1;
  if((x2>=0)&&(x2<g_GfxDriver.m_width))
    g_GfxDriver.m_clipX2 = x2;
  if((y1>=0)&&(y1<g_GfxDriver.m_height))
    g_GfxDriver.m_clipY1 = y1;
  if((y2>=0)&&(y2<g_GfxDriver.m_height))
    g_GfxDriver.m_clipY2 = y2;
  // All done
  return GFX_RESULT_OK;
  }

