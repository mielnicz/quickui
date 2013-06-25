/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 08/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

/** Represents padding around a rectangular area.
 *
 * A padding instance describes the amount of space to keep clear around
 * the edges of a rectangle.
 */
public class Padding {
  //--- Constants
  public static int DEFAULT_PADDING = 2; //! Default padding (for all edges)
  public static int BUTTON_PADDING  = 4; //! Padding for buttons
  
  //--- Special instances
  public static final Padding NONE    = new Padding(0);               //! No padding
  public static final Padding DEFAULT = new Padding(DEFAULT_PADDING); //! Default padding
  public static final Padding BUTTON  = new Padding(BUTTON_PADDING);  //! Button padding
  
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
   * Set's the default padding.
   */
  public Padding() {
    m_left = DEFAULT_PADDING;
    m_right = DEFAULT_PADDING;
    m_top = DEFAULT_PADDING;
    m_bottom = DEFAULT_PADDING;
    }
  
  /** Constructor with constant padding
   * 
   * @param padding the default padding for all sizes.
   */
  public Padding(int padding) {
    m_left = padding;
    m_right = padding;
    m_top = padding;
    m_bottom = padding;
    }
  
  /** Constructor with horizontal and vertical padding
   * 
   * @param horizontal padding in the horizontal direction.
   * @param vertical padding in the vertical direction.
   */
  public Padding(int horizontal, int vertical) {
    m_left = horizontal;
    m_right = horizontal;
    m_top = vertical;
    m_bottom = vertical;
    }
  
  /** Constructor with all elements
   * 
   * @param left padding to the left of the element.
   * @param right padding to the right of the element.
   * @param top padding to the top of the element.
   * @param bottom padding to the bottom of the element.
   */
  public Padding(int left, int right, int top, int bottom) {
    m_left = left;
    m_right = right;
    m_top = top;
    m_bottom = bottom;
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
  
  /** Get the padding for the right of the window.
   * 
   * @return the padding assigned to the right of the window.
   */
  public int getPaddingRight() {
    return m_right;
    }
  
  /** Get the padding for the top of the window.
   * 
   * @return the padding assigned to the top of the window.
   */
  public int getPaddingTop() {
    return m_top;
    }
  
  /** Get the padding for the bottom of the window.
   * 
   * @return the padding assigned to the bottom of the window.
   */
  public int getPaddingBottom() {
    return m_bottom;
    }
  
  }
