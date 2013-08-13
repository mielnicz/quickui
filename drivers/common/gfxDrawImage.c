/*---------------------------------------------------------------------------*
* Common implementation of graphics functions
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This file implements a common version of the function that can be used by
* any graphics driver.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <stdlib.h>
#include <quickgfx.h>
#include <gfxdriver.h>

/** Draw an image to the display
 */
/** Draw a portion of an image to the display */
GFX_RESULT gfx_common_DrawImage(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask, GFX_COLOR color, GFX_PALETTE pPalette) {
  if(pImage==NULL)
    return GFX_RESULT_BADARG;
  switch(pImage->m_header.m_bpp) {
    case IMAGE_BPP_1 : // Monochrome image
      return gfx_DrawIcon(x, y, pImage, sx, sy, w, h, pMask, color);
    case IMAGE_BPP_4 : // Palette image
      return gfx_DrawImage4(x, y, pImage, sx, sy, w, h, pMask, pPalette);
    case IMAGE_BPP_16 : // Color image
      return gfx_DrawImage16(x, y, pImage, sx, sy, w, h, pMask);
    }
  // Unsupported image type
  return GFX_RESULT_BADARG;
  }

