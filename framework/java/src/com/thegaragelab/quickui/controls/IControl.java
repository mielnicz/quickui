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
  //--- Alignment constants
  public static final int ALIGN_LEFT   = 0; //! Align contents to the left
  public static final int ALIGN_TOP    = 0; //! Align contents to the top
  public static final int ALIGN_RIGHT  = 1; //! Align contents to the right
  public static final int ALIGN_BOTTOM = 1; //! Align contents to the bottom
  public static final int ALIGN_CENTER = 2; //! Align contents to the center
  public static final int ALIGN_MIDDLE = 2; //! Align contents to the middle
  
  //-------------------------------------------------------------------------
  // Control specific methods
  //-------------------------------------------------------------------------
  
  /** Get the padding for this control
   * 
   * @return the padding settings used to display this control
   */
  public Padding getPadding();
  
  /** Set the padding for this control
   * 
   * @param padding the new padding for this control.
   */
  public void setPadding(Padding padding);
  
  /** Get the vertical alignment for this control
   * 
   * @return the vertical alignment for this control
   */
  public int getVerticalAlignment();
  
  /** Set the vertical alignment for this control
   *   
   * @param alignment the vertical alignment to use for this control
   */
  public void setVerticalAlignment(int alignment);
  
  /** Get the horizontal alignment for this control
   * 
   * @return the horizontal alignment for this control
   */
  public int getHorizontalAlignment();
  
  /** Set the horizontal alignment for this control
   *   
   * @param alignment the horizontal alignment to use for this control
   */
  public void setHorizontalAlignment(int alignment);
  
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
  
  /** Get the preferred width of this control
   * 
   * This method should calculate the preferred width of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred width in pixels
   */
  public int getPreferredWidth();
  
  /** Get the preferred height of this control
   * 
   * This method should calculate the preferred height of the control in pixels
   * taking into account the current control state and context settings.
   * 
   * @param the preferred height in pixels
   */
  public int getPreferredHeight();
  
  /** Set the listener for a control event
   * 
   * Only a single listener may be set for any event, if you set a new event
   * handler the previous ones will no longer be called.
   * 
   * @param event the event ID to listen for.
   * @param handler the handler to evoke when the event occurs.
   */
  public void setEventHandler(int event, IControlEventHandler handler);
  
  /** Determine if the button is currently touched.
   * 
   * @return true if the button is in a depressed state.
   */
  public boolean isTouched();
  
  }
