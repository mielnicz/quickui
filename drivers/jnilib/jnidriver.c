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
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxInit(JNIEnv *, jobject, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxBeginPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxBeginPaint(JNIEnv *, jobject) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxEndPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxEndPaint(JNIEnv *, jobject) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxPutPixel
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxPutPixel(JNIEnv *, jobject, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxFillRegion
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxFillRegion(JNIEnv *, jobject, jint, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawIcon
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawIcon(JNIEnv *, jobject, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawIconPortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawIconPortion(JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawImage
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawImage(JNIEnv *, jobject, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawImagePortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawImagePortion(JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawLine
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawLine(JNIEnv *, jobject, jint, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxDrawBox
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxDrawBox(JNIEnv *, jobject, jint, jint, jint, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxCheckEvents
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxCheckEvents(JNIEnv *, jobject) {
  return (jint)GFX_RESULT_INTERNAL;
  }

/*
 * Class:     com_thegaragelab_quickui_driver_Driver
 * Method:    gfxRegisterAsset
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_driver_Driver_gfxRegisterAsset (JNIEnv *, jobject, jint, jbyteArray, jint, jint) {
  return (jint)GFX_RESULT_INTERNAL;
  }

