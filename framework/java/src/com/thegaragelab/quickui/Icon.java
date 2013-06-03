/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.nio.*;

/** Represents an icon asset
 * 
 * For QuickUI an icon is a monochrome image where each pixel has one of
 * two states - solid or transparent. They are generally used to overlay
 * an icon (in a specific color) on top of a background image.
 * 
 * Icons are often grouped into sprite sheets so a single Icon asset will
 * have multiple icons in it's image.
 */
public class Icon extends Asset implements IDimension {
  //--- Instance variables
  private int m_width;  //! Width of the icon in pixels
  private int m_height; //! Height of the icon in pixels
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   * 
   * @param data the raw data for the asset.
   * @param size the number of bytes in the data array for the asset.
   */
  Icon(byte[] data, int size) {
    super();
    // Verify the data
    ByteBuffer buffer = ByteBuffer.wrap(data, 0, size);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    m_width = (buffer.get() & 0xFF) + 1;
    m_height = (buffer.get() & 0xFF) + 1;
    // Determine the size of the data we expect
    int expected = (m_width / 8) + (((m_width%8)==0)?0:1);
    expected = expected * m_height;
    if(size!=(expected + 2))
      return;
    // Try and register it
    m_handle = Driver.getInstance().registerAsset(Asset.ICON, data, 0, size);
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
