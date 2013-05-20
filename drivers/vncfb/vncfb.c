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

/** Default framebuffer width */
#define DEFAULT_DISPLAY_WIDTH  320

/** Default framebuffer height */
#define DEFAULT_DISPLAY_HEIGHT 240

/** The framebuffer */
static GFX_COLOR *g_pFrameBuffer;

/** Our graphics driver structure */
GFX_DRIVER g_GfxDriver;

/** Set the color of a single pixel
 */
static GFX_RESULT gfx_generic_PutPixel(uint16_t x, uint16_t y, GFX_COLOR color) {
  if(GFX_CLIP(x, y))
    g_pFrameBuffer[(y * g_GfxDriver.m_width) + x] = color;
  return GFX_RESULT_OK;
  }

/** Implementation of BeginPaint
 *
 * This does nothing, we have no caching or paging operations to worry about.
 */
static GFX_RESULT gfx_generic_BeginPaint() {
  // Nothing to do
  }

/** Implementation of BeginPaint
 *
 * This does nothing, we have no caching or paging operations to worry about.
 */
static GFX_RESULT gfx_generic_EndPaint() {
  // Nothing to do
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
GFX_RESULT gfx_Init() {
  // Initialise our framebuffer
//  memset(g_pFrameBuffer, 0, GENERIC_DISPLAY_WIDTH * GENERIC_DISPLAY_HEIGHT * sizeof(GFX_COLOR));
  // Set up the driver API
  g_GfxDriver.m_width = DEFAULT_DISPLAY_WIDTH;
  g_GfxDriver.m_height = DEFAULT_DISPLAY_HEIGHT;
  g_GfxDriver.m_pfBeginPaint = gfx_generic_BeginPaint;
  g_GfxDriver.m_pfEndPaint = gfx_generic_EndPaint;
  g_GfxDriver.m_pfPutPixel = gfx_generic_PutPixel;
  g_GfxDriver.m_pfFillRegion = gfx_common_FillRegion;
  g_GfxDriver.m_pfDrawIcon = gfx_common_DrawIcon;
  g_GfxDriver.m_pfDrawIconPortion = gfx_common_DrawIconPortion;
  g_GfxDriver.m_pfDrawImage = gfx_common_DrawImage;
  g_GfxDriver.m_pfDrawImagePortion = gfx_common_DrawImagePortion;
  g_GfxDriver.m_pfDrawLine = gfx_common_DrawLine;
  g_GfxDriver.m_pfDrawBox = gfx_common_DrawBox;
  // All done
  return GFX_RESULT_OK;
  }