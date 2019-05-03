package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.http.HttpHeader;

/**
 * Defines the data part of the http request.
 * 
 * @author ssethupathi
 *
 */
public interface RequestData {

	/**
	 * Returns the header part of the request.
	 * 
	 * @return header
	 */
	HttpHeader header();

	/**
	 * Returns the payload part of the request which is an entity.
	 * 
	 * @return payload entity
	 */
	EntityWrapper entity();
}
