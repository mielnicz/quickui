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
  private String  m_text;    //! Text for this control
  private Padding m_padding; //! Padding for this control
  private int     m_valign;  //! Vertical alignment for the control
  private int     m_halign;  //! Horizontal alignment for the control
  private boolean m_touched; //! Are we currently touched?
  
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
    super(parent, rect, Window.WIN_FLAG_ACCEPT_TOUCH | Window.WIN_FLAG_ERASE_BACKGROUND, 0);
    setText(text);
    m_padding = Padding.DEFAULT;
    m_halign = ALIGN_LEFT;
    m_valign = ALIGN_MIDDLE;
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

  /** Get the padding for this control
   * 
   * @return the padding settings used to display this control
   */
  public Padding getPadding() {
    return m_padding;
    }
  
  /** Set the padding for this control
   * 
   * @param padding the new padding for this control.
   */
  public void setPadding(Padding padding) {
    m_padding = padding;
    }
  
  /** Get the vertical alignment for this control
   * 
   * @return the vertical alignment for this control
   */
  public int getVerticalAlignment() {
    return m_valign;
    }
  
  /** Set the vertical alignment for this control
   *   
   * @param alignment the vertical alignment to use for this control
   */
  public void setVerticalAlignment(int alignment) {
    m_valign = alignment;
    }
  
  /** Get the horizontal alignment for this control
   * 
   * @return the horizontal alignment for this control
   */
  public int getHorizontalAlignment() {
    return m_halign;
    }
  
  /** Set the horizontal alignment for this control
   *   
   * @param alignment the horizontal alignment to use for this control
   */
  public void setHorizontalAlignment(int alignment) {
    m_halign = alignment;
    }
  
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
  
  /**
   * @see com.thegaragelab.quickui.controls.IControl#isTouched()
   */
  public boolean isTouched() {
    return m_touched;
    }

  //-------------------------------------------------------------------------
  // Implementation of IWindow
  //-------------------------------------------------------------------------

  /** Called to erase the background of the control.
   */
  @Override
  public void onEraseBackground() {
    // Pick the right color for the background
    Color color;
    if(isTouched())
      color = Application.getInstance().getSystemColor(Application.SYS_COLOR_CTRL_HIGHLIGHT);
    else
      color = Application.getInstance().getSystemColor(Application.SYS_COLOR_CTRL_BACKGROUND);
    // Now erase the background
    fillRect(new Rectangle(Point.ORIGIN, this), color);
    }

  /**
   * @see com.thegaragelab.quickui.Window#onTouchEvent(int, com.thegaragelab.quickui.IPoint)
   */
  @Override
  public void onTouchEvent(int evType, IPoint where) {
    boolean previous = m_touched;
    // Change the touched state
    if((evType==TouchEvent.GFX_EVENT_TOUCH)||(evType==TouchEvent.GFX_EVENT_DRAG))
      m_touched = true;
    else
      m_touched = false;
    // Should we repaint ?
    if(m_touched!=previous)
      setDirty(true);
    }
  
  }
