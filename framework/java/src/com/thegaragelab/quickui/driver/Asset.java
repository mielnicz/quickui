/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 22, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.driver;

/** Represents a single asset for the Driver
 *
 */
public class Asset {
  //--- Asset types
  public static final int ICON  = 0;
  public static final int IMAGE = 1;
  
  //--- Instance variables
  private int m_handle; //! The handle assigned to this asset
  
  /** Get the handle for this asset
   */
  public int getHandle() {
    return m_handle;
    }
  
  }
