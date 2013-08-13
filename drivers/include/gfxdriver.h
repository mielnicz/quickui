/*---------------------------------------------------------------------------*
* Defines the common implementation functions that can be used by drivers
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This file defines functions that can be used by drivers to implement
* functionality that they don't supply on top of the core driver functions.
*---------------------------------------------------------------------------*/
#ifndef __DRIVER_H
#define __DRIVER_H

/* Extra definitions we need */
#include <stdint.h>
#include <stdbool.h>
#include <quickgfx.h>

/* Guard for C++ */
#ifdef __cplusplus
extern "C" {
#endif

//---------------------------------------------------------------------------
// Common implementation of graphics functions
//---------------------------------------------------------------------------

/** Set the clipping region
 */
GFX_RESULT gfx_common_SetClip(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2);

/** Fill a region with the specified color
 */
GFX_RESULT gfx_common_FillRegion(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Draw a portion of an icon to the display */
GFX_RESULT gfx_common_DrawIcon(uint16_t x, uint16_t y, GFX_IMAGE *pIcon, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask, GFX_COLOR color);

/** Draw a portion of an image to the display */
GFX_RESULT gfx_common_DrawImage4(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask, GFX_PALETTE pPalette);

/** Draw a portion of an image to the display */
GFX_RESULT gfx_common_DrawImage16(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask);

/** Draw a portion of an image to the display */
GFX_RESULT gfx_common_DrawImage(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_IMAGE *pMask, GFX_COLOR color, GFX_PALETTE pPalette);

/** Draw a line from one point to another */
GFX_RESULT gfx_common_DrawLine(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Draw a box */
GFX_RESULT gfx_common_DrawBox(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Check for pending events */
GFX_RESULT gfx_common_CheckEvents(_gfx_HandleEvent pfHandleEvent);

/** Add a new event to the event queue */
GFX_RESULT gfx_common_AddEvent(GFX_TOUCH_EVENT evType, uint16_t p1, uint16_t p2);

//---------------------------------------------------------------------------
// Helper functions to manage masking
//---------------------------------------------------------------------------

/** Structure containing information about a mask
 *
 */
typedef struct _MASK_INFO {
  GFX_IMAGE *m_pMask;  //! The monochrome image being used as a mask
  uint16_t   m_bpl;    //! Bytes per line of image
  uint16_t   m_width;  //! Width of the image in pixels
  uint16_t   m_height; //! Height of the image in pixels
  } MASK_INFO;

/** Initialise information for a given image
 */
GFX_RESULT maskInitInfo(MASK_INFO *pMask, GFX_IMAGE *pIcon);

/** Get the value of a single pixel in the source
 */
bool maskGetPixel(MASK_INFO *pMask, uint16_t x, uint16_t y);

/* Guard for C++ */
#ifdef __cplusplus
};
#endif

#endif /* __DRIVER_H */
