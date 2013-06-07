/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Concrete implementation of the IDimension interface.
 *
 */
public class Dimension implements IDimension {
  //--- Constants
  public static final Dimension EMPTY = new Dimension(0, 0);
  
  //--- Instance variables
  public int width;  //! Width of the dimension
  public int height; //! Height of the dimension
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor with a width and height
   * 
   * @param w the width of the dimension
   * @param h the height of the dimension
   */
  public Dimension(int w, int h) {
    width = w;
    height = h;
    }
  
  /** Constructor from another IDimension implementation
   * 
   * @param dimension the dimension to copy
   */
  public Dimension(IDimension dimension) {
    width = dimension.getWidth();
    height = dimension.getHeight();
    }
  
  //-------------------------------------------------------------------------
  // Getters and Setters
  //-------------------------------------------------------------------------
  
  /** Get the width of the rectangle.
   * 
   * @return the width of the rectangle.
   */
  public int getWidth() {
    return width;
    }
  
  /** Set the width of the rectangle.
   * 
   * @param w the new width of the rectangle.
   */
  public void setWidth(int w) {
    width = w;
    }
  
  /** Get the height of the rectangle.
   * 
   * @return the height of the rectangle.
   */
  public int getHeight() {
    return height;
    }
  
  /** Set the height of the rectangle.
   * 
   * @param h the new height of the rectangle.
   */
  public void setHeight(int h) {
    height = h;
    }
  
  //-------------------------------------------------------------------------
  // Static helpers
  //-------------------------------------------------------------------------
  
  /** Convert an IDimension into a string for logging or display
   * 
   * @param dimension the IDimension to convert
   * 
   * @return a string representation of the dimension.
   */
  public static final String toString(IDimension dimension) {
    return String.format("W: %d, H: %d",
      new Object[] {
        new Integer(dimension.getWidth()),
        new Integer(dimension.getHeight())
        }
      );
    }
  
  }
