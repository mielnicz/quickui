/*--------------------------------------------------------------------------*
* Simple test program for the quickgfx library.
*---------------------------------------------------------------------------*
* 20-May-2013 ShaneG
*
* This is a simple test program to exercise the driver (just to ensure that
* graphics are being sent to the output device). All it does is draw random
* rectangles on the screen until it is killed.
*--------------------------------------------------------------------------*/
#include <sys/time.h>
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <quickgfx.h>

// Restrictions
#define MIN_RECT_WIDTH  10
#define MAX_RECT_WIDTH  100
#define MIN_RECT_HEIGHT 10
#define MAX_RECT_HEIGHT 100

// Colors
static GFX_PALETTE g_Palette = {
  GFX_COLOR_FROM_RGB(0,     0, 0),
  GFX_COLOR_FROM_RGB(0,     0, 127),
  GFX_COLOR_FROM_RGB(0,   127, 0),
  GFX_COLOR_FROM_RGB(0,   127, 127),
  GFX_COLOR_FROM_RGB(127,   0, 0),
  GFX_COLOR_FROM_RGB(127,   0, 127),
  GFX_COLOR_FROM_RGB(127, 127, 0),
  GFX_COLOR_FROM_RGB(127, 127, 127),
  GFX_COLOR_FROM_RGB(127, 127, 127),
  GFX_COLOR_FROM_RGB(127, 127, 255),
  GFX_COLOR_FROM_RGB(127, 255, 127),
  GFX_COLOR_FROM_RGB(127, 255, 255),
  GFX_COLOR_FROM_RGB(255, 127, 127),
  GFX_COLOR_FROM_RGB(255, 127, 255),
  GFX_COLOR_FROM_RGB(255, 255, 127),
  GFX_COLOR_FROM_RGB(255, 255, 255),
  };

/** Get the current time stamp in milliseconds
 *
 */
static long currentTimeMillis() {
  struct timeval tsNow;
  gettimeofday(&tsNow, NULL);
  return (tsNow.tv_sec * 1000) + tsNow.tv_usec;
  }

/** Program entry point
 */
int main(int argc, char *argv[]) {
  GFX_RESULT result;
  long now;
  int x, y, w, h;
  // Show a banner
  printf("QuickGFX Test Program\n");
  // Initialise the graphics driver
  result = gfx_Init(0, 0);
  if(result!=GFX_RESULT_OK) {
	printf("ERROR: Initialisation failed with code %i\n", result);
	return 1;
    }
  // Now go into a loop drawing rectangles
  while(true) {
	now = currentTimeMillis();
	// Check for input events
	if(!(now%250))
	  gfx_CheckEvents(NULL);
	// Draw a rectangle
	if(!(now%1000)) {
	  // Pick a size and position for the rectangle
	  w = (rand() % (MAX_RECT_WIDTH - MIN_RECT_WIDTH)) + MIN_RECT_WIDTH;
	  h = (rand() % (MAX_RECT_HEIGHT - MIN_RECT_HEIGHT)) + MIN_RECT_HEIGHT;
	  x = rand() % (g_GfxDriver.m_width - w);
	  y = rand() % (g_GfxDriver.m_height - h);
	  gfx_FillRegion(x, y, x + w, y + h, g_Palette[rand() % GFX_PALETTE_SIZE]);
	  }
    }
  // All done
  return 0;
  }
