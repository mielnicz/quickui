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

  /** Constructor with a parent Window and a Rectangle for position and size.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   * @param text the text for this control.
   */
  public SimpleControl(Container parent, IRectangle rect, String text) {
    super(parent, rect, Window.WIN_ACCEPT_TOUCH, 0);
    setText(text);
    }

  //-------------------------------------------------------------------------
  // Internal helpers
  //-------------------------------------------------------------------------

  /** Fire an event
   * 
   * @param event the event ID to fire.
   * @param data the data associated with this event.
   */
  protected final void fireEvent(int event, Object data) {
    ControlHelper.fireEvent(this, event, data);
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
  
  /** Add a listener for an event
   * 
   * @param event the event ID to listen to.
   * @param handler the handler to process the event.
   */
  public final void setEventHandler(int event, IControlEventHandler handler) {
    ControlHelper.setEventHandler(this, event, handler);
    }
  
  }
