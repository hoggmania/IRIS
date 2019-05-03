package com.temenos.useragent.generic.internal;

import static com.temenos.useragent.generic.internal.EntityWrapper.checkValueforPrimitiveorString;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.Links;

public class DefaultEntityWrapper implements EntityWrapper {

	private List<Link> namedLinks;
	private EntityHandler entityHandler;
	private SessionContext sessionCallback;

	public DefaultEntityWrapper() {
	}

	@Override
	public String id() {
		return entityHandler.getId();
	}

	@Override
	public String get(String fqName) {
		return entityHandler.getValue(fqName);
	}

	@Override
	public int count(String fqName) {
		return entityHandler.getCount(fqName);
	}

	@Override
	public Links links() {
		if (sessionCallback == null) {
			return Links.empty();
		} else {
			return Links.create(entityLinks(), sessionCallback);
		}
	}
	
	@Override
	public Payload embedded() {
		return entityHandler.embedded();
	}

	private List<Link> entityLinks() {
		checkAndBuildLinks();
		return namedLinks;
	}

	private void checkAndBuildLinks() {
		if (namedLinks != null) {
			return;
		}
		List<Link> links = entityHandler.getLinks();
		namedLinks = new ArrayList<Link>();
		for (Link link : links) {
			namedLinks.add(link);
		}
	}

	@Override
	public InputStream getContent() {
		return entityHandler.getContent();
	}

	@Override
	public <T> void set(String fqPropertyName, T value) {
	    checkValueforPrimitiveorString(value);
		entityHandler.setValue(fqPropertyName, value);
	}

	@Override
	public void remove(String fqPropertyName) {
		entityHandler.remove(fqPropertyName);
	}

	@Override
	public void setHandler(EntityHandler transformer) {
		this.entityHandler = transformer;
	}

	@Override
	public void setSessionContext(SessionContext sessionCallback) {
		this.sessionCallback = sessionCallback;
	}
}
