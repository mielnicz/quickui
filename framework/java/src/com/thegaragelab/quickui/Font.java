/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 01/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

import java.io.*;
//--- Imports
import java.nio.*;

/** Represents a Font
 * 
 * A font is essentially an Icon (which is how the asset is registered) with
 * some additional metadata. The metadata describes which characters are
 * provided, their width and height and where they are located within the
 * Icon asset.
 *
 */
public class Font extends Asset {
  //--- Constants
  private static int MAX_CHAR = 255; //! Highest ASCII character code
  
  //--- Instance variables
  private int   m_width;   //! Width of the largest character in pixels
  private int   m_height;  //! Height of the font in pixels
  private int[] m_widths;  //! Widths for each character
  private int[] m_xpos;    //! X co-ordinates for each character
  private int[] m_ypos;    //! Y co-ordinates for each character
  private Icon  m_icon;    //! The icon containing the font graphics
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   * 
   * @param data the raw data for the asset.
   * @param size the number of bytes in the data array for the asset.
   */
  Font(byte[] data, int offset, int size) {
    super();
    // Read and verify the font data
    ByteBuffer buffer = ByteBuffer.wrap(data, offset, size);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    int chars = (buffer.get() & 0xFF) + 1;
    m_height = (buffer.get() & 0xFF) + 1;
    int defchar = buffer.get() & 0xFF;
    buffer.get(); // Skip a spacer byte
    // Make sure we have enough data
    if(size<((chars * 4) + 4))
      return;
    // Read the character map
    m_widths = new int[MAX_CHAR + 1];
    m_xpos = new int[MAX_CHAR + 1];
    m_ypos = new int[MAX_CHAR + 1];
    for(int index=0; index<chars; index++) {
      int ch = buffer.get() & 0xFF;
      m_widths[ch] = (buffer.get() & 0xFF) + 1;
      m_xpos[ch] = buffer.get() & 0xFF;
      m_ypos[ch] = buffer.get() & 0xFF;
      // Update the maximum width
      if(m_widths[ch]>m_width)
        m_width = m_widths[ch];
      }
    // Fill in the rest with the default character
    for(int index=0; index<(MAX_CHAR + 1); index++)
      if(m_widths[index]==0) {
        m_widths[index] = m_widths[defchar];
        m_xpos[index] = m_xpos[defchar];
        m_ypos[index] = m_ypos[defchar];
        }
    // Now load the Icon resource itself
    m_icon = new Icon(data, offset + ((chars * 4) + 4), size - ((chars * 4) + 4));
    // Save the raw data
    if(m_icon.getData()!=null) {
      m_data = new byte[size];
      System.arraycopy(data, offset, m_data, 0, size);
      }
    }

  //-------------------------------------------------------------------------
  // Internal helpers
  //-------------------------------------------------------------------------
  
  /** Convert a string to a sequence of ASCII bytes
   * 
   */
  private byte[] toASCII(String string) {
    // Treat null strings as empty
    if(string==null)
      return new byte[0];
    // Convert to ASCII (falling back to empty if we can't)
    try {
      return string.getBytes("US-ASCII");
      } 
    catch (UnsupportedEncodingException e) {
      // Hacky, but avoids exceptions
      return new byte[0];
      }
    }
  
  //-------------------------------------------------------------------------
  // Implementation of IDimension
  //-------------------------------------------------------------------------
  
  /** Set the width of the dimension.
   * 
   * For a Font the width cannot be changed after creation.
   * 
   * @param w the new width of the dimension.
   */
  public void setWidth(int w) {
    // Do nothing
    }
  
  /** Set the height of the dimension.
   * 
   * For a Font the height cannot be changed after creation.
   * 
   * @param h the new height of the dimension.
   */
  public void setHeight(int h) {
    // Do nothing
    }
  
  /** Get the width of the dimension.
   *
   * For a Font the width represents the width of the widest character.
   * 
   * @return the width of the dimension.
   */
  public int getWidth() {
    return m_width;
    }
  
  /** Get the height of the dimension.
   * 
   * For a Font all characters are the same height. This method returns
   * that height.
   * 
   * @return the height of the dimension.
   */
  public int getHeight() {
    return m_height;
    }
  
  //-------------------------------------------------------------------------
  // Font specific operations
  //-------------------------------------------------------------------------
  
  /** Get the size of a specific character
   * 
   * Get the dimensions of a single ASCII character.
   * 
   * @param ch the character to get the size of
   * 
   * @return a Dimension instance describing the size taken by the character.
   */
  public Dimension getCharSize(char ch) {
    return new Dimension(m_widths[(int)ch], m_height);
    }
  
  /** Get the size of a string
   * 
   * Return a Dimension instance describing the graphical space required to
   * render a string.
   * 
   * @param string the String to get the size for.
   * 
   * @return a Dimension instance describing the space taken by the string.
   */
  public Dimension getStringSize(String string) {
    byte[] chars = toASCII(string);
    int width = 0;
    for(byte ch: chars) {
      int index = ch & 0xFF;
      width = width + m_widths[index];
      }
    return new Dimension(width, m_height);
    }
  
  }
