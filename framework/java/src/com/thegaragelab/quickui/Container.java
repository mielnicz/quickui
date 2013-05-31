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

  /** Constructor with a Rectangle describing its area.
   * 
   * @param rect the Rectangle describing the location and size of the window.
   */
  Container(Rectangle rect) {
    super(rect);
    }

  /** Constructor with a parent Window and a Rectangle describing it position.
   * 
   * @param parent the parent window for this instance.
   * @param rect the Rectangle describing the location and size of the window.
   */
  public Container(Container parent, Rectangle rect) {
    super(parent, rect);
    }

  /** Initialise the state
   * 
   *  This method is used to initialise the state for the type of window
   *  being created. Child classes may override this to set their own
   *  initial state but must call the parent implementation.
   */
  void initialiseState() {
    super.initialiseState();
    // Create our list of child elements
    m_children = new ArrayList<Window>();
    }
  
  //-------------------------------------------------------------------------
  // Implementation of Window
  //-------------------------------------------------------------------------
  
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
  void add(Window child) {
    m_children.add(child);
    }
  
  /** Remove a child window from this container
   * 
   * @param window the Window to remove
   */
  public void remove(Window child) {
    if(m_children.remove(child))
      child.onClose();
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
    super.doRepaint();
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
