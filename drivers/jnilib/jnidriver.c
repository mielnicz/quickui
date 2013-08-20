/*---------------------------------------------------------------------------*
* JNI interface to the selected driver.
*----------------------------------------------------------------------------*
* 23-May-2013 ShaneG
*
* Provides an interface between Java and the QuickGFX low level driver.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <quickgfx.h>
#include "jnidriver.h"

/*--------------------------------------------------------------------------*
* These globals and functions are used to help manage events.
*--------------------------------------------------------------------------*/

//--- Constants
#define DRIVER_CLASS "com/thegaragelab/quickui/Driver"

//--- Globals
static JNIEnv   *g_pEnv   = NULL; //! JNI Environment structure
static jobject   g_obj    = NULL; //! Calling object
static jclass    g_class  = NULL; //! The class of the calling object
static jmethodID g_method = NULL; //! The method to invoke

/** Initialise the event capturing function.
 *
 * Ensures the state is set up so the JNI code can call back to the parent
 * object to post event messages.
 *
 * @param pEnv the JNI environment pointer.
 * @param obj the object calling this method.
 */
static void initEventCapture(JNIEnv *pEnv, jobject obj) {
  if(g_class==NULL) {
    // Get the class and the method description
    g_class = (*pEnv)->FindClass(pEnv, DRIVER_CLASS);
    if(g_class==NULL)
      return;
    g_method = (*pEnv)->GetMethodID(pEnv, g_class, "pushEvent", "(III)V");
    if(g_method==NULL) {
      g_class = NULL;
      return;
      }
    }
  // Check the object type
/*
  if((*pEnv)->IsInstanceOf(pEnv, obj, g_class)) {
    // Save state
    g_pEnv = pEnv;
    g_obj = obj;
    }
*/
  // Save state
  g_pEnv = pEnv;
  g_obj = obj;
  }

/** Handle an event
 *
 * An implementation of this function is provided by the calling application
 * and is used to receive information about events from the driver.
 */
static GFX_RESULT internal_HandleEvent(GFX_TOUCH_EVENT_INFO *pEventInfo) {
  // Make sure we have the state we need
  if((g_pEnv==NULL)||(g_obj==NULL)||(g_class==NULL)||(g_method==NULL))
    return GFX_RESULT_OK;
  // Invoke the function
  (*g_pEnv)->CallVoidMethod(g_pEnv, g_obj, g_method, pEventInfo->m_event, pEventInfo->m_xpos, pEventInfo->m_ypos);
  return GFX_RESULT_OK;
  }

/** Finished event handling
 *
 */
static void doneEventCapture() {
  g_pEnv = NULL;
  g_obj = NULL;
  }

/*--------------------------------------------------------------------------*
* Java function interface to the library.
*--------------------------------------------------------------------------*/

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxInit
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxInit(JNIEnv *pEnv, jobject obj, jint width, jint height) {
  return (jint)gfx_Init(width, height);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxBeginPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxBeginPaint(JNIEnv *pEnv, jobject obj) {
  return (jint)gfx_BeginPaint();
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxEndPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxEndPaint(JNIEnv *pEnv, jobject obj) {
  return (jint)gfx_EndPaint();
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxSetClip
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxSetClip(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2) {
  return (jint)gfx_SetClip(x1, y1, x2, y2);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxPutPixel
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxPutPixel(JNIEnv *pEnv, jobject obj, jint x, jint y, jint color) {
  return (jint)gfx_PutPixel(x, y, color);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxFillRegion
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxFillRegion(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2, jint color) {
  return (jint)gfx_FillRegion(x1, y1, x2, y2, color);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawChar
 * Signature: ([BIIIB)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawChar(JNIEnv *pEnv, jobject obj, jbyteArray font, jint x, jint y, jint color, jbyte ch) {
  // The font is required
  if(font==NULL)
    return GFX_RESULT_BADARG;
  GFX_FONT *pFont = (GFX_FONT *)(*pEnv)->GetByteArrayElements(pEnv, font, NULL);
  if(pFont==NULL)
    return GFX_RESULT_INTERNAL;
  // Now draw the character
  jint result = (jint)gfx_DrawChar(x, y, pFont, color, ch);
  // Clean up and return
  (*pEnv)->ReleaseByteArrayElements(pEnv, font, pFont, JNI_ABORT);
  return result;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawString
 * Signature: ([BIII[B)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawString(JNIEnv *pEnv, jobject obj, jbyteArray font, jint x, jint y, jint color, jbyteArray str) {
  // The font and string are both required
  if((font==NULL)||(str==NULL))
    return GFX_RESULT_BADARG;
  GFX_FONT *pFont = (GFX_FONT *)(*pEnv)->GetByteArrayElements(pEnv, font, NULL);
  if(pFont==NULL)
    return GFX_RESULT_INTERNAL;
  uint8_t *pString = (uint8_t *)(*pEnv)->GetByteArrayElements(pEnv, str, NULL);
  if(pString==NULL) {
    (*pEnv)->ReleaseByteArrayElements(pEnv, font, pFont, JNI_ABORT);
    return GFX_RESULT_INTERNAL;
    }
  // Now draw the character
  jint result = (jint)gfx_DrawString(x, y, pFont, color, pString);
  // Clean up and return
  (*pEnv)->ReleaseByteArrayElements(pEnv, font, pFont, JNI_ABORT);
  (*pEnv)->ReleaseByteArrayElements(pEnv, str, pString, JNI_ABORT);
  return result;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawImage
 * Signature: (II[BIIII[BI[B)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawImage(JNIEnv *pEnv, jobject obj, jint x, jint y, jbyteArray image, jint sx, jint sy, jint w, jint h, jbyteArray mask, jint color, jbyteArray palette) {
  // The image is required
  if(image==NULL)
    return GFX_RESULT_BADARG;
  GFX_IMAGE *pImage = (GFX_IMAGE *)(*pEnv)->GetByteArrayElements(pEnv, image, NULL);
  if(pImage==NULL)
    return GFX_RESULT_INTERNAL;
  // Collect the rest of the arguments
  bool bFreeMask = false, bFreePalette = false;
  GFX_PALETTE *pPalette = NULL;
  GFX_IMAGE *pMask = NULL;
  jint result = (jint)GFX_RESULT_INTERNAL;
  // Do we have a mask ?
  if(mask!=NULL) {
    pMask = (GFX_IMAGE *)(*pEnv)->GetByteArrayElements(pEnv, mask, NULL);
    if(pMask==NULL)
      goto gfxDrawImage_cleanup;
    bFreeMask = true;
    // Make sure the mask is a monochrome image
    if(pMask->m_header.m_bpp!=IMAGE_BPP_1) {
      result = (jint)GFX_RESULT_BADARG;
      goto gfxDrawImage_cleanup;
      }
    }
  // If the main image is 4bpp we need a palette
  if(pImage->m_header.m_bpp==IMAGE_BPP_4) {
    if(palette==NULL) {
      result = (jint)GFX_RESULT_BADARG;
      goto gfxDrawImage_cleanup;
      }
    pPalette = (GFX_COLOR *)(*pEnv)->GetByteArrayElements(pEnv, palette, NULL);
    if(pPalette==NULL)
      goto gfxDrawImage_cleanup;
    bFreePalette = true;
    }
  // Do the rendering
  switch(pImage->m_header.m_bpp) {
    case IMAGE_BPP_1 :
      result = (jint)gfx_DrawIcon(x, y, pImage, sx, sy, w, h, pMask, color);
      break;
    case IMAGE_BPP_4 :
      result = (jint)gfx_DrawImage4(x, y, pImage, sx, sy, w, h, pMask, pPalette);
      break;
    case IMAGE_BPP_16:
      result = (jint)gfx_DrawImage16(x, y, pImage, sx, sy, w, h, pMask);
      break;
    default:
      result = (jint)GFX_RESULT_BADARG;
    }
gfxDrawImage_cleanup:
  // Free up the image data
  (*pEnv)->ReleaseByteArrayElements(pEnv, image, pImage, JNI_ABORT);
  // Free up mask data (if used)
  if(bFreeMask&&(pMask!=NULL))
    (*pEnv)->ReleaseByteArrayElements(pEnv, mask, pMask, JNI_ABORT);
  // Free up palette data (if used)
  if(bFreePalette&&(pPalette!=NULL))
    (*pEnv)->ReleaseByteArrayElements(pEnv, palette, pPalette, JNI_ABORT);
  // All done
  return (jint)result;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawLine
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawLine(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2, jint color) {
  return (jint)gfx_DrawLine(x1, y1, x2, y2, color);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawBox
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawBox(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2, jint color) {
  return (jint)gfx_DrawBox(x1, y1, x2, y2, color);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxCheckEvents
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxCheckEvents(JNIEnv *pEnv, jobject obj) {
  GFX_RESULT result = GFX_RESULT_OK;
  initEventCapture(pEnv, obj);
  result = gfx_CheckEvents(internal_HandleEvent);
  doneEventCapture();
  return (jint)result;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxRegisterAsset
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxRegisterAsset (JNIEnv *pEnv, jobject obj, jint type, jbyteArray data, jint offset, jint size) {
  return (jint)addAsset(pEnv, data, offset, size);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxGetWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxGetWidth(JNIEnv *pEnv, jobject obj) {
  return (jint)g_GfxDriver.m_width;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxGetHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxGetHeight(JNIEnv *pEnv, jobject obj) {
  return (jint)g_GfxDriver.m_height;
  }

  /*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxGetBufferSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxGetBufferSize(JNIEnv *pEnv, jobject obj) {
  return (jint)(g_GfxDriver.m_width * g_GfxDriver.m_height * 2);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxGetBuffer
 * Signature: ([B)I
 */
JNIEXPORT void JNICALL Java_com_thegaragelab_quickui_Driver_gfxGetBuffer(JNIEnv *pEnv, jobject obj, jbyteArray data) {
  // See if we can get a buffer at all
  uint8_t *pBuffer = gfx_Framebuffer();
  if(pBuffer==NULL)
    return;
  // Copy it into the array provided.
  jbyte *pData = (*pEnv)->GetByteArrayElements(pEnv, data, NULL);
  if(pData==NULL)
    return;
  memcpy(pData, pBuffer, g_GfxDriver.m_width * g_GfxDriver.m_height * 2);
  // Release the array
  (*pEnv)->ReleaseByteArrayElements(pEnv, data, pData, 0);
  }



