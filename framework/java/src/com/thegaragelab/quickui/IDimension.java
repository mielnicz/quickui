/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents a dimension
 *
 *  A dimension represents a width and height in two dimensional space.
 */
public interface IDimension {
  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------
  
  /** Get the width of the dimension.
   * 
   * @return the width of the dimension.
   */
  public int getWidth();
  
  /** Set the width of the dimension.
   * 
   * @param w the new width of the dimension.
   */
  public void setWidth(int w);
  
  /** Get the height of the dimension.
   * 
   * @return the height of the dimension.
   */
  public int getHeight();
  
  /** Set the height of the dimension.
   * 
   * @param h the new height of the dimension.
   */
  public void setHeight(int h);

  }
