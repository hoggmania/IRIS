package com.temenos.interaction.core.rim;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Map;

import javax.ws.rs.core.HttpHeaders;

import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.hypermedia.Transition;
import com.temenos.interaction.core.resource.EntityResource;

public interface ResourceRequestHandler {
	
	/**
	 * 
	 * @param rimHandler the main handler for our resource requests
	 * @param headers HttpHeaders
	 * @param ctx our InteractionContext
	 * @param resource the request body (POST, PUT)
	 * @param config the resources we want, and how shall we process them
	 * @return
	 */
	public Map<Transition, ResourceRequestResult> getResources(HTTPHypermediaRIM rimHandler, HttpHeaders headers, InteractionContext ctx, EntityResource<?> resource, ResourceRequestConfig config);
}
