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

/** Control event dispatacher
 * 
 * This class provides static methods for allow listening to events from a
 * control and dispatching events to any registered listeners. It is a private
 * class that is not accessible outside the package.
 */
class ControlHelper {
  //--- Constants (internal)
  private static final String COMMON_CONTROLS = "controls";
  
  //--- Constants (public)
  public static final int ICON_WIDTH  = 16;
  public static final int ICON_HEIGHT = 16;
  
  //--- Static instance variables
  private static Icon m_icons; //! Icons for common controls
  
  //-------------------------------------------------------------------------
  // Helper methods
  //-------------------------------------------------------------------------

  /** Dispatch a message from a control to any registered listeners.
   * 
   */
  public static void fireEvent(IControl source, int event, Object params) {
    }
  
  /** Add a listener for specific messages from a known source.
   * 
   */
  public static void listenFor(IControl source, int event, IControlEventHandler handler) {
    }
  
  /** Draw a control icon at the requested co-ordinates
   * 
   */
  public static void drawControlIcon(ISurface surface, IPoint where, int icon, Color color) {
    // Make sure we have icons
    synchronized(ControlHelper.class) {
      if(m_icons==null) {
        m_icons = Asset.loadIcon(COMMON_CONTROLS);
        }
      if(m_icons==null)
        return;
      }
    // Draw the requested icon
    // TODO: This is not very generic, it assume all icons are on the same line.
    surface.drawIcon(
      where,
      m_icons,
      color,
      new Rectangle(
        icon * ICON_WIDTH,
        0,
        ICON_WIDTH,
        ICON_HEIGHT
        )
      );
    }
  
  }
