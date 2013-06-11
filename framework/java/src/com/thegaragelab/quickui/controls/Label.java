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
 * A label simply displays a string of text and consumes but does not respond
 * to any touch events.
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
    super(parent, rect);
    }

  /** Constructor with a parent, position and size as well as text.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   * @param text the initial text to display
   */
  public Label(Container parent, Rectangle rect, String text) {
    super(parent, rect);
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
    // Get the size information
    Font font = getFont();
    Padding padding = getPadding();
    Dimension size = font.getStringSize(getText());
    // Calculate the X position of the text
    int x = 0;
    switch(getHorizontalAlignment()) {
      case LEFT:
        x = padding.getPaddingLeft();
        break;
      case CENTER:
        x = (getWidth() - padding.getPaddingLeft() - padding.getPaddingRight() - size.getWidth()) / 2;
        break;
      case RIGHT:
        x = getWidth() - padding.getPaddingRight() - size.getWidth();
        break;
      }
    // Calculate the Y position of the text
    int y = 0;
    switch(getVerticalAlignment()) {
      case TOP:
        y = padding.getPaddingTop();
        break;
      case MIDDLE:
        y = (getHeight() - padding.getPaddingTop() - padding.getPaddingBottom() - size.getHeight()) / 2;
        break;
      case BOTTOM:
        y = getHeight() - padding.getPaddingBottom() - size.getHeight();
        break;
      }
    // Draw the text
    this.drawString(
      font,
      new Point(x, y),
      getForeground(),
      getText()
      );
    }

  }
