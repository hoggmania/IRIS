package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Factory creating {@link HttpClient http client} instances.
 * 
 * @author ssethupathi
 *
 */
public class HttpClientFactory {

	public static HttpClient newClient() {
		return new DefaultHttpClient();
	}
}
