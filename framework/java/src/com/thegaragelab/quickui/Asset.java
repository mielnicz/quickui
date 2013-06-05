/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.io.*;
import java.util.*;

/** Represents a single asset.
 * 
 * This class provides the base class for external assets used by the driver
 * such as images, icons, fonts and palettes. These assets must be registered
 * with the driver and assigned an asset handle.
 * 
 * As well as providing the base for the asset class heirarchy this class
 * provides a number of static methods that allow the application (and
 * framework) to load assets easily.
 */
public class Asset {
  //--- Asset types
  public static final int ICON    = 0; //! Represents an icon
  public static final int IMAGE   = 1; //! Represents an image
  public static final int PALETTE = 2; //! Represents a palette
  public static final int FONT    = 3; //! Represents a font
  
  //--- Asset suffixes
  private static final String[] SUFFIXES = {
    ".qco", //! An icon
    ".qmg", //! An image
    ".qpl", //! A palette
    ".qfn", //! A font
    };
  
  //--- Asset locations
  private static final String SYSTEM_ASSETS      = "assets/system/";
  private static final String APPLICATION_ASSETS = "assets/application/";
  
  //--- Asset buffer
  private static final int MAX_ASSET_SIZE = 34 * 1024;       //! Maximum size of an asset
  private static byte[] m_buffer = new byte[MAX_ASSET_SIZE]; //! Buffer for loading assets
  
  //--- Class variables
  private static Map<String, Asset> m_assets; //! All loaded assets
  
  //--- Instance variables
  protected int m_handle; //! The handle assigned to this asset
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Default constructor
   * 
   */
  protected Asset() {
    m_handle = -1;
    }
  
  //-------------------------------------------------------------------------
  // Asset management
  //-------------------------------------------------------------------------
  
  /** Load a raw asset
   * 
   * @param name the name of the asset
   * 
   * @return the number of bytes read into the buffer for this asset or -1 on error.
   */
  private static int loadRawAsset(String name) {
    // Check application assets first
    InputStream input = ClassLoader.getSystemResourceAsStream(APPLICATION_ASSETS + name);
    // If not available there, try the system assets
    if(input==null)
      input = ClassLoader.getSystemResourceAsStream(SYSTEM_ASSETS + name);
    // If we couldn't find anything we fail now
    if(input==null)
      return -1;
    // Load all the data into a byte array
    int size, offset = 0;
    try {
      while((size=input.read(m_buffer, offset, m_buffer.length - offset))>0)
        offset = offset + size;
      }
    catch(Exception ex) {
      // Could not read the asset
      return -1;
      }
    // All done
    return offset;
    }
  
  /** Determine if an asset is already loaded.
   * 
   * @param name the name of the asset
   * 
   * @return an Asset instance if it has been loaded, null if not.
   */
  private static Asset getAsset(String name) {
    // Anything loaded ?
    if(m_assets==null)
      return null;
    // Check it
    return m_assets.get(name);
    }
  
  /** Mark an asset as loaded
   * 
   * @param name the name of the asset to register
   * @param asset the asset to register
   */
  private static void addAsset(String name, Asset asset) {
    if(m_assets==null)
      m_assets = new HashMap<String, Asset>();
    m_assets.put(name, asset);
    }
  
  //-------------------------------------------------------------------------
  // Factory functions
  //-------------------------------------------------------------------------
  
  /** Load an Icon resource
   *
   * @param name the name of the resource to load
   * 
   * @return the asset that has been loaded and registered or null on error.
   */
  public static final synchronized Icon loadIcon(String name) {
    // See if the asset is already available
    name = name + SUFFIXES[ICON];
    Icon icon = (Icon)getAsset(name);
    if(icon!=null)
      return icon;
    // Load the raw data for the asset
    int size = loadRawAsset(name);
    if(size<0)
      return null;
    // Now create the instance
    icon = new Icon(m_buffer, 0, size);
    if(icon.getHandle()<0)
      return null;
    // And register it in the cache of assets
    addAsset(name, icon);
    return icon;
    }
  
  /** Load an image resource
   *
   * @param name the name of the resource to load
   * 
   * @return the asset that has been loaded and registered or null on error.
   */
  public static final synchronized Image loadImage(String name) {
    // See if the asset is already available
    name = name + SUFFIXES[IMAGE];
    Image image = (Image)getAsset(name);
    if(image!=null)
      return image;
    // Load the raw data for the asset
    int size = loadRawAsset(name);
    if(size<0)
      return null;
    // Now create the instance
    image = new Image(m_buffer, 0, size);
    if(image.getHandle()<0)
      return null;
    // And register it in the cache of assets
    addAsset(name, image);
    return image;
    }
  
  /** Load a palette resource
   *
   * @param name the name of the resource to load
   * 
   * @return the asset that has been loaded and registered or null on error.
   */
  public static final synchronized Palette loadPalette(String name) {
    // See if the asset is already available
    name = name + SUFFIXES[PALETTE];
    Palette palette = (Palette)getAsset(name);
    if(palette!=null)
      return palette;
    // Load the raw data for the asset
    int size = loadRawAsset(name);
    if(size<0)
      return null;
    // Now create the instance
    palette = new Palette(m_buffer, 0, size);
    if(palette.getHandle()<0)
      return null;
    // And register it in the cache of assets
    addAsset(name, palette);
    return palette;
    }
  
  /** Load a font resource
   *
   * @param name the name of the resource to load
   * 
   * @return the asset that has been loaded and registered or null on error.
   */
  public static final synchronized Font loadFont(String name) {
    // See if the asset is already available
    name = name + SUFFIXES[FONT];
    Font font = (Font)getAsset(name);
    if(font!=null)
      return font;
    // Load the raw data for the asset
    int size = loadRawAsset(name);
    if(size<0)
      return null;
    // Now create the instance
    font = new Font(m_buffer, 0, size);
    if(font.getHandle()<0)
      return null;
    // And register it in the cache of assets
    addAsset(name, font);
    return font;
    }
  
  //-------------------------------------------------------------------------
  // Getters
  //-------------------------------------------------------------------------
  
  /** Get the handle for this asset
   */
  public int getHandle() {
    return m_handle;
    }
  
  }
