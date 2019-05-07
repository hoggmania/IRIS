package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.Link;

/**
 * Implements a hypermedia link.
 * 
 * @author ssethupathi
 *
 */
public class LinkImpl implements Link {

	private String baseUrl;
	private String rel;
	private String href;
	private String title;
	private String id;
	private String desc;
	private boolean hasEmbeddedPayload;
	private Payload embeddedPayload;

	private LinkImpl(Builder builder) {
		this.baseUrl = builder.baseUrl == null ? "" : builder.baseUrl;
		this.rel = builder.rel == null ? "" : builder.rel;
		this.href = builder.href == null ? "" : builder.href;
		this.title = builder.title == null ? "" : builder.title;
		this.id = builder.id == null ? "" : builder.id;
		this.desc = builder.desc == null ? "" : builder.desc;
		this.embeddedPayload = builder.embeddedPayload;
		this.hasEmbeddedPayload = this.embeddedPayload == null ? false : true;
	}

	@Override
	public String href() {
		return href;
	}

	@Override
	public boolean hasEmbeddedPayload() {
		return hasEmbeddedPayload;
	}

	@Override
	public Payload embedded() {
		return embeddedPayload;
	}

	@Override
	public String rel() {
		return rel;
	}

	@Override
	public String baseUrl() {
		return baseUrl;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public String id() {
		return id;
	}
	
	@Override
	public String description() {
		return desc;
	}

	@Override
	public String toString() {
		return "LinkImpl [rel=" + rel + ", href=" + href + ", title=" + title
				+ ", baseUrl=" + baseUrl + ", hasEmbeddedPayload="
				+ hasEmbeddedPayload + "]";
	}

	public static class Builder {
		private String id = "";
		private String rel = "";
		private String title = "";
		private String href = "";
		private String desc = "";
		private String baseUrl = "";
		private Payload embeddedPayload;

		public Builder(String href) {
			this.href = href;
		}

		public Builder rel(String rel) {
			this.rel = rel;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder baseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder description(String description) {
			this.desc = description;
			return this;
		}		

		public Builder payload(Payload payload) {
			this.embeddedPayload = payload;
			return this;
		}

		public Link build() {
			return new LinkImpl(this);
		}
	}
}
