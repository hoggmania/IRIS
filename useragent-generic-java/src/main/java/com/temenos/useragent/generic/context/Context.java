package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * Defines the interaction execution context.
 * 
 * @author ssethupathi
 *
 */
public interface Context {

	/**
	 * Returns the connection configuration.
	 * 
	 * @return connection configuration
	 */
	ConnectionConfig connectionCongfig();

	/**
	 * Returns the registry for content handlers.
	 * 
	 * @return content handlers.
	 */
	ContentTypeHandlers entityHandlersRegistry();
}
