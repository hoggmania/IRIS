package com.temenos.interaction.loader.xml.resource.notification;

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

import com.temenos.interaction.loader.xml.XmlChangedEventImpl;
import com.temenos.interaction.loader.xml.resource.action.ResourceModificationAction;

public class TestXmlModificationNotifier {

	@Test
	public void testExecute() {
		XmlModificationNotifier rmn = new XmlModificationNotifier();
		
		Map<String, ResourceModificationAction> map = new HashMap<String, ResourceModificationAction>();
		ResourceModificationAction rma = mock(ResourceModificationAction.class);
		map.put("a", rma);
		
		ResourceModificationAction rma2 = mock(ResourceModificationAction.class);		
		map.put("b", rma2);
		
		ApplicationContext ctx = mock(ApplicationContext.class);
		when(ctx.getBeansOfType(ResourceModificationAction.class)).thenReturn(map);
		rmn.setApplicationContext(ctx);
		
		rmn.getPatterns(); // Simulate registration of the notifier with IRIS
		
		XmlChangedEventImpl event = mock(XmlChangedEventImpl.class);
		rmn.execute(event);
		
		verify(rma).notify(event);
		verify(rma2).notify(event);		
	}

	@Test
	public void testGetPattern() {
		XmlModificationNotifier rmn = new XmlModificationNotifier();
		
		Map<String, ResourceModificationAction> map = new HashMap<String, ResourceModificationAction>();
		ResourceModificationAction rma = mock(ResourceModificationAction.class);
		when(rma.getResourcePattern()).thenReturn("classpath*:meta-*.xml");		
		map.put("a", rma);
		
		ResourceModificationAction rma2 = mock(ResourceModificationAction.class);		
		map.put("b", rma2);
		when(rma2.getResourcePattern()).thenReturn("classpath*:MyFile.properties");		
		
		ApplicationContext ctx = mock(ApplicationContext.class);
		when(ctx.getBeansOfType(ResourceModificationAction.class)).thenReturn(map);
		rmn.setApplicationContext(ctx);
		
		Set<String> patterns = rmn.getPatterns();
				
		assertTrue(patterns.contains("classpath*:MyFile.properties"));
		assertTrue(patterns.contains("classpath*:meta-*.xml"));
	}
}
