package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.useragent.generic.mediatype.AtomPayloadHandler;
import com.temenos.useragent.generic.mediatype.HalJsonPayloadHandler;
import com.temenos.useragent.generic.mediatype.JsonPayloadHandler;
import com.temenos.useragent.generic.mediatype.PlainTextPayloadHandler;

/**
 * Factory for accessing the {@link Context context}.
 * 
 * @author ssethupathi
 *
 */
public class ContextFactory {

	private static ThreadLocal<ContextFactory> currentContextFactory = new ThreadLocal<ContextFactory>() {
		@Override
		protected ContextFactory initialValue() {
			return new ContextFactory();
		}
	};

	private ContextImpl context;

	private ContextFactory() {
		BaseConnectionConfig baseConnConfig = new BaseConnectionConfig();
		SystemConnectionConfig sysConnConfig = new SystemConnectionConfig(
				baseConnConfig);
		context = new ContextImpl(sysConnConfig);
	}

	/**
	 * Returns the {@link ContextFactory context factory} associated to the
	 * current thread.
	 * 
	 * @return context factory
	 */
	public static ContextFactory get() {
		return currentContextFactory.get();
	}

	/**
	 * Sets the connection property for the session.
	 * 
	 * @param name
	 * @param value
	 */
	public void setConnectionProperty(String name, String value) {
		context.setSessionProperty(name, value);
	}

	/**
	 * Returns the execution {@link Context context}.
	 * 
	 * @return context
	 */
	public Context getContext() {
		return context;
	}

	public static class ContextImpl implements Context {
		private SessionConnectionConfig connectionConfig;
		private ContentTypeHandlers registry = new ContentTypeHandlers();
		private ContextImpl(ConnectionConfig connConfig) {
			this.connectionConfig = new SessionConnectionConfig(connConfig);
		}

		@Override
		public ConnectionConfig connectionCongfig() {
			return connectionConfig;
		}

		@Override
		public ContentTypeHandlers entityHandlersRegistry() {

			registry.registerForPayloadifMissing("application/atom+xml",
					AtomPayloadHandler.class);
			registry.registerForPayloadifMissing("text/plain", PlainTextPayloadHandler.class);
			registry.registerForPayloadifMissing("text/html", PlainTextPayloadHandler.class);
			registry.registerForPayloadifMissing("", PlainTextPayloadHandler.class);
			registry.registerForPayloadifMissing("application/hal+json", HalJsonPayloadHandler.class);
			registry.registerForPayloadifMissing("application/json", JsonPayloadHandler.class);
			return registry;
		}

		private void setSessionProperty(String name, String value) {
			connectionConfig.setValue(name, value);
		}
	}
}
