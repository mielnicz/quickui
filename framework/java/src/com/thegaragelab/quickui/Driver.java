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

  /** Set the clipping region
   */
  private native int gfxSetClip(int x1, int y1, int x2, int y2);
  
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

  /** Draw an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   */
  public void drawIcon(IPoint point, Icon icon, Color color) {
    }

  /** Draw a portion of an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   * @param portion a Rectangle specifying the portion of the icon to draw.
   */
  public void drawIcon(IPoint point, Icon icon, Color color, IRectangle portion) {
    }
  
  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   */
  public void drawImage(IPoint point, Image image, Palette palette) {
    }
  
  /** Draw a portion of an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   * @param portion the Rectangle describing the portion of the image to display.
   */
  public void drawImage(IPoint point, Image image, Palette palette, IRectangle portion) {
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
