/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.util.*;

/** Java interface to the graphics driver.
 *
 * This class defines the interface to the native methods provided by the
 * QuickGFX driver. It is not visible (or accessible) outside of the package.
 * The class is a singleton, only one driver can be used at any given time.
 */
class Driver implements ISurface {
  //--- Internal constants
  private static final Rectangle DEFAULT_SOURCE = new Rectangle(0, 0, -1, -1);
  
  //--- Instance variables
  private Queue<TouchEvent> m_events; //! The event queue.
  
  //-------------------------------------------------------------------------
  // Singleton management
  //-------------------------------------------------------------------------

  // The singleton instance
  private static Driver m_instance;

  // Load the library.
  static {
    System.loadLibrary("jniquickgfx");
    }
  
  /** Get the current instance
   * 
   * Get the current instance of the driver (or create one if it doesn't
   * exist). This implementation of the method allows you to request a
   * display size - this request will be ignored if the driver has already
   * been created or doesn't support such requests.
   * 
   * @param width the preferred width of the display in pixels
   * @param height the preferred height of the display in pixels
   * 
   * @return the Driver instance or null if an error occurred.
   */
  public static final Driver getInstance(int width, int height) {
    synchronized(Driver.class) {
      // If we already have an instance, return it
      if(m_instance!=null)
        return m_instance;
      // Create a new instance and initialise it
      m_instance = new Driver();
      // TODO: Determine the width and height to request
      if(m_instance.gfxInit(width, height)!=0) {
        // Failed to initialise driver
        m_instance = null;
        }
      // All done
      return m_instance;
      }
    }
  
  /** Get the current instance
   * 
   * Get the current instance of the driver (or create one if it doesn't
   * exist). This implementation of the method will use the drivers default
   * size if it needs to be created.
   * 
   * @return the Driver instance or null if an error occurred.
   */
  public static final Driver getInstance() {
    return getInstance(0, 0);
    }

  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  /** Default constructor
   */
  private Driver() {
    m_events = new LinkedList<TouchEvent>();
    }
  
  //-------------------------------------------------------------------------
  // Native methods
  //-------------------------------------------------------------------------

  /** Initialise the driver
   */
  private native int gfxInit(int width, int height);

  /** Begin a multipart paint operation 
   */
  private native int gfxBeginPaint();

  /** Finish a multipart paint operation 
   */
  private native int gfxEndPaint();

  /** Set the clipping region
   */
  private native int gfxSetClip(int x1, int y1, int x2, int y2);
  
  /** Set the color of a single pixel 
   */
  private native int gfxPutPixel(int x, int y, int color);

  /** Fill a region with the specified color 
   */
  private native int gfxFillRegion(int x1, int y1, int x2, int y2, int color);

  /** Draw a portion of an icon to the display 
   */
  private native int gfxDrawImage(int x, int y, byte[] image, int sx, int sy, int w, int h, byte[] mask, int color, byte[] palette);

  /** Draw a line from one point to another 
   */
  private native int gfxDrawLine(int x1, int y1, int x2, int y2, int color);

  /** Draw a box 
   */
  private native int gfxDrawBox(int x1, int y1, int x2, int y2, int color);

  /** Draw a single character from a font
   * 
   */
  private native int gfxDrawChar(byte[] font, int x, int y, int color, byte ch);
  
  /** Draw a string with the given font
   * 
   */
  private native int gfxDrawString(byte[] font, int x, int y, int color, byte[] text);
  
  /** Process pending events
   */
  private native int gfxCheckEvents();
  
  /** Get the width of the display in pixels
   * 
   * @return the width of the display in pixels.
   */
  private native int gfxGetWidth();
  
  /** Get the height of the display in pixels
   * 
   * @return the height of the display in pixels.
   */
  private native int gfxGetHeight();
  
  /** Get the size of the buffer in bytes
   * 
   * @return the number of bytes required to store the buffer or 0 if the
   *         raw buffer is not available.
   */
  private native int gfxGetBufferSize();
  
  /** Create a copy of the current buffer
   * 
   * @param buffer the location to store the buffer. This must be at least
   *               the number of bytes returned by gfxGetBufferSize().
   */
  private native void gfxGetBuffer(byte[] buffer);
  
  //-------------------------------------------------------------------------
  // Implementation of IDimension
  //-------------------------------------------------------------------------
  
  /** Get the width of the rectangle.
   * 
   * @return the width of the rectangle.
   */
  public int getWidth() {
    return gfxGetWidth();
    }
  
  /** Set the width of the rectangle.
   * 
   * @param w the new width of the rectangle.
   */
  public void setWidth(int w) {
    // TODO: Should probably throw an exception here
    }
  
  /** Get the height of the rectangle.
   * 
   * @return the height of the rectangle.
   */
  public int getHeight() {
    return gfxGetHeight();
    }
  
  /** Set the height of the rectangle.
   * 
   * @param h the new height of the rectangle.
   */
  public void setHeight(int h) {
    // TODO: Should probably throw an exception here
    }
  
  //-------------------------------------------------------------------------
  // Implementation of ISurface
  //
  // Windows will delegate drawing operations to the root window.
  //-------------------------------------------------------------------------

  /** Begin a paint operation.
   * 
   * This method is used to signal the start of a complex paint operation.
   * It is used to help the driver optimise updates to the physical display.
   */
  public void beginPaint() {
    gfxBeginPaint();
    }
  
  /** End a paint operation.
   * 
   * This method is used to signal the end of a complex paint operation.
   */
  public void endPaint() {
    gfxEndPaint();
    }

  /** Set the clipping region for future paint operations
   * 
   * @param x1 the X co-ordinate of the top left corner of the region
   * @param y1 the Y co-ordinate of the top left corner of the region
   * @param x2 the X co-ordinate of the top right corner of the region
   * @param y2 the Y co-ordinate of the top right corner of the region
   */
  public void setClip(IRectangle rect) {
    gfxSetClip(rect.getX(), rect.getY(), rect.getX() + rect.getWidth() - 1, rect.getY() + rect.getHeight() - 1);
    }

  /** Display a single pixel.
   * 
   * @param point the Point at which to display the pixel.
   * @param color the Color to set the pixel to.
   */
  public void putPixel(IPoint point, Color color) {
    gfxPutPixel(point.getX(), point.getY(), color.getNativeFormat());
    }

  /** Fill a rectangle with a specific color.
   * 
   * @param rect the Rectangle describing the area to fill.
   * @param color the Color to fill the rectangle with.
   */
  public void fillRect(IRectangle rect, Color color) {
    gfxFillRegion(rect.getX(), rect.getY(), rect.getX() + rect.getWidth() - 1, rect.getY() + rect.getHeight() - 1, color.getNativeFormat());
    }
  
  /** Draw a line from one point to another 
   * 
   * @param start the starting point for the line.
   * @param end the ending point for the line.
   * @param color the color to draw the line in.
   */
  public void drawLine(IPoint start, IPoint end, Color color) {
    gfxDrawLine(start.getX(), start.getY(), end.getX(), end.getY(), color.getNativeFormat());
    }
  
  /** Draw a box around a rectangle.
   * 
   * @param rect the Rectangle to draw the box around.
   * @param color the Color to draw the box in.
   */
  public void drawBox(IRectangle rect, Color color) {
    gfxDrawBox(rect.getX(), rect.getY(), rect.getX() + rect.getWidth() - 1, rect.getY() + rect.getHeight() - 1, color.getNativeFormat());
    }

  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param source the Rectangle describing the portion of the image to display (optional - may be null).
   * @param mask the Icon to use as a mask (optional - may be null).
   * @param color the color to use for icons (optional - default is black).
   * @param palette the Palette to use to display the image (only required for 4bpp images).
   */
  public void drawImage(IPoint point, Image image, IRectangle source, Icon mask, Color color, Palette palette) {
    // Check parameters here before invoking the call
    if((point==null)||(image==null))
      return;
    // Set up defaults
    if(source==null)
      source = new Rectangle(DEFAULT_SOURCE);
    if(color==null)
      color = Color.BLACK;
    byte[] maskData = null;
    if(mask!=null)
      maskData = mask.getData();
    // 4bpp images require a palette
    byte[] paletteData = null;
    if(image.getBitsPerPixel()==4) {
      if(palette==null)
        return;
      paletteData = palette.getData();
      }
    // Now do the call
    gfxDrawImage(
      point.getX(), 
      point.getY(),
      image.getData(),
      source.getX(),
      source.getY(),
      source.getWidth(),
      source.getHeight(),
      maskData,
      color.getNativeFormat(),
      paletteData
      );
    }
  
  /** Draw a single character using the given font.
   * 
   * @param font the Font to use to render the character.
   * @param point the location to draw the character at.
   * @param color the Color to draw the character with.
   * @param ch the character to draw.
   */
  public void drawChar(Font font, IPoint point, Color color, char ch) {
    // This must be implemented by the framework
    }

  /** Draw a string using the given font.
   * 
   * @param font the Font to use to render the character.
   * @param point the location to draw the character at.
   * @param color the Color to draw the character with.
   * @param string the string to draw.
   */
  public void drawString(Font font, IPoint point, Color color, String string) {
    // This must be implemented by the framework.
    }

  //-------------------------------------------------------------------------
  // Driver specific operations
  //-------------------------------------------------------------------------
  
  /** Process any input events.
   * 
   */
  public void grabEvents() {
    gfxCheckEvents();
    }
  
  /** Push an event to the event queue
   * 
   * This method is usually called by the native driver code but the it could
   * also be used by the framework to simulate events if needed.
   * 
   * @param evType the type of the event
   * @param xpos the first parameter for the event
   * @param ypos the second parameter for the event
   */
  public void pushEvent(int evType, int xpos, int ypos) {
    if(!TouchEvent.isValidEvent(evType))
      return;
    TouchEvent event = new TouchEvent(evType, xpos, ypos);
    m_events.add(event);
    }
  
  /** Get the next event
   * 
   * @return the TouchEvent instance of the next event or null if no events
   *         are pending.
   */
  public TouchEvent nextTouchEvent() {
    return m_events.poll();
    }
  
  }
