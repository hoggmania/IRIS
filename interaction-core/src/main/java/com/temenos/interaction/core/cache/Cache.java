package com.temenos.interaction.core.cache;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Interface to the response cache used by HttpHypermediaRIM
 *
 * @author amcguinness
 *
 */
public interface Cache {
	/** Insert into cache
	 * @param key declared as Object since it can be Uri, etc. toString() is used internally as key
	 * @param data
	 * @param maxAge in seconds
	 */
	public void put( Object key, javax.ws.rs.core.Response.ResponseBuilder data, int maxAge);
	public javax.ws.rs.core.Response.ResponseBuilder get( Object key );
}
