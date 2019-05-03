/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
package com.temenos.interaction.rimdsl.generator.launcher;

/**
 * Implementations of this interface will receive notifications of validation events
 *  
 * @author mlambert
 */
public interface ValidatorEventListener {
	public void notify(String msg);
}
