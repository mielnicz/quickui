/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.nio.*;

/** Represents an image asset
 * 
 * For QuickUI an icon is a 4 bit per pixel image where each pixel represents
 * an index into a palette. The palette is detached from the image so images
 * can be used with whatever palette is preferred which allows a simple form
 * of themeing.
 */
public class Image extends Asset implements IDimension {
  //--- Instance variables
  private int m_width;  //! Width of the image in pixels
  private int m_height; //! Height of the image in pixels
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   * 
   * @param data the raw data for the asset.
   * @param size the number of bytes in the data array for the asset.
   */
  Image(byte[] data, int offset, int size) {
    super();
    // Verify the data
    ByteBuffer buffer = ByteBuffer.wrap(data, offset, size);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    m_width = 1 + buffer.get();
    m_height = 1 + buffer.get();
    // Determine the size of the data we expect
    int expected = (m_width / 2) + (((m_width%2)==0)?0:1);
    expected = expected * m_height;
    if(size!=(expected + 2))
      return;
    // Try and register it
    m_handle = Driver.getInstance().registerAsset(Asset.IMAGE, data, offset, size);
    if(m_handle<0)
      return;
    }
  
  //-------------------------------------------------------------------------
  // Implementation of IDimension
  //-------------------------------------------------------------------------
  
  /** Get the width of the dimension.
   * 
   * @return the width of the dimension.
   */
  public int getWidth() {
    return m_width;
    }
  
  /** Set the width of the dimension.
   * 
   * @param w the new width of the dimension.
   */
  public void setWidth(int w) {
    // Do nothing
    }
  
  /** Get the height of the dimension.
   * 
   * @return the height of the dimension.
   */
  public int getHeight() {
    return m_height;
    }
  
  /** Set the height of the dimension.
   * 
   * @param h the new height of the dimension.
   */
  public void setHeight(int h) {
    // Do nothing
    }
  
  }
