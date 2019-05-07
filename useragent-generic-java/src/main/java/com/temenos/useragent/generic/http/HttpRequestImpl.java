package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class HttpRequestImpl implements HttpRequest {

	private HttpHeader header;
	private String payload;

	public HttpRequestImpl(HttpHeader header, String payload) {
		this.header = header;
		this.payload = payload;
	}

	public HttpRequestImpl(HttpHeader header) {
		this.header = header;
		this.payload = "";
	}

	@Override
	public HttpHeader headers() {
		return header;
	}

	@Override
	public String payload() {
		return payload;
	}

	@Override
	public String toString() {
		return "PayloadRequest [header=" + header + ", payload=" + payload
				+ "]";
	}
}
