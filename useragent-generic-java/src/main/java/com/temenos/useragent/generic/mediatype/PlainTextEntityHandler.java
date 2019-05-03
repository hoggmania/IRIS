package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.internal.EntityHandler;
import com.temenos.useragent.generic.internal.Payload;

/**
 * An entity handler implementation for <i>text/plain</i> media type.
 * 
 * @author ssethupathi
 *
 */
public class PlainTextEntityHandler implements EntityHandler {

	private String plainText;

	public PlainTextEntityHandler(String plainText) {
		this.plainText = plainText;
	}

	@Override
	public String getId() {
		return "";
	}

	@Override
	public List<Link> getLinks() {
		return Collections.emptyList();
	}

	@Override
	public String getValue(String fqPropertyName) {
		return "";
	}

	@Override
	public <T> void setValue(String fqPropertyName, T value) {
		// do nothing
	}
	
	@Override
	public void remove(String fqPropertyName) {
		// do nothing
	}
	
	@Override
	public Payload embedded() {
		return null;
	}
	
	@Override
	public int getCount(String fqPropertyName) {
		return 0;
	}

	@Override
	public void setContent(InputStream stream) {
		// do nothing
	}

	@Override
	public InputStream getContent() {
		return IOUtils.toInputStream(plainText);
	}
}
