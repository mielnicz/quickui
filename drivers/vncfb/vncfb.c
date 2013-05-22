/*---------------------------------------------------------------------------*
* Graphics driver using a VNC virtual framebuffer
*----------------------------------------------------------------------------*
* 24-Apr-2013 shaneg
*
* This driver creates a network accessable framebuffer through the VNC
* protocol.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <string.h>
#include <quickgfx.h>
#include <gfxdriver.h>
#include <rfb.h>

/** Default framebuffer width */
#define DEFAULT_DISPLAY_WIDTH  320

/** Default framebuffer height */
#define DEFAULT_DISPLAY_HEIGHT 240

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
  // Yes, we do - send an update
  rfbMarkRectAsModified(g_pScreenInfo, g_minX, g_minY, g_maxX, g_maxY);
  // Clear the region
  g_minX = 0;
  g_maxX = 0;
  g_minY = 0;
  g_maxY = 0;
  // And done
  return GFX_RESULT_OK;
  }

/** Check for pending events
 */
static GFX_RESULT gfx_vnc_CheckEvents(_gfx_HandleEvent pfHandleEvent) {
  rfbProcessEvents(g_pScreenInfo, 10000);
  return GFX_RESULT_OK;
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
  // Initialise the VNC virtual screen
  g_pScreenInfo = rfbGetScreen(NULL, NULL, DEFAULT_DISPLAY_WIDTH, DEFAULT_DISPLAY_HEIGHT, 5, 3, 2);
  if(g_pScreenInfo==NULL)
	return GFX_RESULT_INTERNAL;
  // Initialise our framebuffer
  g_pFrameBuffer = (GFX_COLOR *)calloc(DEFAULT_DISPLAY_WIDTH * DEFAULT_DISPLAY_HEIGHT, sizeof(GFX_COLOR));
  if(g_pFrameBuffer==NULL)
    return GFX_RESULT_MEMORY;
  g_pScreenInfo->frameBuffer = (char *)g_pFrameBuffer;
  rfbInitServer(g_pScreenInfo);
  // Set up the driver API
  g_GfxDriver.m_width = DEFAULT_DISPLAY_WIDTH;
  g_GfxDriver.m_height = DEFAULT_DISPLAY_HEIGHT;
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
  // All done
  return GFX_RESULT_OK;
  }
