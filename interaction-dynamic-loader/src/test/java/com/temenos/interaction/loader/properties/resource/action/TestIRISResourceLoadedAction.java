package com.temenos.interaction.loader.properties.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.springdsl.EagerSpringDSLResourceStateProvider;
import com.temenos.interaction.springdsl.SpringDSLResourceStateProvider;
import org.junit.Test;

import java.util.Properties;

import static org.mockito.Mockito.*;


/**
 * TODO: Document me!
 *
 * @author mlambert
 *
 */
public class TestIRISResourceLoadedAction {

	@Test
	public void test() {
		IRISResourceLoadedAction action = new IRISResourceLoadedAction();
		
		SpringDSLResourceStateProvider resourceStateProvider = mock(SpringDSLResourceStateProvider.class);
		action.setResourceStateProvider(resourceStateProvider);
		
		Properties props = new Properties();
		props.put("test", "n/a");
		PropertiesEvent event = mock(PropertiesEvent.class);
		when(event.getNewProperties()).thenReturn(props);
		
		action.execute(event);
		
		verify(resourceStateProvider).addState("test", props);
	}

	@Test
	public void testForEagerProvider() {
		IRISResourceLoadedAction action = new IRISResourceLoadedAction();

		SpringDSLResourceStateProvider resourceStateProvider = mock(EagerSpringDSLResourceStateProvider.class);
		action.setResourceStateProvider(resourceStateProvider);

		Properties props = new Properties();
		props.put("test", "n/a");
		PropertiesEvent event = mock(PropertiesEvent.class);
		when(event.getNewProperties()).thenReturn(props);

		action.execute(event);

		verify(resourceStateProvider).addState("test", props);
	}

}
