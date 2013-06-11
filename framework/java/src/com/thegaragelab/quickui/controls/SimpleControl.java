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
import com.thegaragelab.quickui.utils.*;

/** A simple control provides IControl functionality to a Window
 * 
 * This is the base class for Control instances that are simple and self
 * contained - they do not have any child windows and manage themselves.
 * This includes controls like a TextInput, RadioButton or CheckBox.
 */
public abstract class SimpleControl extends Window implements IControl {
  //--- Instance variables
  private String m_text; //! Text for this control
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  /** Constructor with a parent Window a position and flags to set or clear.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   * @param require additional flags to set
   * @param exclude flags to mask out
   */
  public SimpleControl(Container parent, Rectangle rect, int require, int exclude) {
    super(parent, rect, Window.WIN_ACCEPT_TOUCH | require, exclude);    
    }

  /** Constructor with a parent Window and a Rectangle for position and size.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   */
  public SimpleControl(Container parent, Rectangle rect) {
    super(parent, rect, Window.WIN_ACCEPT_TOUCH, 0);
    }

  //-------------------------------------------------------------------------
  // Control specific operations
  //-------------------------------------------------------------------------

  /** Get the text for this control
   * 
   * @return the current text string associated with the control
   */
  public String getText() {
    if(m_text==null)
      m_text = "";
    return m_text;
    }
  
  /** Set the text for this control
   * 
   * @param the text to display for this control.
   */
  public void setText(String text) {
    // Handle null parameters
    if(text==null)
      text = "";
    // Has the text changed?
    if((m_text==null)||(m_text.compareTo(getText())==0)) {
      m_text = text;
      setDirty(true);
      }
    }
  
  /** Fire an event
   * 
   * @param event the event ID to fire.
   * @param data the data associated with this event.
   */
  public final void fireEvent(int event, Object data) {
    ControlHelper.fireEvent(this, event, data);
    }
  
  /** Add a listener for an event
   * 
   */
  public final void listenFor(int event, IControlEventHandler handler) {
    ControlHelper.listenFor(this, event, handler);
    }
  
  }
