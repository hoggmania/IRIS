package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.Url;

/**
 * An actionable link with the {@link SessionContext} which would let the user
 * agent follow by executing a http method on it.
 * 
 * @author ssethupathi
 *
 */
public class LinkWrapper implements ActionableLink {

	private Link link;
	private SessionContext sessionCallback;

	public LinkWrapper(Link link, SessionContext sessionCallback) {
		this.link = link;
		this.sessionCallback = sessionCallback;
	}

	@Override
	public String title() {
		return link.title();
	}

	@Override
	public String href() {
		return link.href();
	}

	@Override
	public String rel() {
		return link.rel();
	}

	@Override
	public String id() {
		return link.id();
	}
	
	@Override
	public String description() {
		return link.description();
	}

	@Override
	public boolean hasEmbeddedPayload() {
		return link.hasEmbeddedPayload();
	}

	@Override
	public Payload embedded() {
		return link.embedded();
	}

	@Override
	public String baseUrl() {
		return link.baseUrl();
	}

	@Override
	public Url url() {
		return new UrlWrapper(baseUrl() + href(), sessionCallback);
	}
}
