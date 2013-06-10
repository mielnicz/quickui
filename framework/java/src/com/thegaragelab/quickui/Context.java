/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 08/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents a graphics context.
 * 
 * A graphics context holds a number of default values regarding how graphics are
 * drawn. Holding all of this in a single object means that windows can refer to
 * a single shared instance but still have a custom instance if needed.
 */
public class Context {
  //--- Instance variables
  private Context m_parent; //! The parent context
  private Font    m_font;   //! The font to use
  private Color   m_color;  //! The foreground color to use
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------

  /** Get the font for this context
   * 
   * @return the font to use for this context.
   */
  public Font getFont() {
    // If we have a font, use that
    if(m_font!=null)
      return m_font;
    // Can we use the parent context font ?
    if(m_parent!=null)
      return m_parent.getFont();
    // Otherwise we need the system font
    return Application.getInstance().getFont();
    }
  
  /** Set the font for this context
   * 
   * @param font the font to use for this context.
   */
  public void setFont(Font font) {
    m_font = font;
    }
  
  /** Get the color for this context
   * 
   * @return a Color instance to use for the foreground (icons and text)
   */
  public Color getForeground() {
    // If we have a color, use it
    if(m_color!=null)
      return m_color;
    // Can we use the parent context color ?
    if(m_parent!=null)
      return m_parent.getForeground();
    // Otherwise we need the system color
    return Color.RED;
    }
  
  /** Set the foreground color for this context
   * 
   * @param color the color to use for this context.
   */
  public void setForeground(Color color) {
    m_color = color;
    }
  
  }
