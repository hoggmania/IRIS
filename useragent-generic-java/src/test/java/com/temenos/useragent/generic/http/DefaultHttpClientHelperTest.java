package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.*;

import org.apache.http.HttpMessage;
import org.apache.http.client.methods.HttpGet;
import org.junit.Ignore;
import org.junit.Test;

import com.temenos.useragent.generic.http.DefaultHttpClientHelper;
import com.temenos.useragent.generic.http.HttpHeader;
import com.temenos.useragent.generic.http.HttpRequest;
import com.temenos.useragent.generic.http.HttpRequestImpl;

public class DefaultHttpClientHelperTest {

	@Test
	public void testBuildRequestHeaders() {
		HttpHeader requestHeader = new HttpHeader();
		requestHeader.set("Content-Type", "application/atom+xml");
		HttpRequest request = new HttpRequestImpl(requestHeader);
		HttpMessage message = new HttpGet();
		DefaultHttpClientHelper.buildRequestHeaders(request, message);
		assertEquals("application/atom+xml",
				message.getFirstHeader("Content-Type").getValue());
	}

	@Test
	public void testRemoveParameter() {
		String contentType = "foo/bar";
		assertEquals("foo/bar",
				DefaultHttpClientHelper.removeParameter(contentType));
		contentType = "foo/bar;";
		assertEquals("foo/bar",
				DefaultHttpClientHelper.removeParameter(contentType));
		contentType = "foo/bar;a=b";
		assertEquals("foo/bar",
				DefaultHttpClientHelper.removeParameter(contentType));
		contentType = "";
		assertEquals("", DefaultHttpClientHelper.removeParameter(contentType));
	}

	@Test
	public void testExtractParameter() {
		String contentType = "foo/bar";
		assertEquals("", DefaultHttpClientHelper.extractParameter(contentType));
		contentType = "foo/bar;";
		assertEquals("", DefaultHttpClientHelper.extractParameter(contentType));
		contentType = "foo/bar;a=b";
		assertEquals("a=b",
				DefaultHttpClientHelper.extractParameter(contentType));
		contentType = "";
		assertEquals("", DefaultHttpClientHelper.extractParameter(contentType));
	}
}
