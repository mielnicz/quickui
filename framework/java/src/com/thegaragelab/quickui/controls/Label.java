/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 08/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

//--- Imports
import com.thegaragelab.quickui.*;

/** This control represents a simple label.
 *
 * A label simply displays a string of text and does not respond to any touch
 * events. The label is aligned left horizontally and centered vertically by
 * default.
 */
public class Label extends SimpleControl {
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  /** Constructor with a parent Window and a Rectangle for position and size.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   */
  public Label(Container parent, Rectangle rect) {
    super(parent, rect, 0, Window.WIN_ACCEPT_TOUCH);
    }

  /** Constructor with a parent, position and size as well as text.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   * @param text the initial text to display
   */
  public Label(Container parent, Rectangle rect, String text) {
    super(parent, rect, 0, Window.WIN_ACCEPT_TOUCH);
    setText(text);
    }

  //-------------------------------------------------------------------------
  // Implementation of IControl
  //-------------------------------------------------------------------------
  
  /** Get the preferred width of this control
   * 
   * This method should calculate the preferred with of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred width in pixels
   */
  public int getPreferredWidth() {
    Dimension text = getFont().getStringSize(getText());
    Padding padding = getPadding();
    return padding.getPaddingLeft() + text.getWidth() + padding.getPaddingRight();
    }
  
  /** Get the preferred height of this control
   * 
   * This method should calculate the preferred height of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred height in pixels
   */
  public int getPreferredHeight() {
    Dimension text = getFont().getStringSize(getText());
    Padding padding = getPadding();
    return padding.getPaddingTop() + text.getHeight() + padding.getPaddingBottom();
    }
  

  //-------------------------------------------------------------------------
  // Implementation of IWindow
  //-------------------------------------------------------------------------

  /** Called when the window needs to be painted
   * 
   *  This method is called to redraw the window.
   */
  @Override
  public void onPaint() {
    super.onPaint();
    // Get the font to use
    Font font = getFont();
    // Calculate the text location
    Point where = new Point(
      0,
      (getHeight() - font.getHeight()) / 2
      );
    // Draw the text
    this.drawString(
      font,
      where,
      getForeground(),
      getText()
      );
    }

  }
