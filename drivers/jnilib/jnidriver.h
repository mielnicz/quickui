/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_thegaragelab_quickui_Driver */

#ifndef _Included_com_thegaragelab_quickui_Driver
#define _Included_com_thegaragelab_quickui_Driver
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxInit
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxInit
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxBeginPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxBeginPaint
  (JNIEnv *, jobject);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxEndPaint
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxEndPaint
  (JNIEnv *, jobject);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxPutPixel
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxPutPixel
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxFillRegion
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxFillRegion
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawIcon
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawIcon
  (JNIEnv *, jobject, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawIconPortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawIconPortion
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawImage
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawImage
  (JNIEnv *, jobject, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawImagePortion
 * Signature: (IIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawImagePortion
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawLine
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawLine
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxDrawBox
 * Signature: (IIIII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxDrawBox
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxCheckEvents
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxCheckEvents
  (JNIEnv *, jobject);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxRegisterAsset
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxRegisterAsset
  (JNIEnv *, jobject, jint, jbyteArray, jint, jint);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxGetWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxGetWidth
  (JNIEnv *, jobject);

/*
 * Class:     com_thegaragelab_quickui_Driver
 * Method:    gfxGetHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_thegaragelab_quickui_Driver_gfxGetHeight
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
