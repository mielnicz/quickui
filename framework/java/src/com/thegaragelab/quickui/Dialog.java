/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 23/07/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents a modal dialog.
 */
public class Dialog extends Container {
  /** Default constructor
   * 
   */
  public Dialog() {
    this(
      new Dimension(
        Application.getInstance().getWidth() / 2,
        Application.getInstance().getHeight() / 2
        )
      );
    }
  
  /** Constructor with a dialog size
   */
  public Dialog(Dimension size) {
    super(null, new Rectangle(Point.ORIGIN, size), Window.WIN_FLAG_ERASE_BACKGROUND, Window.WIN_FLAG_VISIBLE);
    // Adjust the position of the dialog to center it
    setX((Application.getInstance().getWidth() - getWidth()) / 2);
    setY((Application.getInstance().getHeight() - getHeight()) / 2);
    }

  //-------------------------------------------------------------------------
  // Implement of IWindow
  //-------------------------------------------------------------------------
  
  /** Called to erase the background of the window.
   */
  @Override
  public void onEraseBackground() {
    Rectangle client = new Rectangle(Point.ORIGIN, this);
    // Fill in the background
    fillRect(
      client,
      Application.getInstance().getSystemColor(Application.SYS_COLOR_DLG_BACKGROUND)
      );
    // Draw the border
    drawBox(
      client,
      Application.getInstance().getSystemColor(Application.SYS_COLOR_CTRL_BORDER)
      );
    }
  
  }
