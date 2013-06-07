/*---------------------------------------------------------------------------*
* Graphics driver using a VNC virtual framebuffer
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This driver creates a network accessable framebuffer through the VNC
* protocol.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <stdbool.h>
#include <string.h>
#include <quickgfx.h>
#include <gfxdriver.h>
#include <rfb.h>

/* Default size for the framebuffer */
#define DEFAULT_DISPLAY_WIDTH  320
#define DEFAULT_DISPLAY_HEIGHT 240

/* Minimum size for the framebuffer */
#define MIN_DISPLAY_WIDTH  128
#define MIN_DISPLAY_HEIGHT 128

/* Maximum size for the framebuffer */
#define MAX_DISPLAY_WIDTH  1024
#define MAX_DISPLAY_HEIGHT 768

/** The global graphics driver structure */
GFX_DRIVER g_GfxDriver;

/* Internal state
 */
static GFX_COLOR       *g_pFrameBuffer;
static rfbScreenInfoPtr g_pScreenInfo;
static int              g_paintLevel;
static int              g_minX;
static int              g_maxX;
static int              g_minY;
static int              g_maxY;

//---------------------------------------------------------------------------
// Event management
//---------------------------------------------------------------------------

/** Handle movement/touch events from the remote client
 *
 * @param buttonMask indicates the state of the pointer buttons.
 * @param x the X position of the event (in screen co-ordinates).
 * @param y the Y position of the event (in screen co-ordinates).
 * @param cl the client sending the event.
 */
static void vnc_PointerEvent(int buttonMask, int x, int y, rfbClientPtr cl) {
  static bool s_last = false;
  bool state = (buttonMask & 0x01) == 0x01;
  // If the state has changed, send the new state
  if(s_last!=state)
    gfx_AddEvent(state?GFX_EVENT_TOUCH:GFX_EVENT_RELEASE, x, y);
  else if(state)
    gfx_AddEvent(GFX_EVENT_DRAG, x, y);
  s_last = state;
  }

//---------------------------------------------------------------------------
// Implementation of Driver functions
//---------------------------------------------------------------------------

/** Set the color of a single pixel
 */
static GFX_RESULT gfx_vnc_PutPixel(uint16_t x, uint16_t y, GFX_COLOR color) {
  if(GFX_CLIP(x, y))
    g_pFrameBuffer[(y * g_GfxDriver.m_width) + x] = color;
  // Change the current update region
  g_minX = (x<g_minX)?x:g_minX;
  g_minY = (y<g_minY)?y:g_minY;
  g_maxX = (x>g_maxX)?x:g_maxX;
  g_maxY = (y>g_maxY)?y:g_maxY;
  // All done
  return GFX_RESULT_OK;
  }

/** Implementation of BeginPaint
 *
 */
static GFX_RESULT gfx_vnc_BeginPaint() {
  g_paintLevel++;
  return GFX_RESULT_OK;
  }

/** Implementation of BeginPaint
 *
 */
static GFX_RESULT gfx_vnc_EndPaint() {
  g_paintLevel--;
  // Do we need to send an update ?
  if(g_paintLevel>0)
	return GFX_RESULT_OK;
  // Yes, we do - send an update (if one is available)
  if((g_maxX>0)&&(g_maxY>0))
    rfbMarkRectAsModified(g_pScreenInfo, g_minX, g_minY, g_maxX + 1, g_maxY + 1);
  // Clear the region
  g_minX = g_GfxDriver.m_width;
  g_maxX = 0;
  g_minY = g_GfxDriver.m_height;
  g_maxY = 0;
  // And done
  return GFX_RESULT_OK;
  }

/** Check for pending events
 */
static GFX_RESULT gfx_vnc_CheckEvents(_gfx_HandleEvent pfHandleEvent) {
  rfbProcessEvents(g_pScreenInfo, 10000);
  return gfx_common_CheckEvents(pfHandleEvent);
  }

/** Get the framebuffer the driver is using
 *
 * This function may return a pointer to the framebuffer used by the driver.
 * The pixel format of the framebuffer (and if a framebuffer exists at all)
 * is driver independant so it should be used with care.
 *
 * @return a pointer to the framebuffer or NULL if the driver does not allow
 *         direct framebuffer access.
 */
const void *gfx_Framebuffer() {
  return (const void *)g_pFrameBuffer;
  }

/** Initialise the driver
 *
 * This function is implemented by the driver and is required to fill out the
 * driver SPI structure.
 */
GFX_RESULT gfx_Init(uint16_t width, uint16_t height) {
  // Set internal state
  g_paintLevel = 0;
  g_minX = 0;
  g_maxX = 0;
  g_minY = 0;
  g_maxY = 0;
  // Verify the width and height requested
  if(width<=0)
    width = DEFAULT_DISPLAY_WIDTH;
  if(width<MIN_DISPLAY_WIDTH)
    width = MIN_DISPLAY_WIDTH;
  if(width>MAX_DISPLAY_WIDTH)
    width = MAX_DISPLAY_WIDTH;
  if(height<=0)
    height = DEFAULT_DISPLAY_HEIGHT;
  if(height<MIN_DISPLAY_HEIGHT)
    height = MIN_DISPLAY_HEIGHT;
  if(height>MAX_DISPLAY_HEIGHT)
    height = MAX_DISPLAY_HEIGHT;
  // Initialise the VNC virtual screen
  rfbLogEnable(0);
  g_pScreenInfo = rfbGetScreen(NULL, NULL, width, height, 5, 3, 2);
  if(g_pScreenInfo==NULL)
	return GFX_RESULT_INTERNAL;
  // Set the pixel format
  g_pScreenInfo->serverFormat.redShift = 11;
  g_pScreenInfo->serverFormat.greenShift = 6;
  g_pScreenInfo->serverFormat.blueShift = 0;
  // Add handlers for events
  g_pScreenInfo->ptrAddEvent = vnc_PointerEvent;
  // Initialise our framebuffer
  g_pFrameBuffer = (GFX_COLOR *)calloc(width * height, sizeof(GFX_COLOR));
  if(g_pFrameBuffer==NULL)
    return GFX_RESULT_MEMORY;
  g_pScreenInfo->frameBuffer = (char *)g_pFrameBuffer;
  rfbInitServer(g_pScreenInfo);
  // Set up the driver API
  g_GfxDriver.m_width = width;
  g_GfxDriver.m_height = height;
  g_GfxDriver.m_pfBeginPaint = gfx_vnc_BeginPaint;
  g_GfxDriver.m_pfEndPaint = gfx_vnc_EndPaint;
  g_GfxDriver.m_pfPutPixel = gfx_vnc_PutPixel;
  g_GfxDriver.m_pfFillRegion = gfx_common_FillRegion;
  g_GfxDriver.m_pfDrawIcon = gfx_common_DrawIcon;
  g_GfxDriver.m_pfDrawIconPortion = gfx_common_DrawIconPortion;
  g_GfxDriver.m_pfDrawImage = gfx_common_DrawImage;
  g_GfxDriver.m_pfDrawImagePortion = gfx_common_DrawImagePortion;
  g_GfxDriver.m_pfDrawLine = gfx_common_DrawLine;
  g_GfxDriver.m_pfDrawBox = gfx_common_DrawBox;
  g_GfxDriver.m_pfCheckEvents = gfx_vnc_CheckEvents;
  g_GfxDriver.m_pfAddEvent = gfx_common_AddEvent;
  // All done
  return GFX_RESULT_OK;
  }
