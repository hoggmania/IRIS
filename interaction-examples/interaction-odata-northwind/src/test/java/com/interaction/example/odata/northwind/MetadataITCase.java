package com.interaction.example.odata.northwind;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.*;

import org.junit.Test;

public class MetadataITCase extends AbstractNorthwindRuntimeTest {

	public MetadataITCase(RuntimeFacadeType type) {
		super(type);
	}

	@Test
	public void testDefaultXmlContent() {

		String xmlResult = this.rtFacade.getWebResource(endpointUri + "$metadata", "application/xml");
		assertTrue(xmlResult.contains("<?xml version='1.0' encoding='utf-8'?>"));
		String htmlResult = this.rtFacade.getWebResource(endpointUri + "$metadata", "text/html");
		assertTrue(htmlResult.contains("<?xml version='1.0' encoding='utf-8'?>"));
		String xhtmlResult = this.rtFacade.getWebResource(endpointUri + "$metadata", "application/xhtml+xml");
		assertTrue(xhtmlResult.contains("<?xml version='1.0' encoding='utf-8'?>"));
		
	}

}
