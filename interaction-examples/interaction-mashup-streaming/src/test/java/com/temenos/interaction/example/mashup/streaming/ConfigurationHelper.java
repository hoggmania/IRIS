package com.temenos.interaction.example.mashup.streaming;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class ConfigurationHelper {

	private final static String TEST_ENDPOINT_URI_KEY = "TEST_ENDPOINT_URI";
	
	public static String getTestEndpointUri(String defaultUri) {
		String uri = defaultUri;
		if (System.getProperty(TEST_ENDPOINT_URI_KEY) != null)
			uri = System.getProperty(TEST_ENDPOINT_URI_KEY);
		assert(uri != null);
		return uri;
	}
}
