/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* 08/06/2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.controls;

//--- Imports
import com.thegaragelab.quickui.*;

/** IControlEventHandler
 *
 */
public interface IControlEventHandler {
  /** Handle a control event
   * 
   */
  void onEvent(IWindow source, int event, Object data);
  
  }
