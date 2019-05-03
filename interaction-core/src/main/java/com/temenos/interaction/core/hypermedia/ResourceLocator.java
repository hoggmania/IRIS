package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Implementations of this interface provide a way of resolving an alias to a resource state name 
 *
 * @author mlambert
 *
 */
public interface ResourceLocator {
	/** 
	 * @param alias
	 * 			The alias to resolve to a resource state name
	 * 
	 * @return
	 */
	ResourceState resolve(Object... alias);
}
