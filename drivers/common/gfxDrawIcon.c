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

//---------------------------------------------------------------------------
// Helper functions
//---------------------------------------------------------------------------

//--- Local static variables
static GFX_IMAGE *m_pIcon;      //! Pointer to the icon being used
static uint16_t   m_bpl;        //! Bytes per line
static uint16_t   m_width;      //! Width of the icon
static uint16_t   m_height;     //! Height of the icon
static GFX_IMAGE *m_pMask;      //! Pointer to the icon being used
static uint16_t   m_bplMask;    //! Bytes per line
static uint16_t   m_widthMask;  //! Width of the icon
static uint16_t   m_heightMask; //! Height of the icon

/** Initialise state for a particular icon
 *
 */
static void initIcon(GFX_IMAGE *pIcon) {
  m_pIcon = pIcon;
  m_width = pIcon->m_header.m_width, m_width++;
  m_height = pIcon->m_header.m_height, m_height++;
  m_bpl = (m_width / 8) + (((m_width % 8)==0)?0:1);
  }

/** Get the value of a single pixel in the source
 *
 */
static bool getPixel(uint16_t x, uint16_t y) {
  uint16_t offset;
  uint8_t mask;
  // Make sure the co-ordinates are in range
  if((x>=m_width)||(y>=m_height))
    return false;
  // Calculate offset and mask for the source pixel
  offset = (y * m_bpl) + (x / 8);
  mask = 0x80 >> (x % 8);
  // Return the result
  return m_pIcon->m_data[offset]&mask;
  }

/** Initialise state for a particular mask
 *
 */
static void initMask(GFX_IMAGE *pMask) {
  m_pMask = pMask;
  m_widthMask = pMask->m_header.m_width, m_width++;
  m_heightMask = pMask->m_header.m_height, m_height++;
  m_bplMask = (m_widthMask / 8) + (((m_widthMask % 8)==0)?0:1);
  }

/** Get the value of a single pixel in the source
 *
 */
static bool getMaskPixel(uint16_t x, uint16_t y) {
  uint16_t offset;
  uint8_t mask;
  // Make sure the co-ordinates are in range
  if((x>=m_widthMask)||(y>=m_heightMask))
    return false;
  // Calculate offset and mask for the source pixel
  offset = (y * m_bplMask) + (x / 8);
  mask = 0x80 >> (x % 8);
  // Return the result
  return m_pMask->m_data[offset]&mask;
  }

//---------------------------------------------------------------------------
// Public functions
//---------------------------------------------------------------------------

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
  // Verify image data
  if((pIcon==NULL)||(pIcon->m_header.m_bpp!=IMAGE_BPP_1))
    return GFX_RESULT_BADARG;
  initIcon(pIcon);
  // Handle using a mask
  if(pMask!=NULL) {
    // Make sure the mask is a monochrom image
    if((pMask->m_header.m_bpp!=IMAGE_BPP_1)||(pMask->m_header.m_width!=pIcon->m_header.m_width)||(pMask->m_header.m_height!=pIcon->m_header.m_height))
      return GFX_RESULT_BADARG;
    initMask(pMask);
    // Draw the pixels
    for(dy=0;dy<h;dy++) {
      for(dx=0;dx<w;dx++) {
        if(getPixel(sx + dx, sy + dy)&&getMaskPixel(sx + dx, sy + dy))
          gfx_PutPixel(x + dx, y + dy, color);
        }
      }
    }
  else {
    // Draw the pixels
    for(dy=0;dy<h;dy++) {
      for(dx=0;dx<w;dx++) {
        if(getPixel(sx + dx, sy + dy))
          gfx_PutPixel(x + dx, y + dy, color);
        }
      }
    }
  // All done
  return GFX_RESULT_OK;
  }

