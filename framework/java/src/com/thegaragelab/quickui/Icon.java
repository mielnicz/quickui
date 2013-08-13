/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents an icon asset
 * 
 * For QuickUI an icon is a monochrome image where each pixel has one of
 * two states - solid or transparent. They are generally used to overlay
 * an icon (in a specific color) on top of a background image.
 * 
 * Icons are often grouped into sprite sheets so a single Icon asset will
 * have multiple icons in it's image.
 * 
 * The implementation of Icon is just a special case of Image.
 */
public class Icon extends Image {
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   * 
   * @param data the raw data for the asset.
   * @param size the number of bytes in the data array for the asset.
   */
  Icon(byte[] data, int offset, int size) {
    super(data, offset, size);
    }
  
  }
