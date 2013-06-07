/*---------------------------------------------------------------------------*
* Common implementation of graphics functions
*----------------------------------------------------------------------------*
* 05-Jun-2013 shaneg
*
* This file implements a common version of the function that can be used by
* any graphics driver.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <stdlib.h>
#include <quickgfx.h>
#include <gfxdriver.h>

//--- Constants
#define EVENT_QUEUE_GROWTH 16

//--- Globals
static GFX_TOUCH_EVENT_INFO *g_pEvents    = NULL;
static int                   g_eventCount = 0;
static int                   g_eventSize  = 0;

/** Add a new event to the event queue
 */
GFX_RESULT gfx_common_AddEvent(GFX_TOUCH_EVENT evType, uint16_t x, uint16_t y) {
  GFX_TOUCH_EVENT_INFO *pQueue;
  // Do we need to grow the queue ?
  if(g_eventCount==g_eventSize) {
    pQueue = (GFX_TOUCH_EVENT_INFO *)realloc(g_pEvents, (g_eventSize + EVENT_QUEUE_GROWTH) * sizeof(GFX_TOUCH_EVENT_INFO));
    if(pQueue==NULL)
      return GFX_RESULT_OK; // TODO: Should be a more meaningful error code
    g_eventSize += EVENT_QUEUE_GROWTH;
    g_pEvents = pQueue;
    }
  // Add the new event
  g_pEvents[g_eventCount].m_event = evType;
  g_pEvents[g_eventCount].m_xpos = x;
  g_pEvents[g_eventCount].m_ypos = y;
  g_eventCount++;
  // All done
  return GFX_RESULT_OK;
  }

/** Process pending events
 */
GFX_RESULT gfx_common_CheckEvents(_gfx_HandleEvent pfHandleEvent) {
  int index;
  GFX_RESULT result = GFX_RESULT_OK;
  // Do we have something to handle events ?
  if(pfHandleEvent) {
    for(index=0; index<g_eventCount; index++) {
      result = (*pfHandleEvent)(&g_pEvents[index]);
      if(result!=GFX_RESULT_OK)
        break;
      }
    }
  // Purge all events
  g_eventCount = 0;
  // And we are done
  return result;
  }
