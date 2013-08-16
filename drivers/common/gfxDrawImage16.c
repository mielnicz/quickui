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

/** Helper function to get the value of a pixel at a given position
 *
 * @param pImage the image to query.
 * @param x      the x position to check.
 * @param y      the y position to check.
 * @param bpl    number of bytes per line.
 *
 * @return the color index at the position
 */
static uint16_t getImage16Pixel(GFX_IMAGE *pImage, uint16_t x, uint16_t y, int bpl) {
  int offset = bpl * y;
  offset = offset + (x * 2);
  return *((uint16_t *)&pImage->m_data[offset]);
  }

/** Draw a portion of an image to the display
 *
 * Another straight forward, brute force implementation. Once again it depends
 * on the drivers PutPixel implementation to handle any clipping required
 * and doesn't do any error or bounds checking of it's parameters.
 *
 * @param x the X co-ordinate at which to display the icon portion.
 * @param y the Y co-ordinate at which to display the icon portion.
 * @param pIcon the icon to use as the source.
 * @param sx the X co-ordinate within the icon to start displaying.
 * @param xy the Y co-ordinate within the icon to start displaying.
 * @param w the width of the portion to display.
 * @param h the width of the portion to display.
 * @param mask an icon to use as a mask.
 */
GFX_RESULT gfx_common_DrawImage16(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask) {
  uint16_t dx, dy;
  MASK_INFO infoMask;
  // Verify image data
  if((pImage==NULL)||(pImage->m_header.m_bpp!=IMAGE_BPP_16))
    return GFX_RESULT_BADARG;
  // Set the width and height if needed
  if((w<0)||((sx + w)>(pImage->m_header.m_width + 1)))
    w = pImage->m_header.m_width - sx;
  if((h<0)||((sy + h)>(pImage->m_header.m_height + 1)))
    h = pImage->m_header.m_height - sy;
  int bpl = GFX_LINE_LENGTH(pImage, 16);
  // Handle using a mask
  if(pMask!=NULL) {
    // Make sure the mask is a monochrome image
    if((pMask->m_header.m_bpp!=IMAGE_BPP_1)||(pMask->m_header.m_width!=pImage->m_header.m_width)||(pMask->m_header.m_height!=pImage->m_header.m_height))
      return GFX_RESULT_BADARG;
    maskInitInfo(&infoMask, pMask);
    // Draw the pixels
    for(dy=0;dy<h;dy++) {
      for(dx=0;dx<w;dx++) {
        if(maskGetPixel(&infoMask, sx + dx, sy + dy))
          gfx_PutPixel(x + dx, y + dy, getImage16Pixel(pImage, sx + dx, sy + dy, bpl));
        }
      }
    }
  else {
    // Draw the pixels
    for(dy=0;dy<h;dy++) {
      for(dx=0;dx<w;dx++) {
        gfx_PutPixel(x + dx, y + dy, getImage16Pixel(pImage, sx + dx, sy + dy, bpl));
        }
      }
    }
  // All done
  return GFX_RESULT_OK;
  }
