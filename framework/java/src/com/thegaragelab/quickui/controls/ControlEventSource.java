/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 11/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

//--- Imports
import java.lang.ref.*;

/** This class is used combine a control and an event ID into a single type
 *
 */
class ControlEventSource {
  //--- Instance variables
  private WeakReference<IControl> m_source; //! The source of the event
  private int                     m_event;  //! The event ID for the event
  private int                     m_hash;   //! The hashcode for this instance
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  /** Constructor with an event source and an event ID
   * 
   * @param source the source for the event.
   * @param event the event ID to match with the source. 
   */
  public ControlEventSource(IControl source, int event) {
    m_source = new WeakReference<IControl>(source);
    m_event = event;
    m_hash = (source.hashCode() << 4) + event;
    }
  
  //-------------------------------------------------------------------------
  // Override methods to support hashing
  //-------------------------------------------------------------------------

  /** Get the hash code for this instance
   * 
   * @return an integer hashcode for this object.
   */
  public int hashCode() {
    return m_hash;
    }
  
  /** Determine if this object is equal to another.
   * 
   * @param obj the object to compare against.
   * 
   * @return true if obj is 'the same' as this object. This may be a different
   *              instance with the same property values.
   */
  public boolean equals(Object obj) {
    // We need an object of the same class
    if((obj==null)||(obj.getClass()!=getClass()))
      return false;
    // Cast it and do the comparison
    ControlEventSource other = (ControlEventSource)obj;
    return (m_source.get()==other.m_source.get()) && (m_event==other.m_event);
    }
  
  }
