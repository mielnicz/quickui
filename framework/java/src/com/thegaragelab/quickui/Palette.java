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

/** Represents a 16 color palette.
 * 
 * A palette is normal used to display an image although it can be a useful
 * resource for using a specific set of colors.
 */
public class Palette extends Asset {
  //--- Constants
  public static final int PALETTE_SIZE = 16; //! Size of a palette
  
  //--- Instance variables
  private Color[] m_colors; //! The actual colors in the palette
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   * 
   * @param data the raw data for the asset.
   * @param size the number of bytes in the data array for the asset.
   */
  Palette(byte[] data, int size) {
    super();
    // Verify the data
    if(size!=(2 * PALETTE_SIZE))
      return;
    // Try and register it
    m_handle = Driver.getInstance().registerAsset(Asset.PALETTE, data, 0, size);
    if(m_handle<0)
      return;
    // Now build up our internal state
    m_colors = new Color[PALETTE_SIZE];
    ByteBuffer buffer = ByteBuffer.wrap(data, 0, size);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    for(int index=0; index<PALETTE_SIZE; index++)
      m_colors[index] = new Color(buffer.getShort());
    }

  //-------------------------------------------------------------------------
  // Palette specific operations
  //-------------------------------------------------------------------------
  
  /** Get the color at the given index
   * 
   * @param index the index of the color to retrieve. This value will be
   *              clamped to the range of the palette.
   *              
   * @return the color at the given index.
   */
  public Color getColor(int index) {
    // Clamp the index value
    if(index<0)
      index = 0;
    if(index>=PALETTE_SIZE)
      index = PALETTE_SIZE - 1;
    // Return the requested color
    return m_colors[index];
    }
  
  }
