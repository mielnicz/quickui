/*---------------------------------------------------------------------------*
* $Id$
*----------------------------------------------------------------------------*
* May 23, 2013 - shane
* 
* Initial version
*---------------------------------------------------------------------------*/
package com.thegaragelab.quickui.utils;

/** Implementation of flags management
 */
public class Flags {
  //--- Instance variables
  private int m_flags;
 
  //-------------------------------------------------------------------------
  // Implementation of IFlags
  //-------------------------------------------------------------------------

  /** Set one or more flags to true.
   * 
   * @param flags the bit values to set
   */
  public void setFlags(int flags) {
    m_flags |= (flags & 0xFFFF);
    }
  
  /** Clear one or more flags.
   * 
   * @param flags the bit values to clear
   */
  public void clearFlags(int flags) {
    m_flags &= ((~flags) & 0xFFFF);
    }
  
  /** Get the current set of flags
   * 
   * @return the current value of the flags
   */
  public int getFlags() {
    return m_flags & 0xFFFF;
    }
  
  /** Determine if one or more flags are set
   * 
   * @param flags the flags to test for. All must be set to pass.
   */
  public boolean areFlagsSet(int flags) {
    return (m_flags & (flags & 0xFFFF)) == (flags & 0xFFFF);
    }

  }
