package com.temenos.useragent.generic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

import com.temenos.useragent.generic.context.ConnectionConfig;
import com.temenos.useragent.generic.context.ContextFactory;
import com.temenos.useragent.generic.http.HttpClient;
import com.temenos.useragent.generic.http.HttpClientFactory;
import com.temenos.useragent.generic.http.HttpHeader;
import com.temenos.useragent.generic.internal.EntityWrapper;
import com.temenos.useragent.generic.internal.NullEntityWrapper;
import com.temenos.useragent.generic.internal.Payload;
import com.temenos.useragent.generic.internal.ResponseData;
import com.temenos.useragent.generic.internal.SessionContext;
import com.temenos.useragent.generic.internal.UrlWrapper;

public class DefaultInteractionSession implements InteractionSession {

	private HttpHeader header;
	private EntityWrapper entity;
	private SessionContextImpl sessionContext;
	private HttpClient httpClient;

	@Override
	public Url url(String url) {
		return new UrlWrapper(url, sessionContext);
	}

	@Override
	public Url url() {
		return new UrlWrapper(sessionContext);
	}

	@Override
	public InteractionSession registerHandler(String contentType,
			Class<? extends PayloadHandler> handler) {
		ContextFactory.get().getContext().entityHandlersRegistry()
				.registerForPayload(contentType, handler);
		return this;
	}

	@Override
	public InteractionSession header(String name, String... values) {
		header.set(name, values[0]);
		return this;
	}

    @Override
    public <T> InteractionSession set(String propertyName, T propertyValue) {
        entity.set(propertyName, propertyValue);
        return this;
    }

	@Override
	public InteractionSession remove(String propertyName) {
		entity.remove(propertyName);
		return this;
	}

	@Override
	public Entities entities() {
		Payload response = sessionContext.getResponse().body();
		if (response.isCollection()) {
			List<EntityWrapper> entitiesWithSessionContext = new ArrayList<EntityWrapper>();
			for (EntityWrapper entity : response.entities()) {
				entity.setSessionContext(sessionContext);
				entitiesWithSessionContext.add(entity);
			}
			return new Entities(entitiesWithSessionContext);
		} else {
			EntityWrapper entity = response.entity();
			entity.setSessionContext(sessionContext);
			return new Entities(entity);
		}
	}

	@Override
	public InteractionSession reuse() {
		entity = sessionContext.getResponse().body().entity();
		entity.setSessionContext(sessionContext);
		return this;
	}

	@Override
	public InteractionSession clear() {
		initialiseToDefaults();
		return this;
	}

	@Override
	public Result result() {
		return sessionContext.getResponse().result();
	}

	@Override
	public String header(String name) {
		return sessionContext.getResponse().header().get(name);
	}

	@Override
	public Links links() {
		return Links.create(payloadLinks(), sessionContext);
	}

	@Override
	public InteractionSession basicAuth(String username, String password) {
		ContextFactory factory = ContextFactory.get();
		factory.setConnectionProperty(ConnectionConfig.USER_NAME, username);
		factory.setConnectionProperty(ConnectionConfig.PASS_WORD, password);
		return this;
	}

	@Override
	public InteractionSession use(EntityWrapper entity) {
		this.entity = entity;
		this.entity.setSessionContext(sessionContext);
		return this;
	}

	/**
	 * Creates and returns a new {@link InteractionSession interaction session}
	 * instance.
	 * 
	 * @return interaction session
	 */
	public static InteractionSession newSession() {
		return new DefaultInteractionSession();
	}

	private DefaultInteractionSession() {
		initialiseToDefaults();
	}

	private List<Link> payloadLinks() {
		return sessionContext.getResponse().body().links();
	}

	private void initialiseToDefaults() {
		header = new HttpHeader();
		entity = new NullEntityWrapper();
		sessionContext = new SessionContextImpl(this);
		httpClient = HttpClientFactory.newClient();
	}

	private static class SessionContextImpl implements SessionContext {

		private DefaultInteractionSession parent;
		private ResponseData output;

		private SessionContextImpl(DefaultInteractionSession parent) {
			this.parent = parent;
		}

		@Override
		public void setResponse(ResponseData output) {
			this.output = output;
		}

		public ResponseData getResponse() {
			if (output != null) {
				return output;
			} else {
				throw new IllegalStateException(
						"No response for any interactions found");
			}
		}

		@Override
		public HttpHeader getRequestHeader() {
			return parent.header;
		}

		@Override
		public EntityWrapper getRequestEntity() {
			return parent.entity;
		}

		@Override
		public HttpClient getHttpClient() {
			return parent.httpClient;
		}
	}

	@Override
	public void useHttpClient(HttpClient httpClient) {
		if (httpClient == null) {
			throw new IllegalArgumentException("HttpClient is null");
		}
		this.httpClient = httpClient;
	}

}
