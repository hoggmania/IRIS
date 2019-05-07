package com.temenos.interaction.loader.xml.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.core.io.Resource;

import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.loader.xml.XmlChangedEventImpl;
import com.temenos.interaction.odataext.entity.MetadataOData4j;


/**
 * TODO: Document me!
 *
 * @author mlambert
 *
 */
public class TestIRISMetadataChangedAction {

	@Test
	public void test() {
		IRISMetadataChangedAction action = new IRISMetadataChangedAction();
		
		Metadata metadata = mock(Metadata.class);
		action.setMetadata(metadata);
		
		MetadataOData4j metadataOData4j = mock(MetadataOData4j.class);
		action.setMetadataOData4j(metadataOData4j);
		
		Resource resource = mock(Resource.class);
		when(resource.getFilename()).thenReturn("meta-test.xml");
		XmlChangedEventImpl event = new XmlChangedEventImpl(resource);
		
		action.execute(event);
		
		verify(metadata).unload("test");
		verify(metadataOData4j).unloadMetadata("test");
		
		Resource resource2 = mock(Resource.class);
		when(resource2.getFilename()).thenReturn("IRIS-test2.properties");
		XmlChangedEventImpl event2 = new XmlChangedEventImpl(resource2);
		
		action.execute(event2);
		
		verify(metadata, never()).unload("test2");
		verify(metadataOData4j, never()).unloadMetadata("test2");		
	}

}
