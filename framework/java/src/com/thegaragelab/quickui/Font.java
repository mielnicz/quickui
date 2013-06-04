/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 01/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui;

//--- Imports
import java.nio.*;
import java.util.*;

/** Represents a Font
 * 
 * A font is essentially an Icon (which is how the asset is registered) with
 * some additional metadata. The metadata describes which characters are
 * provided, their width and height and where they are located within the
 * Icon asset.
 *
 */
public class Font extends Asset {
  //--- Constants
  private static int MAX_CHAR = 127; //! Highest ASCII character code
  
  //--- Instance variables

  //-------------------------------------------------------------------------
  // Construction and initialisation
  //-------------------------------------------------------------------------
  
  /** Constructor
   * 
   * @param data the raw data for the asset.
   * @param size the number of bytes in the data array for the asset.
   */
  Font(byte[] data, int size) {
    super();
    }

  }
