/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 31/05/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.test;

//--- Imports
import com.thegaragelab.quickui.*;
import com.thegaragelab.quickui.controls.*;
import com.thegaragelab.quickui.dialogs.*;

/** A simple test application for the framework
 *
 */
public class QuickTest extends Application {
  //--- Display preferences
  private static final int SCREEN_WIDTH  = 480; // Preferred width of the window
  private static final int SCREEN_HEIGHT = 272; // Preferred height of the window
  
  //--- Instance variables
  private Point   m_where; // Where to draw text
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Default constructor
   * 
   */
  public QuickTest() {
    super(SCREEN_WIDTH, SCREEN_HEIGHT);
    }
  
  //-------------------------------------------------------------------------
  // Application implementation
  //-------------------------------------------------------------------------
  
  /** Called to initialise the application
   *
   * This method is called after the window management functions have been
   * called but before the event loop starts. Application implementations
   * should use this to set up their state, create all required windows,
   * etc.
   */
   public void onInitialise() {
     // Create some controls
     Rectangle screen = new Rectangle(this);
     Checkbox cbTest = new Checkbox(this, new Rectangle(
       screen.width / 4,
       screen.height / 4,
       128,
       32),
       "Checkbox"
       );
     cbTest.setPreferredSize();
     Button btnTest = new Button(this, new Rectangle(
       screen.width / 2,
       screen.height / 2,
       128,
       32),
       "Button"
       );
     btnTest.setPreferredSize();
     btnTest.setEventHandler(
       Button.EVENT_TOUCHED,
       new IControlEventHandler() {
         public void onEvent(IWindow source, int event, Object params) {
           Application.getInstance().showDialog(new DialogBusy("Loading ..."));
           }
         }
       );
     Label lblTest = new Label(this, new Rectangle(
       screen.width / 4,
       screen.height / 2,
       128,
       32),
       "Label"
       );
     lblTest.setPreferredSize();
     SystemIconButton btnSystem = new SystemIconButton(this, new Rectangle(
       screen.width / 8,
       screen.height / 4,
       32,
       32),
       Application.ICON_HOME
       );
     btnSystem.setPreferredSize();  
     }
   
   /**
    * @see com.thegaragelab.quickui.Window#onPaint()
    */
   @Override
   public void onPaint() {
     super.onPaint();
     if(m_where==null)
       return;
     drawString(
       getFont(),
       m_where,
       Color.RED,
       "This is some sample text!!!"
       );
     }

   /** Called when an input event is targeted to this window
    * 
    * An input event represents a key press or activity on the touch screen.
    * In general, unless you are implementing a control, you don't need to
    * do anything with these events.
    * 
    * @param evType the type of the event
    * @param where the location of the event (in window co-ordinates)
    */
   @Override
   public void onTouchEvent(int evType, IPoint where) {
     if(evType!=TouchEvent.GFX_EVENT_RELEASE)
       m_where = new Point(where);
     else
       m_where = null;
     setDirty(true);
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
    app.setEraseBackground(true);
//    app.setBackground(Color.BLACK);
    app.run();
    }

  }
