/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents a single colors
 * 
 * Colors are represented by RGB triples and mapped to the native RGB565
 * format used by the graphics driver. Color objects are immutable and
 * cannot be changed after creation.
 */
public class Color {
  //--- Constants
  public static final Color WHITE = new Color(0xff, 0xff, 0xff);
  public static final Color BLACK = new Color(0x00, 0x00, 0x00);
  public static final Color RED   = new Color(0xff, 0x00, 0x00);
  public static final Color GREEN = new Color(0x00, 0xff, 0x00);
  public static final Color BLUE  = new Color(0x00, 0x00, 0xff);
  
  //--- Instance variables
  private int m_native; //! The native RGB565 representation of the color
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor from red, green and blue components.
   * 
   *   This constructor takes the individual components as parameters (each
   *   expressed in the range 0 to 255 inclusive) and creates the suitable
   *   native representation.
   *   
   *   @param r the red component of the color (from 0 to 255 inclusive)
   *   @param g the green component of the color (from 0 to 255 inclusive)
   *   @param b the blue component of the color (from 0 to 255 inclusive)
   */
  public Color(int r, int g, int b) {
    m_native = (
      (((r & 0xF8) << 8) & 0xF800) |
      (((g & 0xFC) << 3) & 0x07E0) |
      (((b & 0xF8) >> 3) & 0x001F));
    m_native = m_native & 0xFFFF;
    }

  /** Constructor from an existing native representation.
   * 
   * @param source the native representation of the color.
   */
  public Color(int source) {
    m_native = source & 0xffff;
    }
  
  //-------------------------------------------------------------------------
  // Getters
  //-------------------------------------------------------------------------
  
  /** Get the native representation of the color.
   * 
   * @return the 16 bit RGB565 representation of the color.
   */
  public int getNativeFormat() {
    return m_native;
    }
  
  }
