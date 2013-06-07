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

/** A complex control may contain child windows or controls.
 * 
 * Unlike a simple control that manages everything for itself a complex
 * control may utilise simple (or complex) controls as child windows to
 * achieve it's purpose.
 */
public class ComplexControl extends Container implements IControl {
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
  public ComplexControl(Container parent, Rectangle rect, int require, int exclude) {
    super(parent, rect, Window.WIN_ACCEPT_TOUCH | require, exclude);    
    }

  /** Constructor with a parent Window and a Rectangle for position and size.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   */
  public ComplexControl(Container parent, Rectangle rect) {
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
    if(m_text.compareTo(text)==0) {
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
   * @param event the event ID to listen for.
   * @param handler the handler to evoke when the event occurs.
   */
  public final void listenFor(int event, IControlEventHandler handler) {
    ControlHelper.listenFor(this, event, handler);
    }
  
  }
