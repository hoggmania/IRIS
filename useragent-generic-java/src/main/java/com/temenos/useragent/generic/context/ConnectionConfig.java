package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Defines configuration required for the connection used in the interaction.
 * 
 * @author ssethupathi
 *
 */
public interface ConnectionConfig {

	public static final String ENDPOINT_URI = "URI";
	public static final String USER_NAME = "USERNAME";
	public static final String PASS_WORD = "PASSWORD";
	public static final String SERVICE_ROOT = "COMPANY";

	/**
	 * Gets the value for a given property name.
	 * 
	 * @param propertyName
	 * @return value
	 */
	public String getValue(String propertyName);
}
