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
  
  }
