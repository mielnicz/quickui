/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.util.*;

/** A container is a special type of window that can have child windows.
 *
 */
public class Container extends Window {
  //--- Instance variables
  private List<Window> m_children; //! Child windows
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------

  /** Constructor with a parent Window and a Rectangle describing it position.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   */
  public Container(Container parent, Rectangle rect) {
    super(parent, rect);
    }

  /** Constructor with a parent Window and a Dimension describing it's size.
   * 
   * @param parent the parent window for this instance.
   * @param dimension the dimension of the new window.
   */
  public Container(Container parent, IDimension dimension) {
    super(parent, dimension);
    }
  
  /** Constructor with a parent Window and a width and height.
   * 
   * @param parent the parent Window for this instance.
   * @param width the width of the window in pixels.
   * @param height the height of the window in pixels.
   */
  public Container(Container parent, int width, int height) {
    super(parent, width, height);
    }

  //-------------------------------------------------------------------------
  // Implementation of Window
  //-------------------------------------------------------------------------
  
  /** Get the focused window
   * 
   * @return the window that currently has focus.
   */
  public Window getFocusedWindow() {
    if(!hasFocus())
      return null;
    return this;
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
    // Do we have children ?
    if((m_children==null)||(m_children.size()==0))
      return null;
    // Check each child
    for(Window child: m_children) {
      Window result = child.getWindowByPoint(point);
      if(result!=null)
        return result;
      }
    // Not found, that means it's us
    return this;
    }
  
  //-------------------------------------------------------------------------
  // Container specific operations
  //-------------------------------------------------------------------------

  /** Add a child window to this container.
   * 
   * @param window the Window to add as a child.
   */
  public void addChild(Window child) {
    synchronized(this) {
      if(m_children==null)
        m_children = new ArrayList<Window>();
      m_children.add(child);
      }
    }
  
  //-------------------------------------------------------------------------
  // Internal event methods
  //-------------------------------------------------------------------------

  /** Called to do a repaint of the window
   * 
   * Containers are treated differently here. Even though the container itself
   * may not be dirty one or more of it's child windows may be so we have to
   * walk through all of them and repaint. 
   */
  void doRepaint() {
    Rectangle region;
    // Do we have any children ?
    if((m_children==null)||(m_children.size()==0)) {
      if(isDirty()) {
        region = new Rectangle(this);
        if(areFlagsSet(Window.WFLAG_ERASE_BACKGROUND))
          onEraseBackground(region);
        onPaint(region);
        }
      return;
      }
    // Check our children and repaint as needed
    if(m_children==null)
      return;
    for(Window child: m_children) {
      if(!child.isDirty())
        continue;
      region = new Rectangle(child);
      if(areFlagsSet(Window.WFLAG_ERASE_BACKGROUND))
        onEraseBackground(region);
      onPaint(region);
      child.doRepaint();
      }
    }
  
  /** Called to do an update of the window.
   * 
   *  In the case of a container we do an update on all child windows as
   *  well as our own.
   */
  void doUpdate() {
    super.doUpdate();
    // Do we have children to deal with?
    if((m_children==null)||(m_children.size()==0))
      return;
    // Process all child windows
    for(Window child: m_children)
      child.doUpdate();
    }
  
  }
