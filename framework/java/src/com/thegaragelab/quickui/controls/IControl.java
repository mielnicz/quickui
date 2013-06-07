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

/** This interface defines additional methods that a control must provide.
 *
 * A control generally adds custom events (such as BUTTON_UP, BUTTON_DOWN,
 * etc).
 */
public interface IControl extends IWindow {
  /** Get the text for this control
   * 
   * @return the current text string associated with the control
   */
  public String getText();
  
  /** Set the text for this control
   * 
   * @param the text to display for this control.
   */
  public void setText(String text);
  
  /** Fire an event
   * 
   * @param event the event ID to fire.
   * @param data the data associated with this event.
   */
  public void fireEvent(int event, Object data);
  
  /** Add a listener for an event
   * 
   * @param event the event ID to listen for.
   * @param handler the handler to evoke when the event occurs.
   */
  public void listenFor(int event, IControlEventHandler handler);
  
  }
