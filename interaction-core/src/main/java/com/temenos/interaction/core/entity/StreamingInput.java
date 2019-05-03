package com.temenos.interaction.core.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import java.io.InputStream;

import javax.ws.rs.core.MultivaluedMap;


public class StreamingInput {
	private String entityName;
	private InputStream input;
	private MultivaluedMap<String, String> headers;

	/**
	 * @param entityName
	 * @param headers 
	 * @param parts 
	 */
	public StreamingInput(String entityName, InputStream input, MultivaluedMap<String, String> headers) {
		this.entityName = entityName;
		this.input = input;
		this.headers = headers;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @return the input
	 */
	public InputStream getInput() {
		return input;
	}

	/**
	 * @return the headers
	 */
	public MultivaluedMap<String, String> getHeaders() {
		return headers;
	}
}
