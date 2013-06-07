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
public class TouchEvent implements IPoint {
  //--- Event types (these must track ID's defined in quickgfx.h)
  public static final int GFX_EVENT_TOUCH    = 0; //! Touch event
  public static final int GFX_EVENT_DRAG     = 1; //! A drag event (touched and moving)
  public static final int GFX_EVENT_RELEASE  = 2; //! Release event
  
  //--- Highest numbered event type
  private static final int GFX_EVENT_TYPE_MAX = GFX_EVENT_RELEASE;
  
  //--- Instance variables
  int  m_type; //! Type of the event
  int  m_xpos; //! First parameter for the event
  int  m_ypos; //! Second parameter for the event
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   *
   *  Creates a new event instance with the data provided by the driver.
   *  
   *  @param evType the type of the event.
   *  @param xpos the first parameter of the event.
   *  @param ypos the second parameter of the event.
   */
  TouchEvent(int evType, int xpos, int ypos) {
    m_type = evType;
    m_xpos = xpos;
    m_ypos = ypos;
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
  
  //-------------------------------------------------------------------------
  // Implementation of IPoint
  //-------------------------------------------------------------------------
  
  /** Get the X co-ordinate for this point.
   * 
   * @return the X co-ordinate for this point.
   */
  public int getX() {
    return m_xpos;
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
    return m_ypos;
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
