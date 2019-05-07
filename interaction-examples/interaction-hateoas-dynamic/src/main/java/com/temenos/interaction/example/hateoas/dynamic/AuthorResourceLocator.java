package com.temenos.interaction.example.hateoas.dynamic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.hypermedia.ResourceLocator;
import com.temenos.interaction.core.hypermedia.ResourceState;

/**
 * A mock resource locator
 *
 * @author mlambert
 */
public class AuthorResourceLocator implements ResourceLocator {
	private ResourceState target = null;
	
	
	public AuthorResourceLocator(ResourceState target) {
		this.target = target;
	}

	@Override
	public ResourceState resolve(Object... alias) {				
		return target;
	}

}
