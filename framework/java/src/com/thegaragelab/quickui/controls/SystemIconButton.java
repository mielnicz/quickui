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
   * @return the width of the Icon in pixels
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
  // SystemIconButton operations
  //-------------------------------------------------------------------------
  
  /** Set the icon to display
   * 
   */
  public void setIcon(int icon) {
    m_sysicon = icon;
    setDirty(true);
    }
  
  }
