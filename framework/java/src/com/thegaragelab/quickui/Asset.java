/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

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
  public static final int ICON  = 0;
  public static final int IMAGE = 1;
  
  //--- Instance variables
  private int m_handle; //! The handle assigned to this asset
  
  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  //-------------------------------------------------------------------------
  // Getters
  //-------------------------------------------------------------------------
  
  /** Get the handle for this asset
   */
  public int getHandle() {
    return m_handle;
    }
  
  }
