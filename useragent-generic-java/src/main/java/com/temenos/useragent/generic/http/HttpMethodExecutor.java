package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.internal.ResponseData;

/**
 * Defines an executor for HTTP methods.
 * 
 * @author ssethupathi
 *
 */
public interface HttpMethodExecutor {

	/**
	 * Executes a {@link HttpMethod http method} and returs response.
	 * 
	 * @param method
	 *            http method
	 * @return http response data
	 */
	ResponseData execute(HttpMethod method);
}
