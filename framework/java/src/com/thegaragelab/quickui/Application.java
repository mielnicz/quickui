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
  //--- Instance variables
  private Driver     m_driver;     //! The graphics driver instance
  private EventQueue m_eventQueue; //! The incoming event queue
  
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
    // We have no parent and we have the dimensions of the display driver.
    super(null, Driver.getInstance());
    m_driver = Driver.getInstance();
    m_eventQueue = new EventQueue();
    }
  
  //-------------------------------------------------------------------------
  // Implementation of Window
  //-------------------------------------------------------------------------
  
  /** Get the root Window
   * 
   * We override this to ensure that we are the root window for all children.
   * 
   * @return the Window instance that represents the display.
   */
  public Window getRoot() {
    return this;
    }
  
  /** Set the X co-ordinate for this point.
   * 
   * @param nx the new X co-ordinate for this point.
   */
  public void setX(int nx) {
    // This value is immutable for this instance
    }
  
  /** Set the Y co-ordinate for this point.
   * 
   * @param ny the new Y co-ordinate for this point.
   */
  public void setY(int ny) {
    // This value is immutable for this instance
    }
    
  /** Set the width of the rectangle.
   * 
   * @param w the new width of the rectangle.
   */
  public void setWidth(int w) {
    // This value is immutable for this instance
    }
    
  /** Set the height of the rectangle.
   * 
   * @param h the new height of the rectangle.
   */
  public void setHeight(int h) {
    // This value is immutable for this instance
    }

  //-------------------------------------------------------------------------
  // Implementation of Container
  //-------------------------------------------------------------------------
  
  /** Called to do an update of the window.
   * 
   *  In the case of a container we do an update on all child windows as
   *  well as our own.
   */
  void doUpdate() {
    // Dispatch incoming events to windows
    if(m_eventQueue!=null) {
      synchronized(m_eventQueue) {
        for(InputEvent event: m_eventQueue) {
          // Process the input event
          }
        // Clear pending events
        m_eventQueue.clear();
        }
      }
    // Process updates
    super.doUpdate();
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
    m_driver.putPixel(point, color);
    }

  /** Fill a rectangle with a specific color.
   * 
   * @param rect the Rectangle describing the area to fill.
   * @param color the Color to fill the rectangle with.
   */
  @Override
  public void fillRect(IRectangle rect, Color color) {
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
    m_driver.drawLine(start, end, color);
    }
  
  /** Draw a box around a rectangle.
   * 
   * @param rect the Rectangle to draw the box around.
   * @param color the Color to draw the box in.
   */
  @Override
  public void drawBox(IRectangle rect, Color color) {
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
    m_driver.drawImage(point, image, palette, portion);
    }
  
  //-------------------------------------------------------------------------
  // Application specific operations
  //-------------------------------------------------------------------------
  
  /** Run the application
   * 
   *  This method enters an endless loop that runs the application and sends
   *  updates to the physical display.
   */
  public void run() {
    while(true) {
      // Send any window updates
      doRepaint();
      // Process pending events
      m_driver.grabEvents(null);
      // Do any updates
      doUpdate();
      }
    }

  }
