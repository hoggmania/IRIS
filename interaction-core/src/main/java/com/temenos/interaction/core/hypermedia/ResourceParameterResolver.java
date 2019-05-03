package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



/**
 * Implementations of this interface provide a way of resolving an aliases to resource parameters and values
 *
 * @author mlambert
 *
 */
public interface ResourceParameterResolver {
	ParameterAndValue[] resolve(Object[] aliases, ResourceParameterResolverContext context);
}