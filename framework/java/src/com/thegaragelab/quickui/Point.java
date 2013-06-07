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

  /** Translate the point so the given point is the origin
   * 
   * @point origin the new origin for the co-ordinates
   * 
   * @return IPoint the translated instance.
   */
  public IPoint translate(IPoint origin) {
    return new Point(x + origin.getX(), y + origin.getY());
    }
  
  //-------------------------------------------------------------------------
  // Static helpers
  //-------------------------------------------------------------------------
  
  /** Convert an IPoint into a string for logging or display
   * 
   * @param point the IPoint to convert
   * 
   * @return a string representation of the point.
   */
  public static final String toString(IPoint point) {
    return String.format("X: %d, Y: %d",
      new Object[] {
        new Integer(point.getX()),
        new Integer(point.getY())
        }
      );
    }
  
  /** Offset the point
   * 
   * This is different from translate in that we want to find the offset of
   * this point from the given origin in the same co-ordinate space.
   * 
   * @param origin the new origin
   * @param point the point to find the offset of
   * 
   * @return a Point instance representing the given point relative to the
   *         new origin.
   */
  public static final Point offset(IPoint origin, IPoint point) {
    return new Point(
      point.getX() - origin.getX(),
      point.getY() - origin.getY()
      );
    }
  
  }
