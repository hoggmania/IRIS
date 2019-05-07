package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestMapResourceLocatorProvider {

	@Test(expected=IllegalArgumentException.class)
	public void testNullConstructor() {
		new MapResourceLocatorProvider(null);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyConstructor() {
		new MapResourceLocatorProvider(null);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetInvalidResourceLocator() {
		Map<String, ResourceLocator> nameToResourceLocator = new HashMap<String, ResourceLocator>();
		nameToResourceLocator.put("dummy", mock(ResourceLocator.class));
				
		ResourceLocatorProvider resourceLocatorProvider = new MapResourceLocatorProvider(nameToResourceLocator);
		resourceLocatorProvider.get("fred");
	}
	
	@Test
	public void testGetValidResourceLocator() {
		Map<String, ResourceLocator> nameToResourceLocator = new HashMap<String, ResourceLocator>();
		ResourceLocator expectedLocator = mock(ResourceLocator.class);
		nameToResourceLocator.put("dummy", expectedLocator);
				
		ResourceLocatorProvider resourceLocatorProvider = new MapResourceLocatorProvider(nameToResourceLocator);
		ResourceLocator actualLocator = resourceLocatorProvider.get("dummy");
		
		assertEquals(expectedLocator, actualLocator);
	}		
}
