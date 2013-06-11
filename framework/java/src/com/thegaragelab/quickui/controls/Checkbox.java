/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 11/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

//--- Imports
import com.thegaragelab.quickui.*;

/** Implements a simple checkbox control.
 *
 */
public class Checkbox extends SimpleControl {
  //--- Control events
  public static final int EVENT_CHECKED   = 0; //! Sent when the control is checked
  public static final int EVENT_UNCHECKED = 1; //! Sent when the control is unchecked
  public static final int EVENT_CHANGED   = 2; //! The state has changed
  
  //--- Instance variables
  private boolean m_checked; //! Current state of the control
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   *
   * @param parent the parent Window for this control
   * @param rect   the rectangle describing the position of this control
   * @param text   the text for the the control.
   */
  public Checkbox(Container parent, Rectangle rect, String text) {
    super(parent, rect, text);
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
    Dimension size = getFont().getStringSize(getText());
    Padding padding = getPadding();
    return padding.getPaddingLeft() + ControlHelper.ICON_WIDTH + ControlHelper.ICON_PADDING + size.getWidth() + padding.getPaddingRight();
    }
  
  /** Get the preferred height of this control
   * 
   * This method should calculate the preferred height of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred height in pixels
   */
  public int getPreferredHeight() {
    Dimension size = getFont().getStringSize(getText());
    Padding padding = getPadding();
    return padding.getPaddingTop() + Math.max(ControlHelper.ICON_HEIGHT, size.getHeight()) + padding.getPaddingRight();
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
    Font font = getFont();
    // Get the size of what we want to draw and where to draw it
    Dimension size = font.getStringSize(getText());
    size.width = size.width + ControlHelper.ICON_WIDTH + ControlHelper.ICON_PADDING;
    size.height = Math.max(size.height, ControlHelper.ICON_HEIGHT);
    Point where = ControlHelper.getPosition(this, size, getPadding(), getHorizontalAlignment(), getVerticalAlignment());
    // Draw the icon
    if(isChecked())
      ControlHelper.drawControlIcon(this, where, ControlHelper.ICON_CHECK_SELECTED, getForeground());
    else
      ControlHelper.drawControlIcon(this, where, ControlHelper.ICON_CHECK_EMPTY, getForeground());
    // Adjust to handle where the text should go
    where.x = where.x + ControlHelper.ICON_WIDTH + ControlHelper.ICON_PADDING;
    where.y = where.y + (ControlHelper.ICON_HEIGHT - font.getHeight()) / 2;
    drawString(font, where, getForeground(), getText());
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
    if(evType==TouchEvent.GFX_EVENT_RELEASE)
      setChecked(!isChecked());
    }

  //-------------------------------------------------------------------------
  // Implementation of Checkbox
  //-------------------------------------------------------------------------
  
  /** Get the state of the checkbox
   * 
   * @return true if the checkbox is currently checked, false if not
   */
  public boolean isChecked() {
    return m_checked;
    }
  
  /** Change the checkbox state
   * 
   * @param checked true if the checkbox should be shown as checked,
   *                false if not.
   */
  public void setChecked(boolean checked) {
    // Any change ?
    if(checked==m_checked)
      return;
    // Save the state, set the dirty flag
    m_checked = checked;
    Boolean args = new Boolean(m_checked);
    if(isChecked())
      fireEvent(EVENT_CHECKED, null);
    else
      fireEvent(EVENT_UNCHECKED, null);
    fireEvent(EVENT_CHANGED, args);
    // Set it as dirty
    setDirty(true);
    }
  
  }
