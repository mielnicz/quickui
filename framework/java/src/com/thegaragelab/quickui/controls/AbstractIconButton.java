/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 23/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

//--- Imports
import com.thegaragelab.quickui.*;

/** Base class for buttons based on Icons
 *
 */
public abstract class AbstractIconButton extends SimpleControl implements IButton {
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   *
   * @param parent the parent Window for this control
   * @param rect   the rectangle describing the position of this control
   */
  public AbstractIconButton(Container parent, Rectangle rect) {
    super(parent, rect, "");
    }

  //-------------------------------------------------------------------------
  // Helpers for an Icon based button (implemented by subclass)
  //-------------------------------------------------------------------------
  
  /** Get the width of the Icon
   * 
   * @return the width of the Icon in pxiels
   */
  protected abstract int getIconWidth();
  
  /** Get the height of the Icon
   * 
   * @return the height of the Icon in pxiels
   */
  protected abstract int getIconHeight();
  
  /** Draw the icon at the specific location.
   * 
   * @param where the Point at which the icon should be drawn.
   * @param color the color to draw the Icon in.
   */
  protected abstract void drawIcon(IPoint where, Color color);
  
  //-------------------------------------------------------------------------
  // Implementation of IWindow
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
    Padding padding = getPadding();
    return padding.getPaddingLeft() + getIconWidth() + padding.getPaddingRight();
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
    Padding padding = getPadding();
    return padding.getPaddingTop() + getIconHeight() + padding.getPaddingBottom();
    }
  
  /** Called when the window needs to be painted
   * 
   *  This method is called to redraw the window.
   */
  @Override
  public void onPaint() {
    super.onPaint();
    Padding padding = getPadding();
    drawIcon(
      new Point(padding.getPaddingLeft(), padding.getPaddingLeft()),
      getColor()
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
