/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Java interface to the graphics driver.
 *
 * This class defines the interface to the native methods provided by the
 * QuickGFX driver. It is not visible (or accessible) outside of the package.
 * The class is a singleton, only one driver can be used at any given time.
 */
class Driver implements ISurface {
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
   */
  public static final Driver getInstance() {
    synchronized(Driver.class) {
      // If we already have an instance, return it
      if(m_instance!=null)
        return m_instance;
      // Create a new instance and initialise it
      m_instance = new Driver();
      // TODO: Determine the width and height to request
      if(m_instance.gfxInit(0, 0)!=0) {
        // Failed to initialise driver
        m_instance = null;
        }
      // All done
      return m_instance;
      }
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

  /** Set the color of a single pixel 
   */
  private native int gfxPutPixel(int x, int y, int color);

  /** Fill a region with the specified color 
   */
  private native int gfxFillRegion(int x1, int y1, int x2, int y2, int color);

  /** Draw an icon to the display 
   */
  private native int gfxDrawIcon(int x, int y, int icon, int color);

  /** Draw a portion of an icon to the display 
   */
  private native int gfxDrawIconPortion(int x, int y, int icon, int sx, int sy, int w, int h, int color);

  /** Draw an image to the display 
   */
  private native int gfxDrawImage(int x, int y, int image, int palette);

  /** Draw a portion of an image to the display 
   */
  private native int gfxDrawImagePortion(int x, int y, int image, int sx, int sy, int w, int h, int palette);

  /** Draw a line from one point to another 
   */
  private native int gfxDrawLine(int x1, int y1, int x2, int y2, int color);

  /** Draw a box 
   */
  private native int gfxDrawBox(int x1, int y1, int x2, int y2, int color);

  /** Process pending events
   */
  private native int gfxCheckEvents();
  
  /** Register an asset
   */
  private native int gfxRegisterAsset(int type, byte[] data, int offset, int length);
  
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

  /** Display a single pixel.
   * 
   * @param point the Point at which to display the pixel.
   * @param color the Color to set the pixel to.
   */
  public void putPixel(Point point, Color color) {
    gfxPutPixel(point.x, point.y, color.getNativeFormat());
    }

  /** Fill a rectangle with a specific color.
   * 
   * @param rect the Rectangle describing the area to fill.
   * @param color the Color to fill the rectangle with.
   */
  public void fillRect(Rectangle rect, Color color) {
    gfxFillRegion(rect.x, rect.y, rect.x + rect.width - 1, rect.y + rect.height - 1, color.getNativeFormat());
    }
  
  /** Draw a line from one point to another 
   * 
   * @param start the starting point for the line.
   * @param end the ending point for the line.
   * @param color the color to draw the line in.
   */
  public void drawLine(Point start, Point end, Color color) {
    gfxDrawLine(start.x, start.y, end.x, end.y, color.getNativeFormat());
    }
  
  /** Draw a box around a rectangle.
   * 
   * @param rect the Rectangle to draw the box around.
   * @param color the Color to draw the box in.
   */
  public void drawBox(Rectangle rect, Color color) {
    gfxDrawBox(rect.x, rect.y, rect.x + rect.width - 1, rect.y + rect.height - 1, color.getNativeFormat());
    }

  /** Draw an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   */
  public void drawIcon(Point point, Icon icon, Color color) {
    }

  /** Draw a portion of an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   * @param portion a Rectangle specifying the portion of the icon to draw.
   */
  public void drawIcon(Point point, Icon icon, Color color, Rectangle portion) {
    }
  
  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   */
  public void drawImage(Point point, Image image, Palette palette) {
    }
  
  /** Draw a portion of an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   * @param portion the Rectangle describing the portion of the image to display.
   */
  public void drawImage(Point point, Image image, Palette palette, Rectangle portion) {
    }
  
  //-------------------------------------------------------------------------
  // Driver specific operations
  //-------------------------------------------------------------------------
  
  /** Process any input events.
   * 
   */
  public void grabEvents(EventQueue eventQueue) {
    gfxCheckEvents();
    }
  
  }
