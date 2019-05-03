package com.temenos.interaction.loader.xml.resource.action;

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
import com.temenos.interaction.loader.xml.XmlChangedEventImpl;

public class TestResourceModificationAction {

	@Test
	public void testExplicitFilename() {
		String resourcePattern = "meta-file.xml";
		
		Action action = mock(Action.class);		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(action);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn(resourcePattern);
		
		XmlChangedEventImpl event = new XmlChangedEventImpl(resource);
		
		rma.notify(event);
		
		verify(action).execute(event);
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("myfile2.xml");
		
		XmlChangedEventImpl event2 = new XmlChangedEventImpl(resource2);

		rma.notify(event2);
		
		verify(action, never()).execute(event2);
	}

	@Test
	public void testMatchesFilenamePattern() {
		String resourcePattern = "meta-*.xml";
		
		Action action = mock(Action.class);
		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(action);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("meta-blah.xml");
		
		XmlChangedEventImpl event = new XmlChangedEventImpl(resource);
		
		rma.notify(event);
		
		verify(action).execute(event);
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("IRIS-blah.xml");
		
		XmlChangedEventImpl event2 = new XmlChangedEventImpl(resource2);
				
		rma.notify(event2);
		
		verify(action, never()).execute(event2);
	}
	
	@Test
	public void testMatchesEventType() {
		String resourcePattern = "meta-*.xml";
		
		Action changedAction = mock(Action.class);
		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(changedAction);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("meta-blah.xml");				
		
		XmlChangedEventImpl event = new XmlChangedEventImpl(resource);
				
		rma.notify(event);
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("meta-blah.xml");
		XmlChangedEventImpl event2 = new XmlChangedEventImpl(resource2);		
		
		rma.notify(event2);
		
		verify(changedAction, times(1)).execute(event);		
	}	
	
	@Test
	public void testNotify() {
		String resourcePattern = "meta-*.xml";
		
		Action changedAction = mock(Action.class);
		
		ResourceModificationAction rma = new ResourceModificationAction();
		rma.setResourcePattern(resourcePattern);
		rma.setChangedAction(changedAction);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("IRIS-blah.xml");
		
		XmlChangedEventImpl event = new XmlChangedEventImpl(resource);
		
		rma.notify(event);
		
		verify(changedAction, never()).execute(event);		
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("metadata-blah.xml");
		
		XmlChangedEventImpl event2 = new XmlChangedEventImpl(resource2);
		
		rma.notify(event2);
		
		verify(changedAction, never()).execute(event2);
	}
}
