package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Set;

/**
 * Implementations of this interface provide a mechanism for registering states.
 *
 * @author mlambert
 *
 */
public interface StateRegisteration {
	/**
	 * Register a new state using the information given
	 */
	public void register(String stateName, String path, Set<String> methods);

}
