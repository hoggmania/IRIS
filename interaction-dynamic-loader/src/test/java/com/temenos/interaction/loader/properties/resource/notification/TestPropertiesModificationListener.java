package com.temenos.interaction.loader.properties.resource.notification;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.temenos.interaction.core.loader.PropertiesResourceModificationAction;
import com.temenos.interaction.loader.properties.PropertiesChangedEventImpl;
import com.temenos.interaction.loader.properties.PropertiesLoadedEventImpl;
import com.temenos.interaction.loader.properties.resource.action.ResourceModificationAction;

public class TestPropertiesModificationListener {

	@Test
	public void testGetPattern() {
		PropertiesModificationNotifier rmn = new PropertiesModificationNotifier();
		
		Map<String, PropertiesResourceModificationAction> map = new TreeMap<String, PropertiesResourceModificationAction>();
		ResourceModificationAction rma = mock(ResourceModificationAction.class);
		when(rma.getResourcePattern()).thenReturn("classpath*:IRIS-*.properties");		
		map.put("a", rma);
		
		ResourceModificationAction rma2 = mock(ResourceModificationAction.class);		
		map.put("b", rma2);
		when(rma2.getResourcePattern()).thenReturn("classpath*:MyFile.xml");		
		
		ApplicationContext ctx = mock(ApplicationContext.class);
		when(ctx.getBeansOfType(PropertiesResourceModificationAction.class)).thenReturn(map);
		rmn.setApplicationContext(ctx);
		
		PropertiesModificationListener listener = new PropertiesModificationListener();
		listener.setNotifier(rmn);
		
		List<String> patterns = Arrays.asList(listener.getResourcePatterns());
		
		assertTrue(patterns.contains("classpath*:IRIS-*.properties"));
		assertTrue(patterns.contains("classpath*:MyFile.xml"));
	}
	
	@Test
	public void testExecute() {
		PropertiesModificationListener listener = new PropertiesModificationListener();		
		PropertiesModificationNotifier notifier = mock(PropertiesModificationNotifier.class);
		listener.setNotifier(notifier);
		
		// Spoof app ctx initialization
		listener.onApplicationEvent(null);
		
		PropertiesChangedEventImpl changedEvent = mock(PropertiesChangedEventImpl.class);
		listener.propertiesChanged(changedEvent);
		
		PropertiesLoadedEventImpl loadedEvent = mock(PropertiesLoadedEventImpl.class);
		listener.propertiesChanged(loadedEvent);
						
		verify(notifier, times(1)).execute(loadedEvent);		
		verify(notifier, times(1)).execute(changedEvent);		
	}
}
