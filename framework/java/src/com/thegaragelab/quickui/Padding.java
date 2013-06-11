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
  
  /** Get the padding for the left of the window.
   * 
   * @return the padding assigned to the left of the window.
   */
  public int getPaddingLeft() {
    return m_left;
    }
  
  /** Set the padding for the left of the window
   * 
   * @param padding the padding to assign to the left of the window
   */
  public void setPaddingLeft(int padding) {
    m_left = padding;
    }
  
  /** Get the padding for the right of the window.
   * 
   * @return the padding assigned to the right of the window.
   */
  public int getPaddingRight() {
    return m_right;
    }
  
  /** Set the padding for the right of the window
   * 
   * @param padding the padding to assign to the right of the window
   */
  public void setPaddingRight(int padding) {
    m_right = padding;
    }
  
  /** Get the padding for the top of the window.
   * 
   * @return the padding assigned to the top of the window.
   */
  public int getPaddingTop() {
    return m_top;
    }
  
  /** Set the padding for the top of the window
   * 
   * @param padding the padding to assign to the top of the window
   */
  public void setPaddingTop(int padding) {
    m_top = padding;
    }
  
  /** Get the padding for the bottom of the window.
   * 
   * @return the padding assigned to the bottom of the window.
   */
  public int getPaddingBottom() {
    return m_bottom;
    }
  
  /** Set the padding for the bottom of the window
   * 
   * @param padding the padding to assign to the bottom of the window
   */
  public void setPaddingBottom(int padding) {
    m_bottom = padding;
    }
  
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
