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
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxInit
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxInit(JNIEnv *pEnv, jobject obj, jint width, jint height) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxBeginPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxBeginPaint(JNIEnv *pEnv, jobject obj) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxEndPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxEndPaint(JNIEnv *pEnv, jobject obj) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxPutPixel
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxPutPixel(JNIEnv *pEnv, jobject obj, jint x, jint y, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxFillRegion
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxFillRegion(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawIcon
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawIcon(JNIEnv *pEnv, jobject obj, jint x, jint y, jint icon, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawIconPortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawIconPortion(JNIEnv *pEnv, jobject obj, jint x, jint y, jint icon, jint sx, jint sy, jint w, jint h, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawImage
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawImage(JNIEnv *pEnv, jobject obj, jint x, jint y, jint image, jint palette) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawImagePortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawImagePortion(JNIEnv *pEnv, jobject obj, jint x, jint y, jint image, jint sx, jint sy, jint w, jint h, jint palette) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawLine
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawLine(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawBox
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawBox(JNIEnv *pEnv, jobject obj, jint x1, jint y1, jint x2, jint y2, jint color) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxCheckEvents
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxCheckEvents(JNIEnv *pEnv, jobject obj) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxRegisterAsset
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxRegisterAsset (JNIEnv *pEnv, jobject obj, jint type, jbyteArray data, jint offset, jint size) {
  return (jint)GFX_RESULT_INTERNAL;
  }

