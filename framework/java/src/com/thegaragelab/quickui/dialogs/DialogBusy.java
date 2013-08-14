/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 23/07/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.dialogs;

//--- Imports
import com.thegaragelab.quickui.*;
import com.thegaragelab.quickui.controls.*;

/** Represents a 'busy' dialog
 * 
 * This shows a modal dialog with an animated 'busy' signal and some text. The
 * dialog is generally used to indicate some background process is in operation.
 * 
 * There is no user interaction.
 */
public class DialogBusy extends Dialog implements SimpleTimer.Listener {
  //--- Constants
  private static final long   TEST_INTERVAL   = 250;
  private static final String ANIMATION_ASSET = "loader";
  private static final int    IMAGE_SIZE      = 24;
  private static final int    IMAGE_WIDTH     = 4;
  private static final int    IMAGE_FRAMES    = 12;

  //--- Instance variables
  private SimpleTimer m_timer;    //! The timer used to control testing
  private Image       m_image;    //! The image to animate
  private Palette     m_palette;  //! The palette to use for colors
  private int         m_frame;    //! The current frame of the icon
  private Rectangle   m_iconRect; //! Where to place the icon
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   *
   */
  public DialogBusy(String text) {
    super();
    // Now add the text
    if(m_image!=null)
      new Label(this, new Rectangle(m_image.getHeight() + 4, 2, getWidth() - m_image.getHeight() - 4, getHeight() - 4), text);
    else
      new Label(this, new Rectangle(2, 2, getWidth() - 4, getHeight() - 4), text);
    }

  /** Constructor
   * 
   */
  public DialogBusy(Dimension size, String text) {
    super(size);
    }

  //-------------------------------------------------------------------------
  // Implementation of IWindow
  //-------------------------------------------------------------------------
  
  /** Set up the Window
   * 
   * @see com.thegaragelab.quickui.Window#onCreate()
   */
  @Override
  public void onCreate() {
    super.onCreate();
    // Load the icon to use for animation
    m_image = Asset.loadImage(ANIMATION_ASSET);
    m_palette = Asset.loadPalette("theme");
    m_iconRect = new Rectangle(1, 1, IMAGE_SIZE, IMAGE_SIZE);
    }

  /** Paint the window
   * 
   * @see com.thegaragelab.quickui.Window#onPaint()
   */
  @Override
  public void onPaint() {
    super.onPaint();
    // Do we have an icon to paint ?
    if(m_image==null)
      return;
    // Clear the icon background
    fillRect(
      m_iconRect,
      Application.getInstance().getSystemColor(Application.SYS_COLOR_DLG_BACKGROUND)
      );
    // Calculate the new frame of the icon and draw it
    m_frame = m_frame % IMAGE_FRAMES;
    Rectangle slice = new Rectangle(
      IMAGE_SIZE * (m_frame % IMAGE_WIDTH),
      IMAGE_SIZE * (m_frame / IMAGE_WIDTH),
      IMAGE_SIZE,
      IMAGE_SIZE
      );
    this.drawImage(
      m_iconRect,
      m_image,
      m_palette,
      slice
      );
    }

  /** Use visibility to start/stop background processes and animations.
   * 
   * @see com.thegaragelab.quickui.Window#onVisible(boolean)
   */
  @Override
  public void onVisible(boolean visible) {
    super.onVisible(visible);
    // Handle timers and task management
    if(visible) {
      startActivity();
      m_timer = SimpleTimer.repeat(TEST_INTERVAL, this);
      }
    else {
      m_timer.stop();
      }
    }

  //-------------------------------------------------------------------------
  // Implementation of SimpleTimer.Listener
  //-------------------------------------------------------------------------
  
  public void onTimer(SimpleTimer simpleTimer, long delay) {
    if(isComplete())
      setVisible(false);
    else {
      m_frame++;
      setDirty(true);
      }
    }

  //-------------------------------------------------------------------------
  // DialogBusy specific methods
  //-------------------------------------------------------------------------
  
  /** Called to start the background activity
   * 
   * Child classes can override this to start a background process. If they do
   * they should also implement the 'isComplete()' method to indicate that the
   * process is complete and the dialog can dissappear.
   */
  protected void startActivity() {
    // Nothing to do in this implementation
    }
  
  /** Indicates that the background activity has completed.
   * 
   * If this method returns true the dialog will hide itself and normal operations
   * will continue.
   * 
   * @return true if the operation is completed, false if it is still ongoing.
   */
  protected boolean isComplete() {
    return false; // Continue until the dialog is hidden.
    }
  
  }
