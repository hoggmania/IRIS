package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.http.HttpClient;
import com.temenos.useragent.generic.http.HttpHeader;

/**
 * Defines the context which runs through the interaction session.
 * 
 * @author ssethupathi
 *
 */
public interface SessionContext {

	/**
	 * Returns the http header to use in the request.
	 * 
	 * @return http header
	 */
	HttpHeader getRequestHeader();

	/**
	 * Returns the http client to be used for executing the request.
	 * 
	 * @return http client
	 */
	HttpClient getHttpClient();

	/**
	 * Returns the entity to use in the request or null of no entity to be used
	 * in the request.
	 * 
	 * @return entity
	 */
	EntityWrapper getRequestEntity();

	/**
	 * Sets the response data after having got the request executed.
	 * 
	 * @param response
	 */
	void setResponse(ResponseData response);

	/**
	 * Returns the response or null if no response available.
	 * 
	 * @return response.
	 */
	ResponseData getResponse();

}
