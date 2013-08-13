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
#include <stdbool.h>
#include <quickgfx.h>
#include <gfxdriver.h>

/** Draw a portion of an icon to the display
 *
 * Another straight forward, brute force implentation. Once again it depends
 * on the drivers PutPixel implementation to handle any clipping required
 * and doesn't do any error or bounds checking of it's parameters.
 *
 * @param x the X co-ordinate at which to display the icon portion.
 * @param y the Y co-ordinate at which to display the icon portion.
 * @param pIcon the icon to use as the source.
 * @param sx the X co-ordinate within the icon to start displaying.
 * @param xy the Y co-ordinate wihing the icon to start displaying.
 * @param w the width of the portion to display.
 * @param h the width of the portion to display.
 * @param color the color to display the parts of the icon in.
 * @param mask an icon to use as a mask.
 */
GFX_RESULT gfx_common_DrawIcon(uint16_t x, uint16_t y, GFX_IMAGE *pIcon, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask, GFX_COLOR color) {
  uint16_t dx, dy;
  MASK_INFO infoIcon, infoMask;
  // Verify image data
  if((pIcon==NULL)||(pIcon->m_header.m_bpp!=IMAGE_BPP_1))
    return GFX_RESULT_BADARG;
  maskInitInfo(&infoIcon, pIcon);
  // Handle using a mask
  if(pMask!=NULL) {
    // Make sure the mask is a monochrom image
    if((pMask->m_header.m_bpp!=IMAGE_BPP_1)||(pMask->m_header.m_width!=pIcon->m_header.m_width)||(pMask->m_header.m_height!=pIcon->m_header.m_height))
      return GFX_RESULT_BADARG;
    maskInitInfo(&infoMask, pMask);
    // Draw the pixels
    for(dy=0;dy<h;dy++) {
      for(dx=0;dx<w;dx++) {
        if(maskGetPixel(&infoIcon, sx + dx, sy + dy)&&maskGetPixel(&infoMask, sx + dx, sy + dy))
          gfx_PutPixel(x + dx, y + dy, color);
        }
      }
    }
  else {
    // Draw the pixels
    for(dy=0;dy<h;dy++) {
      for(dx=0;dx<w;dx++) {
        if(maskGetPixel(&infoIcon, sx + dx, sy + dy))
          gfx_PutPixel(x + dx, y + dy, color);
        }
      }
    }
  // All done
  return GFX_RESULT_OK;
  }

