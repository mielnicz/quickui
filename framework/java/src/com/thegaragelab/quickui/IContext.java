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
public interface IContext {
  /** Vertical alignment
   */
  public enum VerticalAlignment {
    PARENT, //! Use parent window alignment
    TOP,    //! Align elements to the top of the window
    MIDDLE, //! Align elements to the middle of the window
    BOTTOM  //! Align elements to the bottom of the window
    };
  
  /** Vertical alignment
   */
  public enum HorizontalAlignment {
    PARENT, //! Use parent window alignment
    LEFT,   //! Align elements to the left of the window
    CENTER, //! Align elements to center of the window
    RIGHT   //! Align elements to the right of the window
    };
    
  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------

  /** Get the font for this context
   * 
   * @return the font to use for this context.
   */
  public Font getFont();
  
  /** Set the font for this context
   * 
   * @param font the font to use for this context.
   */
  public void setFont(Font font);
  
  /** Get the color for this context
   * 
   * @return a Color instance to use for the foreground (icons and text)
   */
  public Color getForeground();
  
  /** Set the foreground color for this context
   * 
   * @param color the color to use as the foreground.
   */
  public void setForeground(Color color);
  
  /** Get the background color for this context
   * 
   * @return a Color instance to use for the background.
   */
  public Color getBackground();
  
  /** Set the background color for this context
   * 
   * @param color the color to use as the background.
   */
  public void setBackground(Color color);
  
  /** Get the padding to use when drawing.
   *
   * @return the current padding to use.
   */
  public Padding getPadding();
  
  /** Set the padding for drawing.
   * 
   * @param padding the padding to use when drawing.
   */
  public void setPadding(Padding padding);
  
  /** Get the vertical alignment for elements
   * 
   * @return the vertical alignment to use for this window.
   */
  public VerticalAlignment getVerticalAlignment();
  
  /** Set the vertical alignment for elements
   * 
   * @param align the vertical alignment to use.
   */
  public void setVerticalAlignment(VerticalAlignment align);
  
  /** Get the horizontal alignment for elements
   * 
   * @return the horizontal alignment to use for this window.
   */
  public HorizontalAlignment getHorizontalAlignment();
  
  /** Set the horizontal alignment for elements
   * 
   * @param align the horizontal alignment to use.
   */
  public void setHorizontalAlignment(HorizontalAlignment align);
  
  }
