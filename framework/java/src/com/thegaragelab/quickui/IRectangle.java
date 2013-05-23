/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Extends the IPoint interface to represent a rectangular area.
 *
 */
public interface IRectangle extends IPoint, IDimension {
  /** Determine if this rectangle contains the given point
   * 
   * @param point the point to test.
   * 
   * @return true if the rectangle contains the given point, false otherwise.
   */
  public boolean contains(Point point) ;
  
  }
