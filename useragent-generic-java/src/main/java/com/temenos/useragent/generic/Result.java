package com.temenos.useragent.generic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Defines the HTTP interaction result.
 * 
 * @author ssethupathi
 *
 */
public interface Result {

	/**
	 * Returns Http status code
	 * 
	 * @return http status code
	 */
	public int code();

	/**
	 * Returns Http status reason
	 * 
	 * @return reason
	 */
	public String reason();

}