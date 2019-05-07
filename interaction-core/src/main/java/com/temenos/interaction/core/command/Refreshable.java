package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 *
 * @author andres
 */
public interface Refreshable<T> {
  public void refresh(T context);    
}
