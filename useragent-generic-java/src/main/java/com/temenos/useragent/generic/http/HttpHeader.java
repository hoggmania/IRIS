package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HttpHeader {

	private Map<String, String> headers = new HashMap<String, String>();

	public Collection<String> names() {
		return headers.keySet();
	}

	public void set(String name, String value) {
		headers.put(name, value);
	}

	public String get(String name) {
		return headers.get(name);
	}

	public String toString() {
		return headers.toString();
	}
}
