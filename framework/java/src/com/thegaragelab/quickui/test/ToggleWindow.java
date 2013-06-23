/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 07/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.test;

//--- Imports
import java.util.*;
import com.thegaragelab.quickui.*;

/** ToggleWindow
 *
 */
public class ToggleWindow extends Window {
  //--- Instance variables
  private Random m_random; //! Random number generator
  
  /** Constructor
   *
   * @param parent
   * @param rect
   */
  public ToggleWindow(Container parent, Rectangle rect) {
    super(parent, rect);
    m_random = new Random();
    setAcceptTouch(true);
    setEraseBackground(true);
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
    // We only want the touch events
    if(evType!=TouchEvent.GFX_EVENT_TOUCH)
      return;
    // Change color when touched
/*    
    Palette palette = Application.getInstance().getPalette();
    if(palette!=null)
      setBackground(palette.getColor(m_random.nextInt(Palette.PALETTE_SIZE)));
    setDirty(true);
*/    
    }

  }
