/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

/** Represents a rectangular area that can be drawn on.
 *
 *  This interface provides a way to draw items to the display.
 */
public interface ISurface extends IDimension {
  /** Begin a paint operation.
   * 
   * This method is used to signal the start of a complex paint operation.
   * It is used to help the driver optimise updates to the physical display.
   */
  public void beginPaint();
  
  /** End a paint operation.
   * 
   * This method is used to signal the end of a complex paint operation.
   */
  public void endPaint();

  /** Set the clipping region for future operations
   * 
   * @param rect the Rectangle describing the clipping region.
   */
  public void setClip(IRectangle rect);
  
  /** Display a single pixel.
   * 
   * @param point the Point at which to display the pixel.
   * @param color the Color to set the pixel to.
   */
  public void putPixel(IPoint point, Color color);

  /** Fill a rectangle with a specific color.
   * 
   * @param rect the Rectangle describing the area to fill.
   * @param color the Color to fill the rectangle with.
   */
  public void fillRect(IRectangle rect, Color color);
  
  /** Draw a line from one point to another 
   * 
   * @param start the starting point for the line.
   * @param end the ending point for the line.
   * @param color the color to draw the line in.
   */
  public void drawLine(IPoint start, IPoint end, Color color);
  
  /** Draw a box around a rectangle.
   * 
   * @param rect the Rectangle to draw the box around.
   * @param color the Color to draw the box in.
   */
  public void drawBox(IRectangle rect, Color color);

  /** Draw an Image to the screen.
   * 
   * @param point the Point specifying the top left corner of the icon.
   * @param image the Image to display.
   * @param source the Rectangle describing the portion of the image to display (optional - may be null).
   * @param mask the Icon to use as a mask (optional - may be null).
   * @param color the color to use for icons (optional - default is black).
   * @param palette the Palette to use to display the image (only required for 4bpp images).
   */
  public void drawImage(IPoint point, Image image, IRectangle source, Icon mask, Color color, Palette palette);

  /** Draw a single character using the given font.
   * 
   * @param font the Font to use to render the character.
   * @param point the location to draw the character at.
   * @param color the Color to draw the character with.
   * @param ch the character to draw.
   */
  public void drawChar(Font font, IPoint point, Color color, char ch);

  /** Draw a string using the given font.
   * 
   * @param font the Font to use to render the character.
   * @param point the location to draw the character at.
   * @param color the Color to draw the character with.
   * @param string the string to draw.
   */
  public void drawString(Font font, IPoint point, Color color, String string);

  }
