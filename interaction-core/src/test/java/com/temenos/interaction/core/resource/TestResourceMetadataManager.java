package com.temenos.interaction.core.resource;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.temenos.interaction.core.entity.Metadata;
import com.temenos.interaction.core.hypermedia.ResourceStateMachine;

public class TestResourceMetadataManager {

	@Test
	public void testConstructor() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("AirlinesMetadata.xml");
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, "UTF-8");
		String xml = writer.toString();
		ResourceStateMachine stateMachine = mock(ResourceStateMachine.class);
		ResourceMetadataManager mdProducer = new ResourceMetadataManager(xml, stateMachine);
		Metadata metadata = mdProducer.getMetadata();
		assertNotNull(metadata);
	}
	
	@Test
	public void testCurrencyList() throws Exception {
		ResourceMetadataManager mdProducer = new ResourceMetadataManager();
		Metadata metadata = mdProducer.getMetadata("CountryList");
		assertNotNull(metadata);
	}

	@Test
	public void testCustomerInfo() throws Exception {
		ResourceMetadataManager mdProducer = new ResourceMetadataManager();
		Metadata metadata = mdProducer.getMetadata("CustomerInfo");
		assertNotNull(metadata);
	}
}
