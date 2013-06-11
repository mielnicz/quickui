/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** This interface describes the basic interface to a window.
 */
public interface IWindow extends IRectangle, ISurface {
  /** Get the parent of this Window
   * 
   * @return the Window instance that represents the parent of this window.
   */
  public abstract IWindow getParent();

  /** Mark the window as 'dirty' (needs to be repainted)
   * 
   * @param dirty true if the window is dirty, false if not
   */
  public abstract void setDirty(boolean dirty);

  /** Determine if the window is dirty (needs to be repainted)
   * 
   * @return true if the window is dirty and needs to be repainted.
   */
  public abstract boolean isDirty();

  /** Set the visibility of the window
   * 
   * @param visible true if the window should be visible, false if not
   */
  public abstract void setVisible(boolean visible);

  /** Determine if we are visible
   * 
   * @return true if the window is currently visible, false if not
   */
  public abstract boolean isVisible();

  /** Indicate to the Window it should erase it's background on repaint.
   * 
   * @param erase true if the Window should erase it's background.
   */
  public abstract void setEraseBackground(boolean erase);
  
  /** Determine if the window should erase it's background on repaint.
   * 
   * @return true if the window should erase it's background.
   */
  public abstract boolean getEraseBackground();
  
  /** Allow the window to receive touch events
   * 
   * @param accept true if this Window should accept touch events.
   */
  public abstract void setAcceptTouch(boolean accept);

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
  public abstract IWindow getAcceptTouch();

  /** Get a window by location
   * 
   * @param point the Point we are searching for.
   * 
   * @return the smallest window that contains this point.
   */
  public abstract IWindow getWindowByPoint(IPoint point);

  /** Called when the window is created
   * 
   */
  public abstract void onCreate();

  /** Called when the window is closed
   * 
   */
  public abstract void onClose();

  /** Called to update the window
   * 
   */
  public abstract void onUpdate();

  /** Called to erase the background of the window.
   */
  public abstract void onEraseBackground();
  
  /** Called when the window needs to be painted
   * 
   *  This method is called to redraw the window.
   */
  public abstract void onPaint();

  /** Called when an input event is targeted to this window
   * 
   * An input event represents a key press or activity on the touch screen.
   * In general, unless you are implementing a control, you don't need to
   * do anything with these events.
   * 
   * @param evType the type of the event
   * @param where the location of the event (in window co-ordinates)
   */
  public abstract void onTouchEvent(int evType, IPoint where);

  /** Called when the visibility of the window has changed
   * 
   * @param visible true if the window is now visible, false if not
   */
  public abstract void onVisible(boolean visible);

}