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
public class Button extends SimpleControl {
  //--- Button events
  public static final int EVENT_TOUCHED = 0; //! Fired when the button was touched and released
  
  //--- Instance variables
  private Padding m_innerPadding; //! Padding between text and button
  private boolean m_depressed;    //! True if the button is touched
  
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
    m_innerPadding = Padding.DEFAULT;
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
    return padding.getPaddingLeft() + m_innerPadding.getPaddingLeft() + size.getWidth() + m_innerPadding.getPaddingRight() + padding.getPaddingRight();
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
    return padding.getPaddingTop() + m_innerPadding.getPaddingTop() + size.getHeight() + m_innerPadding.getPaddingBottom() + padding.getPaddingBottom();
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
    // Determine the size of the rectangle we are drawing in
    Rectangle container = new Rectangle(Point.ORIGIN, this);
    Padding padding = getPadding();
    container.x = container.x + padding.getPaddingLeft();
    container.width = container.width - padding.getPaddingLeft() - padding.getPaddingRight();
    container.y = container.y + padding.getPaddingTop();
    container.height = container.height - padding.getPaddingTop() - padding.getPaddingBottom();
    // Get the size of the text and where to draw it
    Dimension text = font.getStringSize(getText());
    Point where = ControlHelper.getPosition(container, text, m_innerPadding, getHorizontalAlignment(), getVerticalAlignment());
    where.translate(container);
    // Now draw in the appropriate state
    if(isDepressed()) {
      // Invert the colors
      fillRect(container, getForeground());
      drawString(getFont(), where, getBackground(), getText());
      }
    else {
      fillRect(container, getBackground());
      drawString(getFont(), where, getForeground(), getText());
      }
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
    if(evType==TouchEvent.GFX_EVENT_TOUCH) {
      m_depressed = true;
      setDirty(true);
      }
    else if(evType==TouchEvent.GFX_EVENT_RELEASE) {
      m_depressed = false;
      setDirty(true);
      this.fireEvent(EVENT_TOUCHED, null);
      }
    }

  //-------------------------------------------------------------------------
  // Implementation of Button
  //-------------------------------------------------------------------------
  
  /** Determine if the button is currently depressed
   * 
   * @return true if the button is in a depressed state.
   */
  public boolean isDepressed() {
    return m_depressed;
    }
  
  }
