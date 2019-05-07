package com.temenos.interaction.core.rim;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.resource.RESTResource;

public class ResourceRequestResult {

	private final int status;
	private final RESTResource resource;
	
	public ResourceRequestResult(int status, RESTResource resource) {
		super();
		this.status = status;
		this.resource = resource;
	}

	public int getStatus() {
		return status;
	}
	public RESTResource getResource() {
		return resource;
	}
	
}
