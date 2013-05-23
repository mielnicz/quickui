/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents a single 2 dimensional point.
 */
public class Point implements IPoint {
  //--- Constants
  public static final Point ORIGIN = new Point(0, 0);
  
  //--- Instance variables
  public int x; //! The X co-ordinate
  public int y; //! The Y co-ordinate
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor with 2 points
   * 
   * @param nx the X co-ordinate to assign to the point.
   * @param ny the Y co-ordinate to assign to the point.
   */
  public Point(int nx, int ny) {
    x = nx;
    y = ny;
    }
  
  /** Constructor from another point (copy constructor)
   * 
   * @param point the Point to copy
   */
  public Point(IPoint point) {
    x = point.getX();
    y = point.getY();
    }
  
  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------
  
  /** Get the X co-ordinate for this point.
   * 
   * @return the X co-ordinate for this point.
   */
  public int getX() {
    return x;
    }
  
  /** Set the X co-ordinate for this point.
   * 
   * @param nx the new X co-ordinate for this point.
   */
  public void setX(int nx) {
    x = nx;
    }
  
  /** Get the Y co-ordinate for this point.
   * 
   * @return the Y co-ordinate for this point.
   */
  public int getY() {
    return y;
    }
  
  /** Set the Y co-ordinate for this point.
   * 
   * @param ny the new Y co-ordinate for this point.
   */
  public void setY(int ny) {
    y = ny;
    }
  
  }
