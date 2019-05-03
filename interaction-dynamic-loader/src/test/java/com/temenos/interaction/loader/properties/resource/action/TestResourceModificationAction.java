package com.temenos.interaction.loader.properties.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.loader.properties.PropertiesChangedEventImpl;
import com.temenos.interaction.loader.properties.PropertiesLoadedEventImpl;

public class TestResourceModificationAction {

	@Test
	public void testExplicitFilename() {
		String resourcePattern = "myfile.properties";
		
		Action action = mock(Action.class);		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(action);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn(resourcePattern);
		
		PropertiesChangedEventImpl event = new PropertiesChangedEventImpl(null, resource, null);
		
		rma.notify(event);
		
		verify(action).execute(event);
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("myfile2.properties");
		
		PropertiesChangedEventImpl event2 = new PropertiesChangedEventImpl(null, resource2, null);

		rma.notify(event2);
		
		verify(action, never()).execute(event2);
	}

	@Test
	public void testMatchesFilenamePattern() {
		String resourcePattern = "IRIS-*.properties";
		
		Action action = mock(Action.class);
		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(action);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("IRIS-blah.properties");
		
		PropertiesChangedEventImpl event = new PropertiesChangedEventImpl(null, resource, null);
		
		rma.notify(event);
		
		verify(action).execute(event);
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("metadata-blah.properties");
		
		PropertiesChangedEventImpl event2 = new PropertiesChangedEventImpl(null, resource2, null);
				
		rma.notify(event2);
		
		verify(action, never()).execute(event2);
	}
	
	@Test
	public void testMatchesEventType() {
		String resourcePattern = "IRIS-*.properties";
		
		Action changedAction = mock(Action.class);
		Action loadedAction = mock(Action.class);
		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(changedAction);
		rma.setLoadedAction(loadedAction);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("IRIS-blah.properties");				
		
		PropertiesEvent event = new PropertiesChangedEventImpl(null, resource, null);
				
		rma.notify(event);
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("IRIS-blah.properties");
		PropertiesEvent event2 = new PropertiesLoadedEventImpl(null, resource2, null);		
		
		rma.notify(event2);
		
		verify(changedAction, times(1)).execute(event);
		verify(loadedAction, times(1)).execute(event2);		
	}	
	
	@Test
	public void testNotify() {
		String resourcePattern = "IRIS-*.properties";
		
		Action changedAction = mock(Action.class);
		Action loadedAction = mock(Action.class);
		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(changedAction);
		rma.setLoadedAction(loadedAction);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("IRIS-blah.properties");
		
		PropertiesEvent event = new PropertiesLoadedEventImpl(null, resource, null);
		
		rma.notify(event);
		
		verify(changedAction, never()).execute(event);		
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("metadata-blah.properties");
		
		PropertiesEvent event2 = new PropertiesLoadedEventImpl(null, resource2, null);
		
		rma.notify(event2);
		
		verify(changedAction, never()).execute(event2);
		verify(loadedAction, never()).execute(event2);
	}
}
