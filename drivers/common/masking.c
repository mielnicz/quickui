/*---------------------------------------------------------------------------*
* Helper functions for handling image masks.
*----------------------------------------------------------------------------*
* 13-Aug-2013 shaneg
*
* Implements helper functions to aid image masking operations.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <stdlib.h>
#include <stdbool.h>
#include <quickgfx.h>
#include <gfxdriver.h>

/** Initialise state for a particular icon
 *
 */
GFX_RESULT maskInitInfo(MASK_INFO *pMask, GFX_IMAGE *pIcon) {
  // Check arguments
  if((pMask==NULL)||(pIcon==NULL)||(pIcon->m_header.m_bpp!=IMAGE_BPP_1))
    return GFX_RESULT_BADARG;
  // Fill out the information structure
  pMask->m_pMask = pIcon;
  pMask->m_width = pIcon->m_header.m_width + 1;
  pMask->m_height = pIcon->m_header.m_height + 1;
  pMask->m_bpl = GFX_LINE_LENGTH(pIcon, 1);
  // Ready
  return GFX_RESULT_OK;
  }

/** Get the value of a single pixel in the source
 *
 */
bool maskGetPixel(MASK_INFO *pMask, uint16_t x, uint16_t y) {
  uint16_t offset;
  uint8_t mask;
  // Check parameters
  if(pMask==NULL)
    return false;
  // Make sure the co-ordinates are in range
  if((x>=pMask->m_width)||(y>=pMask->m_height))
    return false;
  // Calculate offset and mask for the source pixel
  offset = (y * pMask->m_bpl) + (x / 8);
  mask = 0x80 >> (x % 8);
  // Return the result
  return pMask->m_pMask->m_data[offset]&mask;
  }

