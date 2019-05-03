package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.useragent.generic.Result;

public class HttpResult implements Result {

	private int code;
	private String reason;

	public HttpResult(int statusCode, String reason) {
		this.code = statusCode;
		this.reason = reason;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String reason() {
		return reason;
	}
}
