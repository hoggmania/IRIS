package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.useragent.generic.PayloadHandler;

/**
 * Factory to supply {@link PayloadHandler payload handlers} for supported media
 * types.
 * <p>
 * Payload handlers implementing {@link PayloadHandler} are registered against
 * the media types they support in a registry and available for handling payload
 * dynamically.
 * </p>
 * TODO support for non string type/generic payloads
 * 
 * @author ssethupathi
 *
 */
public class PayloadHandlerFactory<T extends PayloadHandler> {
	private final Class<? extends PayloadHandler> handlerClass;

	private PayloadHandlerFactory(
			final Class<? extends PayloadHandler> handlerType) {
		this.handlerClass = handlerType;
	}

	/**
	 * Creates the factory for a {@link PayloadHandler payload handler} type.
	 * 
	 * @param handlerClass
	 * @return Payload hander factory
	 */
	public static <T extends PayloadHandler> PayloadHandlerFactory<T> createFactory(
			final Class<? extends PayloadHandler> handlerClass) {
		if (handlerClass == null) {
			throw new IllegalArgumentException(
					"Illegal payload handler class 'null'");
		}
		return new PayloadHandlerFactory<T>(handlerClass);
	}

	/**
	 * Creates the appropriate {@link PayloadHandler payload handler} for the
	 * payload/
	 * 
	 * @param payload
	 * @return payload handler
	 */
	public PayloadHandler createHandler(String payload) {
		try {
			PayloadHandler handler = handlerClass.newInstance();
			handler.setPayload(payload);
			return handler;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}
}
