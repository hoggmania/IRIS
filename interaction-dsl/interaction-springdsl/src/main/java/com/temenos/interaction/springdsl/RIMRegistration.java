package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.rim.HTTPResourceInteractionModel;


/**
 * Implementations of this interface will provide a mechanism for registering resource interaction models 
 * (RIMs) with a web service container e.g. Apache Wink.
 *
 * @author mlambert
 *
 */
public interface RIMRegistration {

	/**
	 * Registers a resource interaction model (RIM) with a web service container
	 * 
	 * @param resource
	 * 				The resource to register
	 */
	void register(HTTPResourceInteractionModel resource);
}
