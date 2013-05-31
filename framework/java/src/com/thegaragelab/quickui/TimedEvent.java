/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 01/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.util.*;

/** Represents a timer that can be used to trigger events at a later date
 *
 */
public class TimedEvent {
  /** The listener to use for timer events
   * 
   */
  public interface Listener {
    /** Called when a timer event is triggered.
     * 
     * @param timer the timer that caused the event.
     * @param delay the delay (in milliseconds) between this event and when
     *              it should have happened.
     */
    public void onTimer(TimedEvent timer, long delay);
    
    };

  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  //--- Class variables
  private static Set<TimedEvent> m_timers = new HashSet<TimedEvent>();
  
  //--- Instance variables
  private boolean  m_repeat;   //! True if this timer repeats
  private long     m_last;     //! The last time we triggered an event
  private long     m_wait;     //! How long to wait between events
  private Listener m_listener; //! The listener to receive events
  
  /** Constructor
   * 
   * @param wait the period to wait between events.
   * @param listener the listener to receive events.
   * @param repeat true if we should repeat events rather than just send one.
   */
  private TimedEvent(long wait, Listener listener, boolean repeat) {
    m_last = System.currentTimeMillis();
    m_wait = wait;
    m_repeat = repeat;
    m_listener = listener;
    }
  
  //-------------------------------------------------------------------------
  // Public creation
  //-------------------------------------------------------------------------

  /** Create a one shot timer
   * 
   * This type of timer triggers once only a certain period of time after it
   * as been created.
   * 
   * @param wait how long to wait (in milliseconds) before triggering an event
   * @param listener the listener for the event.
   */
  public static final synchronized TimedEvent once(long wait, Listener listener) {
    TimedEvent timer = new TimedEvent(wait, listener, false);
    m_timers.add(timer);
    return timer;
    }
  
  /** Create a repeating timer
   * 
   * This type of timer triggers at regular intervals.
   * 
   * @param wait how long to wait (in milliseconds) between events
   * @param listener the listener for the event.
   */
  public static final TimedEvent repeat(long wait, Listener listener) {
    TimedEvent timer = new TimedEvent(wait, listener, true);
    m_timers.add(timer);
    return timer;
    }
  
  //-------------------------------------------------------------------------
  // TimedEvent management
  //-------------------------------------------------------------------------

  /** Remove a timer
   * 
   */
  private static final synchronized void remove(TimedEvent timer) {
    m_timers.remove(timer);
    }
  
  /** Update all timers
   * 
   */
  static final synchronized void update() {
    // Create a copy of the set of timers as an array
    TimedEvent[] timers = new TimedEvent[m_timers.size()];
    timers = m_timers.toArray(timers);
    // Get the current time and use that to trigger timers
    long now = System.currentTimeMillis();
    for(int i=0; i<timers.length; i++)
      timers[i].trigger(now);
    }
  
  /** Trigger an event
   * 
   * This method is used to determine if the timer should trigger an event,
   * and if so, dispatch that event to the listener.
   * 
   * @param now the current time. Used to determine if the timer should
   *            trigger or not.
   */
  private void trigger(long now) {
    if((now-m_last)>m_wait) {
      // Trigger the timer
      if(m_listener!=null)
        m_listener.onTimer(this, now - m_last - m_wait);
      // Reset state
      m_last = now;
      if(!m_repeat)
        TimedEvent.remove(this);
      }
    }
  
  //-------------------------------------------------------------------------
  // Public operations
  //-------------------------------------------------------------------------

  /** Stop a timer
   *
   * This method is used to stop a repeating timer. After calling this method
   * it should no longer trigger any more events.
   */
  public void stop() {
    TimedEvent.remove(this);
    }
  
  }
