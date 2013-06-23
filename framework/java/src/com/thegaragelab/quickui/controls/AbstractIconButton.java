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
  //--- Instance variables
  private Color m_color; //! The current color
  
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
  // Helpers for an Icon based button
  //-------------------------------------------------------------------------
  
  /** Set the color for the Icon
   * 
   * @param color the Color to use for the icon or null to use default.
   */
  public void setColor(Color color) {
    m_color = color;
    }
  
  /** Get the color for the Icon
   * 
   * @return the Color to use for the icon.
   */
  public Color getColor() {
    if(m_color!=null)
      return m_color;
    // Use the default (Control foreground)
    return Application.getInstance().getSystemColor(Application.SYS_COLOR_CTRL_FOREGROUND);
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
  // Implementation of IControl
  //-------------------------------------------------------------------------
  
  /** Get the preferred width of this control
   * 
   * This method should calculate the preferred with of the control in pixels
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
  
  }
