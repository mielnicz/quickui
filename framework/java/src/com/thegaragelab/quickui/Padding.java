/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 08/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents padding around a rectangular area.
 *
 */
public class Padding {
  //--- Constants
  public static int DEFAULT_PADDING = 2; //! Default padding (for all edges)
  
  //--- Instance variables
  private int m_left;    //! Padding to the left
  private int m_right;  //! Padding to the right
  private int m_top;    //! Padding to the top
  private int m_bottom; //! Padding to the bottom
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Default constructor
   * 
   */
  public Padding() {
    m_left = DEFAULT_PADDING;
    m_right = DEFAULT_PADDING;
    m_top = DEFAULT_PADDING;
    m_bottom = DEFAULT_PADDING;
    }
  
  /** Constructor with horizontal and vertical padding
   * 
   */
  public Padding(int horizontal, int vertical) {
    m_left = horizontal;
    m_right = horizontal;
    m_top = vertical;
    m_bottom = vertical;
    }
  
  /** Constructor with all elements
   * 
   */
  public Padding(int left, int right, int top, int bottom) {
    m_left = left;
    m_right = right;
    m_top = top;
    m_bottom = bottom;
    }
  
  /** Constructor from another padding element
   * 
   */
  public Padding(Padding padding) {
    m_left = padding.m_left;
    m_right = padding.m_right;
    m_top = padding.m_top;
    m_bottom = padding.m_bottom;
    }
  
  //-------------------------------------------------------------------------
  // Getters and setters
  //-------------------------------------------------------------------------
  
  
  //-------------------------------------------------------------------------
  // Static helpers
  //-------------------------------------------------------------------------
  
  /** Clone a copy of this padding description
   * 
   * @return a copy of this padding instance.
   */
  public final Padding clone() {
    return new Padding(this);
    }
  
  }
