package com.temenos.useragent.generic.context;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.HashMap;
import java.util.Map;

import com.temenos.useragent.generic.PayloadHandler;
import com.temenos.useragent.generic.internal.PayloadHandlerFactory;

/**
 * This class contains the content handlers for the registered media types.
 * 
 * @author ssethupathi
 *
 */
public class ContentTypeHandlers {

	private Map<String, PayloadHandlerFactory<? extends PayloadHandler>> payloadHandlersFactory;

	public ContentTypeHandlers() {
		this.payloadHandlersFactory = new HashMap<String, PayloadHandlerFactory<? extends PayloadHandler>>();
	}

	public void registerForPayload(String contentType,
			final Class<? extends PayloadHandler> transformerClass) {
		payloadHandlersFactory.put(contentType,
				PayloadHandlerFactory.createFactory(transformerClass));
	}

    public void registerForPayloadifMissing(String contentType, final Class<? extends PayloadHandler> transformerClass) {
        if(!payloadHandlersFactory.containsKey(contentType)){
            registerForPayload(contentType, transformerClass);
        }
    }

	public PayloadHandlerFactory<? extends PayloadHandler> getPayloadHandlerFactory(
			String contentType) {
		return payloadHandlersFactory.get(contentType);
	}
}
