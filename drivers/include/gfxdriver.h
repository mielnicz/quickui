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
#include <quickgfx.h>

/* Guard for C++ */
#ifdef __cplusplus
extern "C" {
#endif

/** Fill a region with the specified color
 */
GFX_RESULT gfx_common_FillRegion(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Draw an icon to the display */
GFX_RESULT gfx_common_DrawIcon(uint16_t x, uint16_t y, GFX_ICON *pIcon, GFX_COLOR color);

/** Draw a portion of an icon to the display */
GFX_RESULT gfx_common_DrawIconPortion(uint16_t x, uint16_t y, GFX_ICON *pIcon, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_COLOR color);

/** Draw an image to the display */
GFX_RESULT gfx_common_DrawImage(uint16_t x, uint16_t y, GFX_IMAGE *pImage, GFX_PALETTE pPalette);

/** Draw a portion of an image to the display */
GFX_RESULT gfx_common_DrawImagePortion(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_PALETTE pPalette);

/** Draw a line from one point to another */
GFX_RESULT gfx_common_DrawLine(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Draw a box */
GFX_RESULT gfx_common_DrawBox(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Check for pending events */
GFX_RESULT gfx_common_CheckEvents(_gfx_HandleEvent pfHandleEvent);

/** Add a new event to the event queue */
GFX_RESULT gfx_common_AddEvent(GFX_EVENT evType, uint16_t p1, uint16_t p2);

/* Guard for C++ */
#ifdef __cplusplus
};
#endif

#endif /* __DRIVER_H */
