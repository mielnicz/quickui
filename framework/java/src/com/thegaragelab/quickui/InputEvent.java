/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 24, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents an input event
 */
public class InputEvent implements IPoint {
  //--- Event types (these must track ID's defined in quickgfx.h)
  public static final int GFX_EVENT_KEY_DOWN = 0; //! Key down event
  public static final int GFX_EVENT_KEY_UP   = 1; //! Key up event
  public static final int GFX_EVENT_TOUCH    = 2; //! Touch event
  public static final int GFX_EVENT_DRAG     = 3; //! A drag event (touched and moving)
  public static final int GFX_EVENT_RELEASE  = 4; //! Release event
  
  //--- Highest numbered event type
  private static final int GFX_EVENT_TYPE_MAX = GFX_EVENT_RELEASE;
  
  //--- Instance variables
  int  m_type;   //! Type of the event
  int  m_param1; //! First parameter for the event
  int  m_param2; //! Second parameter for the event
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   *
   *  Creates a new event instance with the data provided by the driver.
   *  
   *  @param evType the type of the event.
   *  @param param1 the first parameter of the event.
   *  @param param2 the second parameter of the event.
   */
  InputEvent(int evType, int param1, int param2) {
    m_type = evType;
    m_param1 = param1;
    m_param2 = param2;
    }

  //-------------------------------------------------------------------------
  // Static helpers
  //-------------------------------------------------------------------------
  
  /** Determine if the event type is valid
   * 
   * @param evType the event type to check
   * 
   * @return true if the event type is valid, false if not
   */
  static boolean isValidEvent(int evType) {
    if((evType>=0)&&(evType<=GFX_EVENT_TYPE_MAX))
      return true;
    // Nope
    return false;
    }

  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------
  
  /** Get the event type
   * 
   * @return the type of the event
   */
  public int getEventType() {
    return m_type;
    }
  
  /** Get the key that was pressed
   * 
   * @return the key number that was pressed.
   */
  public int getKey() {
    switch(m_type) {
      case GFX_EVENT_KEY_DOWN:
      case GFX_EVENT_KEY_UP:
        return m_param1;
      }
    return 0;
    }
  
  //-------------------------------------------------------------------------
  // InputEvent specific operations
  //-------------------------------------------------------------------------
  
  /** Determine if this is a key event
   * 
   * @return true if this event is the result of a key press.
   */
  public boolean isKeyEvent() {
    return (m_type==GFX_EVENT_KEY_UP)||(m_type==GFX_EVENT_KEY_DOWN);
    }
  
  /** Determine if this is a touch event
   * 
   * @return true if this event is the result of a touch operation
   */
  public boolean isTouchEvent() {
    return (m_type==GFX_EVENT_TOUCH)||(m_type==GFX_EVENT_DRAG)||(m_type==GFX_EVENT_RELEASE);
    }
  
  //-------------------------------------------------------------------------
  // Implementation of IPoint
  //-------------------------------------------------------------------------
  
  /** Get the X co-ordinate for this point.
   * 
   * @return the X co-ordinate for this point.
   */
  public int getX() {
    switch(m_type) {
      case GFX_EVENT_TOUCH:
      case GFX_EVENT_DRAG:
      case GFX_EVENT_RELEASE:
        return m_param1;
      }
    return 0;
    }
  
  /** Set the X co-ordinate for this point.
   * 
   * @param nx the new X co-ordinate for this point.
   */
  public void setX(int nx) {
    // Do nothing
    }
  
  /** Get the Y co-ordinate for this point.
   * 
   * @return the Y co-ordinate for this point.
   */
  public int getY() {
    switch(m_type) {
      case GFX_EVENT_TOUCH:
      case GFX_EVENT_DRAG:
      case GFX_EVENT_RELEASE:
        return m_param1;
      }
    return 0;
    }
  
  /** Set the Y co-ordinate for this point.
   * 
   * @param ny the new Y co-ordinate for this point.
   */
  public void setY(int ny) {
    // Do nothing
    }

  /** Translate the point so the given point is the origin
   * 
   * @point origin the new origin for the co-ordinates
   * 
   * @return IPoint the translated instance.
   */
  public IPoint translate(IPoint origin) {
    return new Point(getX() + origin.getX(), getY() + origin.getY());
    }
  
  }
