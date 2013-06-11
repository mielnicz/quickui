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
  //--- Alignment constants
  public static final int PARENT = 0; //! Use the parent alignment
  public static final int TOP    = 1; //! Align to the top of the window
  public static final int MIDDLE = 2; //! Align to the middle of the window
  public static final int BOTTOM = 3; //! Align to the bottom of the window
  public static final int LEFT   = 4; //! Align to the left of the window
  public static final int CENTER = 5; //! Align to the center of the window
  public static final int RIGHT  = 6; //! Align to the right of the window
    
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
  public int getVerticalAlignment();
  
  /** Set the vertical alignment for elements
   * 
   * @param align the vertical alignment to use.
   */
  public void setVerticalAlignment(int align);
  
  /** Get the horizontal alignment for elements
   * 
   * @return the horizontal alignment to use for this window.
   */
  public int getHorizontalAlignment();
  
  /** Set the horizontal alignment for elements
   * 
   * @param align the horizontal alignment to use.
   */
  public void setHorizontalAlignment(int align);
  
  }
