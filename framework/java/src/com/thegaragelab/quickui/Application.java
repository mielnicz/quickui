/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** A single application.
 * 
 *  The Application is a special type of container window that is the top of
 *  the window hierarchy. Individual applications will override methods of
 *  this class to implement application specific functionality.
 */
public class Application extends Container {
  //--- Constants
  private static final String SYSTEM_RESOURCE = "system";
  
  //--- System icons
  public static final  int SYSTEM_ICON_SIZE = 16; //! Size of system icons
  public static final  int ICON_HOME        = 0;  //! The home icon
  public static final  int ICON_UP          = 1;  //! Icon pointing up
  public static final  int ICON_UP_RIGHT    = 2;  //! Icon pointing up and right
  public static final  int ICON_RIGHT       = 3;  //! Icon pointing right
  public static final  int ICON_DOWN_RIGHT  = 4;  //! Icon pointing down and right
  public static final  int ICON_DOWN        = 5;  //! Icon pointing down
  public static final  int ICON_DOWN_LEFT   = 6;  //! Icon pointing down and left
  public static final  int ICON_LEFT        = 7;  //! Icon pointing left
  public static final  int ICON_UP_LEFT     = 8;  //! Icon pointing up and left
  public static final  int ICON_ALERT       = 9;  //! Alert icon
  public static final  int ICON_ERROR       = 10; //! Error icon
  public static final  int ICON_INFO        = 11; //! Information icon
  public static final  int ICON_OK          = 12; //! Showing OK
  public static final  int ICON_CANCEL      = 13; //! Showing Cancel or Bad
  public static final  int ICON_TAG         = 14; //! Tag (partial save)
  public static final  int ICON_SAVE        = 15; //! Save (save everything)
  private static final int SYSTEM_ICON_MAX = ICON_SAVE; //! Maximum ID for system icons
  
  //--- System colors
  //
  // These constants define offsets into the system palette that are used for
  // various elements of the UI. They are not enforced but the framework will
  // use them by default. By changing or overriding the system palette you can
  // change the look of the whole UI very simply.
  public static final int SYS_COLOR_BACKGROUND      = 0; //! Default background
  public static final int SYS_COLOR_FOREGROUND      = 1; //! Default foreground
  public static final int SYS_COLOR_WIN_BACKGROUND  = 2; //! Window background
  public static final int SYS_COLOR_WIN_FOREGROUND  = 3; //! Window foreground
  public static final int SYS_COLOR_CTRL_BACKGROUND = 4; //! Control background
  public static final int SYS_COLOR_CTRL_FOREGROUND = 5; //! Control foreground
  public static final int SYS_COLOR_CTRL_HIGHLIGHT  = 6; //! Control highlights
  public static final int SYS_COLOR_CTRL_BORDER     = 7; //! Control borders
  public static final int SYS_COLOR_DLG_BACKGROUND  = 8; //! Dialog background
  public static final int SYS_COLOR_DLG_FOREGROUND  = 9; //! Dialog foreground
  
  //--- Class variables
  private static Application m_instance;
  
  //--- Instance variables
  private Driver     m_driver;     //! The graphics driver instance
  private Point      m_offset;     //! The offset for painting operations
  private Palette    m_palette;    //! The system palette
  private Icon       m_icons;      //! The system icons
  private IWindow    m_target;     //! The window currently accepting touch events.
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Default constructor
   * 
   * The default constructor uses the dimensions of the display driver as
   * it's size and origin (0, 0) as it's top left co-ordinate. We have
   * no parent and set ourselves as the root window.
   */
  public Application() {
    // We have no parent and we use the dimensions of the display driver.
    super(new Rectangle(Point.ORIGIN, Driver.getInstance()));
    m_instance = this;
    }
  
  /** Constructor with a preferred size
   * 
   * The default constructor uses the dimensions of the display driver as
   * it's size and origin (0, 0) as it's top left co-ordinate. We have
   * no parent and set ourselves as the root window.
   * 
   * This version of the constructor allows you to specify a preferred size
   * for the display (which may be ignored).
   * 
   * @param width the preferred width of the display in pixels.
   * @param height the preferred height of the display in pixels.
   */
  public Application(int width, int height) {
    // We have no parent and we use the dimensions of the display driver.
    super(new Rectangle(Point.ORIGIN, Driver.getInstance(width, height)));
    m_instance = this;
    }
  
  /** Initialise the state
   * 
   *  This method is used to initialise the state for the type of window
   *  being created. Child classes may override this to set their own
   *  initial state but must call the parent implementation.
   */
  @Override
  void initialiseState() {
    super.initialiseState();
    // Initialise our own state
    m_driver = Driver.getInstance();
    m_offset = Point.ORIGIN;
    // Load our assets
    m_palette = Asset.loadPalette(SYSTEM_RESOURCE);
    m_icons = Asset.loadIcon(SYSTEM_RESOURCE);
    // Set the base context
    setFont(Asset.loadFont(SYSTEM_RESOURCE));
    setHorizontalAlignment(IContext.LEFT);
    setVerticalAlignment(IContext.MIDDLE);
    setPadding(Padding.NONE);
    // TODO: Colors should come from Palette indices
    setForeground(Color.WHITE);
    setBackground(Color.BLACK);
    }
  
  //-------------------------------------------------------------------------
  // Singleton management
  //-------------------------------------------------------------------------
  
  /** Get the current application instance
   * 
   * @return the running application
   */
  public static final Application getInstance() {
    return m_instance;
    }
  
  //-------------------------------------------------------------------------
  // Implementation of Window
  //-------------------------------------------------------------------------
  
  /** Set the X co-ordinate for this point.
   * 
   * @param nx the new X co-ordinate for this point.
   */
  @Override
  public final void setX(int nx) {
    // This value is immutable for this instance
    }
  
  /** Set the Y co-ordinate for this point.
   * 
   * @param ny the new Y co-ordinate for this point.
   */
  @Override
  public final void setY(int ny) {
    // This value is immutable for this instance
    }
    
  /** Set the width of the rectangle.
   * 
   * @param w the new width of the rectangle.
   */
  @Override
  public final void setWidth(int w) {
    // This value is immutable for this instance
    }
    
  /** Set the height of the rectangle.
   * 
   * @param h the new height of the rectangle.
   */
  @Override
  public final void setHeight(int h) {
    // This value is immutable for this instance
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
    // Do nothing in this instance
    }
  
  /** Allow the window to receive touch events
   * 
   * @param accept true if this Window should accept touch events.
   */
  @Override
  public final void setAcceptTouch(boolean accept) {
    // Do nothing, we always accept touch events
    }
  
  /** Get the window that accepts touch events for us
   * 
   * If this window will accept and process touch events then we return
   * ourselves, otherwise we delegate up the list of parent windows until
   * one accepts the role (the Application instance will always accept
   * touch events).
   * 
   * @return the Window instance that will process touch events on our
   *         behalf.
   */
  @Override
  public final IWindow getAcceptTouch() {
    // We will always handle touch events on behalf of any window
    return this;
    }
  
  //-------------------------------------------------------------------------
  // Implementation of IContext
  //-------------------------------------------------------------------------

  /** Set the font for this context
   * 
   * @param font the font to use for this context.
   */
  @Override
  public void setFont(Font font) {
    if(font!=null)
      super.setFont(font);
    }
  
  /** Set the foreground color for this context
   * 
   * @param color the color to use as the foreground.
   */
  @Override
  public void setForeground(Color color) {
    if(color!=null)
      super.setForeground(color);
    }
  
  /** Set the background color for this context
   * 
   * @param color the color to use as the background.
   */
  @Override
  public void setBackground(Color color) {
    if(color!=null)
      super.setBackground(color);
    }
  
  /** Set the padding for drawing.
   * 
   * @param padding the padding to use when drawing.
   */
  @Override
  public void setPadding(Padding padding) {
    if(padding!=null)
      super.setPadding(padding);
    }
  
  /** Set the vertical alignment for elements
   * 
   * @param align the vertical alignment to use.
   */
  @Override
  public void setVerticalAlignment(int align) {
    if(align!=IContext.PARENT)
      super.setVerticalAlignment(align);
    }
  
  /** Set the horizontal alignment for elements
   * 
   * @param align the horizontal alignment to use.
   */
  @Override
  public void setHorizontalAlignment(int align) {
    if(align!=IContext.PARENT)
      super.setHorizontalAlignment(align);
    }
  
  //-------------------------------------------------------------------------
  // Implementation of Container
  //-------------------------------------------------------------------------
  
  /** Called to do a repaint of the window
   * 
   * This is an internal helper to manage the painting process. Child
   * windows should only override the onPaint() method and do custom
   * painting there - it will be called as needed by the internal logic
   * of the framework.
   * 
   * @param force if true force a repaint regardless of the 'dirty' state.
   */
  @Override
  void doRepaint(boolean force) {
    // Set the offset to origin
    setOffset(Point.ORIGIN);
    // And start the repaint sequence
    super.doRepaint(force);
    }

  //-------------------------------------------------------------------------
  // Internal event and painting helpers
  //-------------------------------------------------------------------------

  /** Set the offset for this window
   * 
   * @param offset the offset to use for future painting operations
   */
  @Override
  void setOffset(IPoint offset) {
    if(offset==null)
      m_offset = Point.ORIGIN;
    else
      m_offset = new Point(offset);
    }
  
  /** Process an TouchEvent
   * 
   * @param event the TouchEvent to handle
   */
  void doTouchEvent(TouchEvent event) {
    // A touch event can change the focus, handle that situation.
    if(event.getEventType()==TouchEvent.GFX_EVENT_TOUCH) {
      IWindow window = getWindowByPoint(event);
      if(window!=null)
        m_target = window.getAcceptTouch();
      }
    // Now dispatch the touch event
    if(m_target!=null)
      m_target.onTouchEvent(event.getEventType(), Point.offset(m_target, event));
    else
      onTouchEvent(event.getEventType(), event);
    }
  
  //-------------------------------------------------------------------------
  // Implementation of ISurface
  //-------------------------------------------------------------------------
  
  /** Begin a paint operation.
   * 
   * This method is used to signal the start of a complex paint operation.
   * It is used to help the driver optimise updates to the physical display.
   */
  @Override
  public void beginPaint() {
    m_driver.beginPaint();
    }
  
  /** End a paint operation.
   * 
   * This method is used to signal the end of a complex paint operation.
   */
  @Override
  public void endPaint() {
    m_driver.endPaint();
    }

  /** Set the clipping region for future operations
   * 
   * @param rect the Rectangle describing the clipping region.
   */
  @Override
  public void setClip(IRectangle rect) {
    m_driver.setClip(rect);
    }

  /** Display a single pixel.
   * 
   * @param point the Point at which to display the pixel.
   * @param color the Color to set the pixel to.
   */
  @Override
  public void putPixel(IPoint point, Color color) {
    point = point.translate(m_offset);
    m_driver.putPixel(point, color);
    }

  /** Fill a rectangle with a specific color.
   * 
   * @param rect the Rectangle describing the area to fill.
   * @param color the Color to fill the rectangle with.
   */
  @Override
  public void fillRect(IRectangle rect, Color color) {
    rect = (IRectangle)rect.translate(m_offset);
    m_driver.fillRect(rect, color);
    }
  
  /** Draw a line from one point to another 
   * 
   * @param start the starting point for the line.
   * @param end the ending point for the line.
   * @param color the color to draw the line in.
   */
  @Override
  public void drawLine(IPoint start, IPoint end, Color color) {
    start = start.translate(m_offset);
    end = end.translate(m_offset);
    m_driver.drawLine(start, end, color);
    }
  
  /** Draw a box around a rectangle.
   * 
   * @param rect the Rectangle to draw the box around.
   * @param color the Color to draw the box in.
   */
  @Override
  public void drawBox(IRectangle rect, Color color) {
    rect = (IRectangle)rect.translate(m_offset);
    m_driver.drawBox(rect, color);
    }

  /** Draw an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   */
  @Override
  public void drawIcon(IPoint point, Icon icon, Color color) {
    point = point.translate(m_offset);
    m_driver.drawIcon(point, icon, color);
    }

  /** Draw a portion of an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   * @param portion a Rectangle specifying the portion of the icon to draw.
   */
  @Override
  public void drawIcon(IPoint point, Icon icon, Color color, IRectangle portion) {
    point = point.translate(m_offset);
    m_driver.drawIcon(point, icon, color, portion);
    }
  
  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   */
  @Override
  public void drawImage(IPoint point, Image image, Palette palette) {
    point = point.translate(m_offset);
    m_driver.drawImage(point, image, palette);
    }
  
  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   * @param portion the Rectangle describing the portion of the image to display.
   */
  @Override
  public void drawImage(IPoint point, Image image, Palette palette, IRectangle portion) {
    point = point.translate(m_offset);
    m_driver.drawImage(point, image, palette, portion);
    }
  
  //-------------------------------------------------------------------------
  // Application specific operations
  //-------------------------------------------------------------------------
  
  /** Get the system icons
   *
   * @return the Icon asset containing the system icons
   */
  public Icon getIcons() {
    return m_icons;
    }
  
  /** Draw a particular system icon at the given location
   * 
   * @param position where to draw the icon
   * @param icon the name of the icon
   */
  public void drawSystemIcon(IPoint position, int icon, Color color) {
    // Check boundaries
    if((icon<0)||(icon>SYSTEM_ICON_MAX)||(m_icons==null))
      return;
    // Show the icon
    drawIcon(position, m_icons, color, new Rectangle(icon * SYSTEM_ICON_SIZE, 0, SYSTEM_ICON_SIZE, SYSTEM_ICON_SIZE));
    }
  
  /** Get the current system palette
   * 
   * @return the palette defined as the system palette.
   */
  public Palette getPalette() {
    return m_palette;
    }
  
  /** Get a system color
   *
   * @param index the index of the color to fetch.
   * 
   * @return the color from the system palette that matches the given index.
   */
  public Color getSystemColor(int index) {
    return m_palette.getColor(index % Palette.PALETTE_SIZE);
    }
  
  /** Called to initialise the application
   *
   * This method is called after the window management functions have been
   * called but before the event loop starts. Application implementations
   * should use this to set up their state, create all required windows,
   * etc.
   */
  public void onInitialise() {
    // Nothing to do in this implementation
    }
  
  /** Run the application
   * 
   *  This method enters an endless loop that runs the application and sends
   *  updates to the physical display.
   */
  public final void run() {
    onInitialise();
    while(true) {
      // Process pending events
      m_driver.grabEvents();
      TouchEvent event = m_driver.nextTouchEvent();
      while(event!=null) {
        doTouchEvent(event);
        event = m_driver.nextTouchEvent();
        }
      // Process timers
      SimpleTimer.update();
      // Do any updates
      doUpdate();
      // Repaint what is needed.
      doRepaint(false);
      }
    }

  }
