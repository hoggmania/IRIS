package com.temenos.interaction.commands.mule;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.ws.rs.core.MultivaluedMap;

public class ViewCommandWrapper {

	private final MultivaluedMap<String, String> pathParams;
	private final MultivaluedMap<String, String> queryParams;
	
	public ViewCommandWrapper(MultivaluedMap<String, String> pathParams, MultivaluedMap<String, String> queryParams) {
		this.pathParams = pathParams;
		this.queryParams = queryParams;
	}
	
	public MultivaluedMap<String, String> getPathParameters() {
		return pathParams;
	}
	
	public MultivaluedMap<String, String> getQueryParameters() {
		return queryParams;
	}
}
