/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 31/05/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.test;

//--- Imports
import java.util.*;
import com.thegaragelab.quickui.*;

/** A simple test application for the framework
 *
 */
public class QuickTest extends Application implements TimedEvent.Listener {
  //--- Instance variables
  private Random m_random = new Random();
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Default constructor
   * 
   */
  public QuickTest() {
    super();
    }
  
  //-------------------------------------------------------------------------
  // Application implementation
  //-------------------------------------------------------------------------
  
  /** Called when a timer event is triggered.
   * 
   * @param timer the timer that caused the event.
   * @param delay the delay (in milliseconds) between this event and when
   *              it should have happened.
   */
  public void onTimer(TimedEvent timer, long delay) {
    System.out.println(delay);
    setDirty(true);
    }

  /** Called to initialise the application
   *
   * This method is called after the window management functions have been
   * called but before the event loop starts. Application implementations
   * should use this to set up their state, create all required windows,
   * etc.
   */
   public void onInitialise() {
     TimedEvent.repeat(250L, this);
     }
   
   /** Repaint the window
    */
   @Override
   public void onPaint() {
     // Create a random rectangle to paint
     Rectangle rect = new Rectangle(
       m_random.nextInt(getWidth()),
       m_random.nextInt(getHeight()),
       m_random.nextInt(getWidth()),
       m_random.nextInt(getHeight())
       );
     // Pick a random color
     Color color = Color.BLACK;
     switch(m_random.nextInt(3)) {
       case 0:
         color = Color.RED;
         break;
       case 1:
         color = Color.GREEN;
         break;
       case 2:
         color = Color.BLUE;
         break;
       }
     // Paint the rectangle
     fillRect(rect, color);
     }

  //-------------------------------------------------------------------------
  // Main program
  //-------------------------------------------------------------------------
  
  /** Program entry point
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    // Simple create and run our application
    Application app = new QuickTest();
    app.run();
    }

  }
