package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.HashMap;
import java.util.Map;


/**
 * A simple map based resource locator provider 
 *
 * @author mlambert
 *
 */
public class MapResourceLocatorProvider implements ResourceLocatorProvider {
	private Map<String, ResourceLocator> nameToLocator = new HashMap<String, ResourceLocator>();

	/**
	 * @param nameToLocator
	 */
	public MapResourceLocatorProvider(Map<String, ResourceLocator> nameToLocator) {
		if(nameToLocator == null || nameToLocator.isEmpty()) {
			throw new IllegalArgumentException("nameToLocator must be a non empty map");
		}
		
		this.nameToLocator = nameToLocator;
	}


	@Override
	public ResourceLocator get(String name) {
		
		if(nameToLocator.containsKey(name)) {
			return nameToLocator.get(name);
		}
		
		throw new IllegalArgumentException("Invalid resource locator name: " + name);
	}

}
