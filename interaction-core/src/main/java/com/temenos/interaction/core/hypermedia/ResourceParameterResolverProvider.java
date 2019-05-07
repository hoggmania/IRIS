package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



/**
 * Implementations of this interface provide a way of resolving a RIM resource locator name to a concrete resource parameter resolver
 *
 * @author mlambert
 *
 */
public interface ResourceParameterResolverProvider {
	public ResourceParameterResolver get(String name);
}
