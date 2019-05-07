package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.Result;
import com.temenos.useragent.generic.http.HttpHeader;

/**
 * Defines the data part of the http execution response.
 * 
 * @author ssethupathi
 *
 */
public interface ResponseData {

	/**
	 * Returns the header part of the response.
	 * 
	 * @return response header
	 */
	HttpHeader header();

	/**
	 * Returns the http execution result for the execution.
	 * 
	 * @return http execution result
	 */
	Result result();

	/**
	 * Returns the payload part of the response.
	 * 
	 * @return response payload
	 */
	Payload body();

}
