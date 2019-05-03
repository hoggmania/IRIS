package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.useragent.generic.http.HttpHeader;

public class RequestDataImpl implements RequestData {

	private EntityWrapper entity;
	private HttpHeader header;

	public RequestDataImpl(HttpHeader header, EntityWrapper entity) {
		this.header = header;
		this.entity = entity;
	}

	@Override
	public HttpHeader header() {
		return header;
	}

	@Override
	public EntityWrapper entity() {
		return entity;
	}
}
