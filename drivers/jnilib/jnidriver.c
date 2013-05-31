/*---------------------------------------------------------------------------*
* JNI interface to the selected driver.
*----------------------------------------------------------------------------*
* 23-May-2013 ShaneG
*
* Provides an interface between Java and the QuickGFX low level driver.
*---------------------------------------------------------------------------*/
#include <stdint.h>
#include <string.h>
#include <quickgfx.h>
#include "jnidriver.h"

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
  return (jint)GFX_RESULT_OK;
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
 * Method:    gfxDrawIcon
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawIcon(JNIEnv *pEnv, jobject obj, jint x, jint y, jint icon, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawIconPortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawIconPortion(JNIEnv *pEnv, jobject obj, jint x, jint y, jint icon, jint sx, jint sy, jint w, jint h, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawImage
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawImage(JNIEnv *pEnv, jobject obj, jint x, jint y, jint image, jint palette) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawImagePortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawImagePortion(JNIEnv *pEnv, jobject obj, jint x, jint y, jint image, jint sx, jint sy, jint w, jint h, jint palette) {
  return (jint)GFX_RESULT_INTERNAL;
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

/** Handle an event
 *
 * An implementation of this function is provided by the calling application
 * and is used to receive information about events from the driver.
 */
static GFX_RESULT internal_HandleEvent(GFX_EVENT_INFO *pEventInfo) {
  return GFX_RESULT_OK;
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxCheckEvents
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxCheckEvents(JNIEnv *pEnv, jobject obj) {
  return (jint)gfx_CheckEvents(internal_HandleEvent);
  }

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxRegisterAsset
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxRegisterAsset (JNIEnv *pEnv, jobject obj, jint type, jbyteArray data, jint offset, jint size) {
  return (jint)GFX_RESULT_INTERNAL;
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
