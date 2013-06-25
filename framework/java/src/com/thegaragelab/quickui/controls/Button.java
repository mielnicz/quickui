/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 12/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

//--- Imports
import com.thegaragelab.quickui.*;

/** Implements a simple push button.
 *
 */
public class Button extends SimpleControl implements IButton {
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
  *
  * @param parent the parent Window for this control
  * @param rect   the rectangle describing the position of this control
  * @param text   the text for the the control.
  */
  public Button(Container parent, Rectangle rect, String text) {
    super(parent, rect, text);
    setPadding(Padding.BUTTON);
    }

  //-------------------------------------------------------------------------
  // Implementation of IControl
  //-------------------------------------------------------------------------
  
  /** Get the preferred width of this control
   * 
   * This method should calculate the preferred width of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred width in pixels
   */
  @Override
  public int getPreferredWidth() {
    Dimension size = Application.getInstance().getFont().getStringSize(getText());
    Padding padding = getPadding();
    return padding.getPaddingLeft() + size.getWidth() + padding.getPaddingRight();
    }
  
  /** Get the preferred height of this control
   * 
   * This method should calculate the preferred height of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred height in pixels
   */
  @Override
  public int getPreferredHeight() {
    Dimension size = Application.getInstance().getFont().getStringSize(getText());
    Padding padding = getPadding();
    return padding.getPaddingTop() + size.getHeight() + padding.getPaddingBottom();
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
    Font font = Application.getInstance().getFont();
    Dimension size = font.getStringSize(getText());
    // Draw the text on top
    drawString(
      font,
      ControlHelper.getPosition(this, size, getPadding(), getHorizontalAlignment(), getVerticalAlignment()),
      getColor(),
      getText()
      );
    }

  /** Called when an input event is targeted to this window
   * 
   * An input event represents a key press or activity on the touch screen.
   * In general, unless you are implementing a control, you don't need to
   * do anything with these events.
   * 
   * @param evType the type of the event
   * @param where the location of the event (in window co-ordinates)
   */
  @Override
  public void onTouchEvent(int evType, IPoint where) {
    super.onTouchEvent(evType, where);
    if(evType==TouchEvent.GFX_EVENT_RELEASE)
      this.fireEvent(EVENT_TOUCHED, null);
    }

  }
