/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** A Common interface for two dimensional points.
 *
 */
public interface IPoint {
  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------
  
  /** Get the X co-ordinate for this point.
   * 
   * @return the X co-ordinate for this point.
   */
  public int getX();
  
  /** Set the X co-ordinate for this point.
   * 
   * @param nx the new X co-ordinate for this point.
   */
  public void setX(int nx);
  
  /** Get the Y co-ordinate for this point.
   * 
   * @return the Y co-ordinate for this point.
   */
  public int getY();
  
  /** Set the Y co-ordinate for this point.
   * 
   * @param ny the new Y co-ordinate for this point.
   */
  public void setY(int ny);
  
  /** Translate the point so the given point is the origin
   * 
   * @point origin the new origin for the co-ordinates
   * 
   * @return IPoint the translated instance.
   */
  public IPoint translate(IPoint origin);
  
  }
