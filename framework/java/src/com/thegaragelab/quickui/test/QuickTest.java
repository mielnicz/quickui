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
  //--- Constants
  private static final int WINDOW_WIDTH  = 25; // Width of the child window
  private static final int WINDOW_HEIGHT = 25; // Height of the child window
  private static final int WINDOW_OFFSET = 10; // Offset for window placement
  
  //--- Instance variables
  private Random m_random = new Random();
  private Window m_winTL; // Window in the top left corner
  private Window m_winTR; // Window in the top right corner
  private Window m_winBL; // Window in the bottom left corner
  private Window m_winBR; // Window in the bottom right corner
  
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
    // Pick a random window
    Window w = m_winTL;
    switch(m_random.nextInt(4)) {
      case 0:
        w = m_winTL;
        break;
      case 1:
        w = m_winTR;
        break;
      case 2:
        w = m_winBR;
        break;
      case 3:
        w = m_winBL;
        break;
      }
    // Set a random color
    switch(m_random.nextInt(3)) {
      case 0:
        w.setBackground(Color.RED);
        break;
      case 1:
        w.setBackground(Color.GREEN);
        break;
      case 2:
        w.setBackground(Color.BLUE);
        break;
      }
    }

  /** Called to initialise the application
   *
   * This method is called after the window management functions have been
   * called but before the event loop starts. Application implementations
   * should use this to set up their state, create all required windows,
   * etc.
   */
   public void onCreate() {
     // Create our child windows
     Rectangle screen = new Rectangle(this);
     m_winTL = new Window(this, new Rectangle(
       WINDOW_OFFSET,
       WINDOW_OFFSET,
       WINDOW_WIDTH,
       WINDOW_HEIGHT
       ));
     m_winTL.setBackground(Color.RED);
     m_winTR = new Window(this, new Rectangle(
       screen.width - WINDOW_OFFSET - WINDOW_WIDTH,
       WINDOW_OFFSET,
       WINDOW_WIDTH,
       WINDOW_HEIGHT
       ));
     m_winTR.setBackground(Color.GREEN);
     m_winBL = new Window(this, new Rectangle(
       WINDOW_OFFSET,
       screen.height - WINDOW_OFFSET - WINDOW_HEIGHT,
       WINDOW_WIDTH,
       WINDOW_HEIGHT
       ));
     m_winBL.setBackground(Color.BLUE);
     m_winBR = new Window(this, new Rectangle(
       screen.width - WINDOW_OFFSET - WINDOW_WIDTH,
       screen.height - WINDOW_OFFSET - WINDOW_HEIGHT,
       WINDOW_WIDTH,
       WINDOW_HEIGHT
       ));
     m_winBR.setBackground(Color.WHITE);
     // Set up our timer
     TimedEvent.repeat(250L, this);
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
