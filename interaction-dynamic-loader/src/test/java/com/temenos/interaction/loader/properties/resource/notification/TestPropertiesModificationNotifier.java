package com.temenos.interaction.loader.properties.resource.notification;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.core.loader.PropertiesResourceModificationAction;
import com.temenos.interaction.loader.properties.resource.action.ResourceModificationAction;

public class TestPropertiesModificationNotifier {

	@Test
	public void testExecute() {
		PropertiesModificationNotifier rmn = new PropertiesModificationNotifier();
		
		Map<String, PropertiesResourceModificationAction> map = new HashMap<String, PropertiesResourceModificationAction>();
		ResourceModificationAction rma = mock(ResourceModificationAction.class);
		map.put("a", rma);
		
		ResourceModificationAction rma2 = mock(ResourceModificationAction.class);		
		map.put("b", rma2);
		
		ApplicationContext ctx = mock(ApplicationContext.class);
		when(ctx.getBeansOfType(PropertiesResourceModificationAction.class)).thenReturn(map);
		rmn.setApplicationContext(ctx);
		
		rmn.getPatterns(); // Simulate registration of the notifier with IRIS
		
		PropertiesEvent event = mock(PropertiesEvent.class);
		rmn.execute(event);
		
		verify(rma).notify(event);
		verify(rma2).notify(event);		
	}

	@Test
	public void testGetPattern() {
		PropertiesModificationNotifier rmn = new PropertiesModificationNotifier();
		
		Map<String, PropertiesResourceModificationAction> map = new HashMap<String, PropertiesResourceModificationAction>();
		ResourceModificationAction rma = mock(ResourceModificationAction.class);
		when(rma.getResourcePattern()).thenReturn("classpath*:IRIS-*.properties");		
		map.put("a", rma);
		
		ResourceModificationAction rma2 = mock(ResourceModificationAction.class);		
		map.put("b", rma2);
		when(rma2.getResourcePattern()).thenReturn("classpath*:MyFile.xml");		
		
		ApplicationContext ctx = mock(ApplicationContext.class);
		when(ctx.getBeansOfType(PropertiesResourceModificationAction.class)).thenReturn(map);
		rmn.setApplicationContext(ctx);
		
		Set<String> patterns = rmn.getPatterns();
				
		assertTrue(patterns.contains("classpath*:MyFile.xml"));
		assertTrue(patterns.contains("classpath*:IRIS-*.properties"));
	}
}
