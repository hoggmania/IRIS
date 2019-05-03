package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



/**
 * Implementations of this interface provide a way of resolving a RIM resource locator name to a concrete resource locator
 *
 * @author mlambert
 *
 */
public interface ResourceLocatorProvider {
	public ResourceLocator get(String name);
}
