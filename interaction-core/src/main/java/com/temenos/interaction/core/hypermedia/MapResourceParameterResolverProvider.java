package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.HashMap;
import java.util.Map;


/**
 * A simple map based resource parameter resolver provider 
 *
 * @author mlambert
 *
 */
public class MapResourceParameterResolverProvider implements ResourceParameterResolverProvider {
	private Map<String, ResourceParameterResolver> nameToParameterResolver = new HashMap<String, ResourceParameterResolver>();

	/**
	 * @param nameToLocator
	 */
	public MapResourceParameterResolverProvider(Map<String, ResourceParameterResolver> nameToParameterResolver) {
		if(nameToParameterResolver == null || nameToParameterResolver.isEmpty()) {
			throw new IllegalArgumentException("nameToParameterResolver must be a non empty map");
		}
		
		this.nameToParameterResolver = nameToParameterResolver;
	}


	@Override
	public ResourceParameterResolver get(String name) {
		
		if(nameToParameterResolver.containsKey(name)) {
			return nameToParameterResolver.get(name);
		}
		
		throw new IllegalArgumentException("Invalid resource locator name: " + name);
	}

}
