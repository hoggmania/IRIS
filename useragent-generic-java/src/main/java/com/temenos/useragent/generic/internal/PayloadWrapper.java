package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.PayloadHandler;

/**
 * Defines a wrapper to {@link Payload payload} with a handler.
 * 
 * @author ssethupathi
 *
 */
public interface PayloadWrapper extends Payload {

	/**
	 * Sets the appropriate handler for the payload.
	 * 
	 * @param handler
	 */
	void setHandler(PayloadHandler handler);
}
