package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Collections;
import java.util.List;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.PayloadHandler;
import com.temenos.useragent.generic.internal.DefaultEntityWrapper;
import com.temenos.useragent.generic.internal.EntityHandler;
import com.temenos.useragent.generic.internal.EntityWrapper;

/**
 * A payload hanlder for <i>text/plain</i> media type.
 * 
 * @author ssethupathi
 *
 */
public class PlainTextPayloadHandler implements PayloadHandler {

	private String plainText = null;

	@Override
	public boolean isCollection() {
		return false;
	}

	@Override
	public List<Link> links() {
		return Collections.emptyList();
	}

	@Override
	public List<EntityWrapper> entities() {
		return Collections.emptyList();
	}

	@Override
	public EntityWrapper entity() {
		EntityWrapper plainTextWrapper = new DefaultEntityWrapper();
		EntityHandler handler = new PlainTextEntityHandler(plainText);
		plainTextWrapper.setHandler(handler);
		return plainTextWrapper;
	}

	@Override
	public void setPayload(String payload) {
		plainText = payload;
	}

	@Override
	public void setParameter(String parameter) {
		// parameter not handed yet
	}
}
