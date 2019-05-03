package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.temenos.useragent.generic.Links;

public class NullEntityWrapper implements EntityWrapper {

	@Override
	public String id() {
		return "";
	}

	@Override
	public String get(String fqName) {
		return "";
	}

	@Override
	public int count(String fqName) {
		return 0;
	}

	@Override
	public Links links() {
		return Links.empty();
	}

	@Override
	public void setHandler(EntityHandler handler) {
		throw new IllegalStateException("Unexpected method call");
	}

	@Override
	public void setSessionContext(SessionContext sessionCallback) {
		throw new IllegalStateException("Unexpected method call");
	}

	@Override
	public <T> void set(String fqPropertyName, T value) {
		throw new IllegalStateException("Unexpected method call");
	}

	@Override
	public void remove(String fqPropertyName) {
		throw new IllegalStateException("Unexpected method call");
	}
	
	@Override
	public Payload embedded() {
		throw new IllegalStateException("Unexpected method call");
	}

	@Override
	public InputStream getContent() {
		try {
			return IOUtils.toInputStream("", "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
