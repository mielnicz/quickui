/*---------------------------------------------------------------------------*
* Defines the driver interface to the graphics hardware
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This file defines the interface to the graphics driver (the code that spits
* out pixels to the screen). Rather than make it overly generic there are a
* few assumptions:
*
* 1/ All pixels are 16 bits wide (the bit format is not important)
* 2/ The screen width is fixed, it's always 320x240
* 3/ The driver must provide (at a minimum) the ability to initialise the
*    screen and draw a single pixel of a fixed color. Other operations are
*    implemented by the common library but will not be optimised for the
*    target hardware.
*---------------------------------------------------------------------------*/
#ifndef __QUICKGFX_H
#define __QUICKGFX_H

/* Extra definitions we need */
#include <stdint.h>

/* Guard for C++ */
#ifdef __cplusplus
extern "C" {
#endif

//---------------------------------------------------------------------------
// Core constants
//---------------------------------------------------------------------------

/** Size of palettes (number of colors) */
#define GFX_PALETTE_SIZE 16

//---------------------------------------------------------------------------
// Common types and structures
//---------------------------------------------------------------------------

/** Return codes
 */
typedef enum _GFX_RESULT {
  GFX_RESULT_OK = 0, //! Operation succeeded
  GFX_RESULT_MEMORY, //! Out of memory
  GFX_RESULT_FAILED, //! Generic failure (when no other data is available)
  } GFX_RESULT;

// These structures are all byte aligned (space is at a premium)
#pragma pack(push, 1)

/** A single 16 bit color for final display
 */
typedef uint16_t GFX_COLOR;

/** Palette to map 4 bit color information to native colors
 */
typedef GFX_COLOR GFX_PALETTE[GFX_PALETTE_SIZE];

/** Event types
 *
 * This enum defines the event types we recognise.
 */
typedef enum _GFX_EVENT {
  GFX_EVENT_NONE = 0, //! Used for a NULL events. Can be ignored.
  GFX_EVENT_KEY_DOWN, //! Key down event
  GFX_EVENT_KEY_UP,   //! Key up event
  GFX_EVENT_TOUCH,    //! Touch event
  GFX_EVENT_RELEASE,  //! Release event
  } GFX_EVENT;

/** Represents a single event
 *
 * This structure holds information about an event.
 */
typedef struct _GFX_EVENT_INFO {
  GFX_EVENT m_event;  //! The type of event that was detected
  uint16_t  m_param1; //! The first parameter for the event
  uint16_t  m_param2; //! The second parameter for the event
  } GFX_EVENT_INFO;

/** Image header information
 *
 * This structure provides common information about an image regardless of
 * it's format.
 */
typedef struct _GFX_IMAGE_HEADER {
  uint8_t m_width; //! Width of the image in pixels
  uint8_t m_height; //! Height of the image in pixels
  } GFX_IMAGE_HEADER;

/** A compressed image
 *
 * Images are stored with 4 bits per pixel providing an index into a dynamic
 * color lookup table. This means a single image can contain at most 16 distinct
 * colors but those colors can be chosen from the full range of colors provided
 * by the driver.
 */
typedef struct _GFX_IMAGE {
  GFX_IMAGE_HEADER m_header;  //! Image information
  uint16_t         m_data[1]; //! The actual image data
  } GFX_IMAGE;

/** This structure represents an icon
 *
 * An icon is a monochrome image bit packed into byte values. When rendered
 * the 'on' bits (those set to one) are changed to the specified color. The
 * 'off' bits are ignored and allow the image behind to show though.
 */
typedef struct _GFX_ICON {
  GFX_IMAGE_HEADER m_header;  //! Image information
  uint8_t          m_data[1]; //! The actual image data
  } GFX_ICON;

// Revert to normal structure packing
#pragma pack(pop)

//---------------------------------------------------------------------------
// Macros
//---------------------------------------------------------------------------

/** Create a 16 bit color from 3 color components
 *
 * The 16 bit color is in the format rrrrrggggggbbbbb, if the driver expects
 * a different format you will have to do the conversion on the fly.
 */
#define GFX_COLOR_FROM_RGB(r, g, b) \
  ((uint16_t)(( \
    ((((uint16_t)(r)) & 0xF8) << 11) | \
    ((((uint16_t)(g)) & 0xFC) << 3) | \
    ((((uint16_t)(b)) & 0xF8) >> 3) ) \
    & 0xFFFF \
    ))

/** Calculate the length (in bytes) of a line in an icon image
 */
#define GFX_LINE_LENGTH_ICON(icon) \
  ((((icon).m_header.m_width - 1) / 8) + 1)

/** Calculate the length (in bytes) of a line in an image
 */
#define GFX_LINE_LENGTH_IMAGE(image) \
  ((((image).m_header.m_width * 4 - 1) / 8) + 1)

//---------------------------------------------------------------------------
// Graphics driver SPI
//---------------------------------------------------------------------------

/** Handle an event
 *
 * An implementation of this function is provided by the calling application
 * and is used to receive information about events from the driver.
 */
typedef GFX_RESULT (*_gfx_HandleEvent)(GFX_EVENT_INFO *pEventInfo);

/** Begin a multipart paint operation */
typedef GFX_RESULT (*_gfx_BeginPaint)();

/** Finish a multipart paint operation */
typedef GFX_RESULT (*_gfx_EndPaint)();

/** Set the color of a single pixel */
typedef GFX_RESULT (*_gfx_PutPixel)(uint16_t x, uint16_t y, GFX_COLOR color);

/** Fill a region with the specified color */
typedef GFX_RESULT (*_gfx_FillRegion)(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Draw an icon to the display */
typedef GFX_RESULT (*_gfx_DrawIcon)(uint16_t x, uint16_t y, GFX_ICON *pIcon, GFX_COLOR color);

/** Draw a portion of an icon to the display */
typedef GFX_RESULT (*_gfx_DrawIconPortion)(uint16_t x, uint16_t y, GFX_ICON *pIcon, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_COLOR color);

/** Draw an image to the display */
typedef GFX_RESULT (*_gfx_DrawImage)(uint16_t x, uint16_t y, GFX_IMAGE *pImage, GFX_PALETTE pPalette);

/** Draw a portion of an image to the display */
typedef GFX_RESULT (*_gfx_DrawImagePortion)(uint16_t x, uint16_t y, GFX_IMAGE *pImage, uint8_t sx, uint8_t sy, uint8_t w, uint8_t h, GFX_PALETTE pPalette);

/** Draw a line from one point to another */
typedef GFX_RESULT (*_gfx_DrawLine)(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Draw a box */
typedef GFX_RESULT (*_gfx_DrawBox)(uint16_t x1, uint16_t y1, uint16_t x2, uint16_t y2, GFX_COLOR color);

/** Process pending events
 */
typedef GFX_RESULT (*_gfx_CheckEvents)(_gfx_HandleEvent pfHandleEvent);

/** The graphics driver API
 */
typedef struct _GFX_DRIVER {
  uint16_t              m_width;              //! Width of the display in pixels
  uint16_t              m_height;             //! Height of the display in pixels
  _gfx_BeginPaint       m_pfBeginPaint;       //! Begin a paint operation
  _gfx_EndPaint         m_pfEndPaint;         //! End a paint operation
  _gfx_PutPixel         m_pfPutPixel;         //! Place a single pixel
  _gfx_FillRegion       m_pfFillRegion;       //! Fill a region with a single color
  _gfx_DrawIcon         m_pfDrawIcon;         //! Draw an entire icon
  _gfx_DrawIconPortion  m_pfDrawIconPortion;  //! Draw a portion of an icon
  _gfx_DrawImage        m_pfDrawImage;        //! Draw an entire image
  _gfx_DrawImagePortion m_pfDrawImagePortion; //! Draw a portion of an image
  _gfx_DrawLine         m_pfDrawLine;         //! Draw a line
  _gfx_DrawBox          m_pfDrawBox;          //! Draw a box
  _gfx_CheckEvents      m_pfCheckEvents;      //! Check for pending events
  } GFX_DRIVER;

//---------------------------------------------------------------------------
// Graphics driver API
//---------------------------------------------------------------------------

/** The driver singleton */
extern GFX_DRIVER g_GfxDriver;

/** Initialise the driver
 *
 * This function is implemented by the driver and is required to fill out the
 * driver SPI structure. You can request a specific size for the framebuffer
 * but the driver is not required to honor it. Always check the width and
 * height members for the driver structure to get the actual size.
 *
 * @param width the requested width of the framebuffer
 * @param height the requested height of the framebuffer
 *
 * @return GFX_RESULT_OK if the driver is ready to use, another error code if
 *                       things didn't go well.
 */
GFX_RESULT gfx_Init(uint16_t width, uint16_t height);

/** Get the framebuffer the driver is using
 *
 * This function may return a pointer to the framebuffer used by the driver.
 * The pixel format of the framebuffer (and if a framebuffer exists at all)
 * is driver independant so it should be used with care.
 *
 * @return a pointer to the framebuffer or NULL if the driver does not allow
 *         direct framebuffer access.
 */
const void *gfx_Framebuffer();

/** Begin a paint operation
 */
#define gfx_BeginPaint() (*g_GfxDriver.m_pfBeginPaint)()

/** End a paint operation
 */
#define gfx_EndPaint() (*g_GfxDriver.m_pfEndPaint)()

/** Set the color of a single pixel
 *
 * @param pDriver the SPI driver to use
 * @param x the x position of the pixel to set
 * @param y the y position of the pixel to set
 * @param color the color to set the pixel to
 *
 * @return GFX_RESULT_OK if everything was ok.
 */
#define gfx_PutPixel(x, y, color) (*g_GfxDriver.m_pfPutPixel)(x, y, color)

/** Fill a region with the specified color
 */
#define gfx_FillRegion(x1, y1, x2, y2, color) (*g_GfxDriver.m_pfFillRegion)(x1, y1, x2, y2, color)

/** Draw an icon to the display */
#define gfx_DrawIcon(x, y, pIcon, color) (*g_GfxDriver.m_pfDrawIcon)(x, y, pIcon, color)

/** Draw a portion of an icon to the display */
#define gfx_DrawIconPortion(x, y, pIcon, sx, sy, w, h, color) (*g_GfxDriver.m_pfDrawIconPortion)(x, y, pIcon, sx, sy, w, h, color)

/** Draw an image to the display */
#define gfx_DrawImage(x, y, pImage, pPalette) (*g_GfxDriver.m_pfDrawImage)(x, y, pImage, pPalette)

/** Draw a portion of an image to the display */
#define gfx_DrawImagePortion(x, y, pImage, sx, sy, w, h, pPalette) (*g_GfxDriver.m_pfDrawImagePortion)(x, y, pImage, sx, sy, w, h, pPalette)

/** Draw a line from one point to another */
#define gfx_DrawLine(x1, y1, x2, y2, color) (*g_GfxDriver.m_pfDrawLine)(x1, y1, x2, y2, color)

/** Draw a box */
#define gfx_DrawBox(x1, y1, x2, y2, color) (*g_GfxDriver.m_pfDrawBox)(x1, y1, x2, y2, color)

/** Check for pending events */
#define gfx_CheckEvents(pfHandleEvent) (*g_GfxDriver.m_pfCheckEvents)(pfHandleEvent)

/** Clip a value
 *
 * @param x the x co-ordinate to clip
 * @param y the y co-ordinate to clip
 *
 * @return true if the co-ordinates can be displayed, false if not
 */
#define GFX_CLIP(x, y) \
  (((x)>=0)&&((x)<g_GfxDriver.m_width)&&((y)>=0)&&((y)<g_GfxDriver.m_height))

/** Clamp the X co-ordinate
 *
 * @param x the X co-ordinate to clamp
 *
 * @return the clamped value that will fit on the screen
 */
#define GFX_CLAMP_X(x) \
  (((x)<0)?0:(((x)>=g_GfxDriver.m_width)?(g_GfxDriver.m_width - 1):(x)))

/** Clamp the Y co-ordinate
 *
 * @param y the Y co-ordinate to clamp
 *
 * @return the clamped value that will fit on the screen
 */
#define GFX_CLAMP_Y(y) \
  (((y)<0)?0:(((y)>=g_GfxDriver.m_height)?(g_GfxDriver.m_height - 1):(y)))

/* Guard for C++ */
#ifdef __cplusplus
};
#endif

#endif /* __QUICKGFX_H */
