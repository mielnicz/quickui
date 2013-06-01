/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

import java.util.*;

//--- Imports
import com.thegaragelab.quickui.utils.*;

/** Represents a simple window.
 * 
 * This is the base class for all visual elements in the Framework. It
 * provides the basic functionality required for all visual elements.
 */
public class Window implements IRectangle, ISurface, IFlags {
  //--- Constants
  private static final int WIN_FLAG_DIRTY   = 0x0001;
  private static final int WIN_FLAG_VISIBLE = WIN_FLAG_DIRTY << 1;
  
  //--- Instance variables
  private Container m_parent;     //! The parent Window
  private Window    m_root;       //! The root Window
  private Rectangle m_rectangle;  //! Position and size of the window
  private Flags     m_flags;      //! Current flags
  private Color     m_background; //! The background color for the window
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  /** Constructor with a Rectangle describing its area.
   * 
   * @param rect the Rectangle describing the location and size of the window.
   */
  Window(Rectangle rect) {
    m_rectangle = new Rectangle(rect);
    m_root = this;
    m_flags = new Flags();
    initialiseState();
    // Call the creation method
    onCreate();
    }

  /** Constructor with a parent Window and a Rectangle describing it position.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   */
  public Window(Container parent, Rectangle rect) {
    m_parent = parent;
    m_root = m_parent.getRoot();
    m_flags = new Flags();
    m_rectangle = new Rectangle(rect);
    initialiseState();
    // Call the creation method
    onCreate();
    }

  /** Initialise the state
   * 
   *  This method is used to initialise the state for the type of window
   *  being created. Child classes may override this to set their own
   *  initial state but must call the parent implementation.
   */
  void initialiseState() {
    // Always start as visible and dirty
    setDirty(true);
    setVisible(true);
    // Add ourselves to the parent (if we have one)
    if(m_parent!=null) {
      // Translate our co-ordinates from relative to absolute
      m_rectangle = (Rectangle)m_rectangle.translate(m_parent);
      // Add ourselves to the parent
      m_parent.add(this);
      }
    }
  
  //-------------------------------------------------------------------------
  // Window specific operations
  //-------------------------------------------------------------------------

  /** Get the parent of this Window
   * 
   * @return the Window instance that represents the parent of this window.
   */
  public Window getParent() {
    return m_parent;
    }
  
  /** Get the root Window
   * 
   * @return the Window instance that represents the display.
   */
  public Window getRoot() {
    return m_root;
    }

  /** Set the background color for this window
   * 
   * @param color the background color to use. If null the window will not
   *              paint a background on redraw.
   */
  public void setBackground(Color color) {
    if(m_background!=color)
      setDirty(true);
    m_background = color;
    }
  
  /** Mark the window as 'dirty' (needs to be repainted)
   * 
   * @param dirty true if the window is dirty, false if not
   */
  public void setDirty(boolean dirty) {
    if(dirty)
      m_flags.setFlags(WIN_FLAG_DIRTY);
    else
      m_flags.clearFlags(WIN_FLAG_DIRTY);
    }
  
  /** Determine if the window is dirty (needs to be repainted)
   * 
   * @return true if the window is dirty and needs to be repainted.
   */
  public boolean isDirty() {
    return m_flags.areFlagsSet(WIN_FLAG_DIRTY | WIN_FLAG_VISIBLE);
    }

  /** Set the visibility of the window
   * 
   * @param visible true if the window should be visible, false if not
   */
  public void setVisible(boolean visible) {
    // Any change ?
    if(m_flags.areFlagsSet(WIN_FLAG_VISIBLE)==visible)
      return;
    // Change the flag
    if(visible)
      m_flags.setFlags(WIN_FLAG_VISIBLE | WIN_FLAG_DIRTY);
    else
      m_flags.clearFlags(WIN_FLAG_VISIBLE);
    // Let the window know the state has changed
    onVisible(visible);
    }
  
  /** Determine if we are visible
   * 
   * @return true if the window is currently visible, false if not
   */
  public boolean isVisible() {
    return m_flags.areFlagsSet(WIN_FLAG_VISIBLE);
    }
  
  /** Get a window by location
   * 
   * @param point the Point we are searching for.
   * 
   * @return the smallest window that contains this point.
   */
  public Window getWindowByPoint(Point point) {
    if(!contains(point))
      return null;
    return this;
    }
  
  //-------------------------------------------------------------------------
  // Internal event and painting helpers
  //-------------------------------------------------------------------------

  /** Find all dirty child windows
   * 
   * As we have no child windows we simply add ourselves to the list (if we
   * are dirty).
   * 
   * @param children the list of dirty children
   */
  void findDirtyChildren(List<Window> children) {
    if(isDirty())
      children.add(this);
    }
  
  /** Set the offset for this window
   * 
   * @param offset the offset to use for future painting operations
   */
  void setOffset(IPoint offset) {
    if(m_root!=null)
      m_root.setOffset(offset);
    }
  
  /** Called to do a repaint of the window
   * 
   * This is an internal helper to manage the painting process. Child
   * windows should only override the onPaint() method and do custom
   * painting there - it will be called as needed by the internal logic
   * of the framework.
   * 
   * @param force if true force a repaint regardless of the 'dirty' state.
   */
  void doRepaint(boolean force) {
    // If we are not visible, don't do anything
    if(!isVisible())
      return;
    // If we are not dirty, don't do anything
    if(!(isDirty()||force))
      return;
    // Start the paint operation
    Rectangle region = new Rectangle(this);
    getRoot().setClip(region);
    beginPaint();
    // Erase the background if needed
    if(m_background!=null)
      fillRect(region, m_background);
    // Repaint the window
    setOffset(region);
    onPaint();
    // Finish the paint operation
    endPaint();
    setDirty(false);
    }
  
  /** Called to do an update of the window
   */
  void doUpdate() {
    onUpdate();
    }
  
  //-------------------------------------------------------------------------
  // Public event methods
  //-------------------------------------------------------------------------

  /** Called when the window is created
   * 
   */
  public void onCreate() {
    // Do nothing in this instance
    }
  
  /** Called when the window is closed
   * 
   */
  public void onClose() {
    // Do nothing in this instance
    }
  
  /** Called to update the window
   * 
   */
  public void onUpdate() {
    // Do nothing in this instance
    }
  
  /** Called when the window needs to be painted
   * 
   *  This method is called to redraw the window.
   */
  public void onPaint() {
    // Do nothing in this instance
    }
  
  /** Called when the visibility of the window has changed
   * 
   * @param visible true if the window is now visible, false if not
   */
  public void onVisible(boolean visible) {
    // Do nothing in this instance
    }
  
  //-------------------------------------------------------------------------
  // Implementation of IPoint and IRectangle
  //-------------------------------------------------------------------------

  /** Get the X co-ordinate for this point.
   * 
   * @return the X co-ordinate for this point.
   */
  public int getX() {
    return m_rectangle.x;
    }
  
  /** Set the X co-ordinate for this point.
   * 
   * @param nx the new X co-ordinate for this point.
   */
  public void setX(int nx) {
    m_rectangle.x = nx;
    }
  
  /** Get the Y co-ordinate for this point.
   * 
   * @return the Y co-ordinate for this point.
   */
  public int getY() {
    return m_rectangle.y;
    }
  
  /** Set the Y co-ordinate for this point.
   * 
   * @param ny the new Y co-ordinate for this point.
   */
  public void setY(int ny) {
    m_rectangle.y = ny;
    }
  
  /** Translate the point so the given point is the origin
   * 
   * @point origin the new origin for the co-ordinates
   * 
   * @return IPoint the translated instance.
   */
  public IPoint translate(IPoint origin) {
    return m_rectangle.translate(origin);
    }
  
  /** Get the width of the rectangle.
   * 
   * @return the width of the rectangle.
   */
  public int getWidth() {
    return m_rectangle.width;
    }
  
  /** Set the width of the rectangle.
   * 
   * @param w the new width of the rectangle.
   */
  public void setWidth(int w) {
    m_rectangle.width = w;
    }
  
  /** Get the height of the rectangle.
   * 
   * @return the height of the rectangle.
   */
  public int getHeight() {
    return m_rectangle.height;
    }
  
  /** Set the height of the rectangle.
   * 
   * @param h the new height of the rectangle.
   */
  public void setHeight(int h) {
    m_rectangle.height = h;
    }
  
  /** Determine if this rectangle contains the given point
   * 
   * @param point the point to test.
   * 
   * @return true if the rectangle contains the given point, false otherwise.
   */
  public boolean contains(Point point) {
    return m_rectangle.contains(point);
    }
  
  //-------------------------------------------------------------------------
  // Implementation of IFlags
  //-------------------------------------------------------------------------
  
  /** Set one or more flags to true.
   * 
   * @param flags the bit values to set
   */
  public void setFlags(int flags) {
    if(m_flags==null)
      m_flags = new Flags();
    m_flags.setFlags(flags);
    }
  
  /** Clear one or more flags.
   * 
   * @param flags the bit values to clear
   */
  public void clearFlags(int flags) {
    m_flags.clearFlags(flags);
    }
  
  /** Get the current set of flags
   * 
   * @return the current value of the flags
   */
  public int getFlags() {
    return m_flags.getFlags();
    }
  
  /** Determine if one or more flags are set
   * 
   * @param flags the flags to test for. All must be set to pass.
   */
  public boolean areFlagsSet(int flags) {
    return m_flags.areFlagsSet(flags);
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
    getRoot().beginPaint();
    }
  
  /** End a paint operation.
   * 
   * This method is used to signal the end of a complex paint operation.
   */
  public void endPaint() {
    getRoot().endPaint();
    }

  /** Set the clipping region for future operations
   * 
   * @param rect the Rectangle describing the clipping region.
   */
  public void setClip(IRectangle rect) {
    // Do nothing in this implementation
    }

  /** Display a single pixel.
   * 
   * @param point the Point at which to display the pixel.
   * @param color the Color to set the pixel to.
   */
  public void putPixel(IPoint point, Color color) {
    getRoot().putPixel(point, color);
    }

  /** Fill a rectangle with a specific color.
   * 
   * @param rect the Rectangle describing the area to fill.
   * @param color the Color to fill the rectangle with.
   */
  public void fillRect(IRectangle rect, Color color) {
    getRoot().fillRect(rect, color);
    }
  
  /** Draw a line from one point to another 
   * 
   * @param start the starting point for the line.
   * @param end the ending point for the line.
   * @param color the color to draw the line in.
   */
  public void drawLine(IPoint start, IPoint end, Color color) {
    getRoot().drawLine(start, end, color);
    }
  
  /** Draw a box around a rectangle.
   * 
   * @param rect the Rectangle to draw the box around.
   * @param color the Color to draw the box in.
   */
  public void drawBox(IRectangle rect, Color color) {
    getRoot().drawBox(rect, color);
    }

  /** Draw an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   */
  public void drawIcon(IPoint point, Icon icon, Color color) {
    getRoot().drawIcon(point, icon, color);
    }

  /** Draw a portion of an Icon to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param icon the Icon to display.
   * @param color the Color to use for the solid parts of the icon.
   * @param portion a Rectangle specifying the portion of the icon to draw.
   */
  public void drawIcon(IPoint point, Icon icon, Color color, IRectangle portion) {
    getRoot().drawIcon(point, icon, color, portion);
    }
  
  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   */
  public void drawImage(IPoint point, Image image, Palette palette) {
    getRoot().drawImage(point, image, palette);
    }
  
  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param palette the Palette to use to display the image.
   * @param portion the Rectangle describing the portion of the image to display.
   */
  public void drawImage(IPoint point, Image image, Palette palette, IRectangle portion) {
    getRoot().drawImage(point, image, palette, portion);
    }
  
  }
