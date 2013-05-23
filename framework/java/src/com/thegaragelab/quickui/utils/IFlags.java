/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.utils;

/** Defines an interface for managing flags.
 */
public interface IFlags {
  /** Set one or more flags to true.
   * 
   * @param flags the bit values to set
   */
  public void setFlags(int flags);
  
  /** Clear one or more flags.
   * 
   * @param flags the bit values to clear
   */
  public void clearFlags(int flags);
  
  /** Get the current set of flags
   * 
   * @return the current value of the flags
   */
  public int getFlags();
  
  /** Determine if one or more flags are set
   * 
   * @param flags the flags to test for. All must be set to pass.
   */
  public boolean areFlagsSet(int flags);
  
  }
