/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 23/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/

package com.thegaragelab.quickui.controls;

import com.thegaragelab.quickui.*;

/** SystemIconButton
 *
 */
public class SystemIconButton extends AbstractIconButton {
  //--- Instance variables
  private int m_sysicon; //! The system icon for the button
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   *
   * @param parent the parent Window for this control
   * @param rect   the rectangle describing the position of this control
   * @param icon   the system icon for this control
   */
  public SystemIconButton(Container parent, Rectangle rect, int icon) {
    super(parent, rect);
    m_sysicon = icon;
    }

  //-------------------------------------------------------------------------
  // Implementation of AbstractIconButton
  //-------------------------------------------------------------------------
  
  /** Get the width of the Icon
   * 
   * @return the width of the Icon in pxiels
   */
  @Override
  protected int getIconWidth() {
    return Application.SYSTEM_ICON_SIZE;
    }

  /** Get the height of the Icon
   * 
   * @return the height of the Icon in pxiels
   */
  @Override
  protected int getIconHeight() {
    return Application.SYSTEM_ICON_SIZE;
    }

  /** Draw the icon at the specific location.
   * 
   * @param where the Point at which the icon should be drawn.
   * @param color the color to draw the Icon in.
   */
  @Override
  protected void drawIcon(IPoint where, Color color) {
    Application.getInstance().drawSystemIcon(where, m_sysicon, color);
    }

  //-------------------------------------------------------------------------
  // Implementation of IWindow
  //-------------------------------------------------------------------------
  
  /**
   * @see com.thegaragelab.quickui.controls.SimpleControl#onEraseBackground()
   */
  @Override
  public void onEraseBackground() {
    super.onEraseBackground();
    // Draw the border
    drawBox(
      new Rectangle(Point.ORIGIN, this),
      Application.getInstance().getSystemColor(Application.SYS_COLOR_CTRL_HIGHLIGHT)
      );
    }

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
      Application.getInstance().getSystemColor(Application.SYS_COLOR_CTRL_FOREGROUND),
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
