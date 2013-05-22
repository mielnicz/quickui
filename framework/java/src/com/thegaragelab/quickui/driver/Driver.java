/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.driver;

/** Java interface to the graphics driver.
 *
 * This class defines the interface to the native methods provided by the
 * QuickGFX driver. It is not visible (or accessible) outside of the package.
 * The class is a singleton, only one driver can be used at any given time.
 */
public class Driver {
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
  
  }
