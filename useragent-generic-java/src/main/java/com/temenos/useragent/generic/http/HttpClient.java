package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Defines the Http client used for the interactions.
 * 
 * @author ssethupathi
 *
 */
public interface HttpClient {

	/**
	 * Http GET method executes {@link HttpRequest request}.
	 * 
	 * @param url
	 * @param request
	 * @return response
	 */
	HttpResponse get(String url, HttpRequest request);

	/**
	 * Http POST method executes {@link HttpRequest request}.
	 * 
	 * @param url
	 * @param request
	 * @return response
	 */

	HttpResponse post(String url, HttpRequest request);

	/**
	 * Http PUT method executes {@link HttpRequest request}.
	 * 
	 * @param url
	 * @param request
	 * @return response
	 */
	HttpResponse put(String url, HttpRequest request);
	
	/**
	 * HTTP DELETE method executes {@link HttpRequest request}.
	 * @param url
	 * @param request
	 * @return response
	 */
	HttpResponse delete(String url, HttpRequest request);
}
