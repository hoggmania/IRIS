package com.temenos.useragent.generic.internal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.ArrayList;
import java.util.List;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.PayloadHandler;

public class DefaultPayloadWrapper implements PayloadWrapper {

	private PayloadHandler transformer;
	private List<EntityWrapper> entities;

	public DefaultPayloadWrapper() {
	}

	@Override
	public List<Link> links() {
		return transformer.links();
	}

	@Override
	public EntityWrapper entity() {
		return transformer.entity();
	}

	@Override
	public List<EntityWrapper> entities() {
		checkAndBuildEntities();
		return entities;
	}

	private void checkAndBuildEntities() {
		if (entities == null) {
			entities = new ArrayList<EntityWrapper>();
			for (EntityWrapper entityWrapper : transformer.entities()) {
				entities.add(entityWrapper);
			}
		}
	}

	@Override
	public void setHandler(PayloadHandler transformer) {
		this.transformer = transformer;
	}

	@Override
	public boolean isCollection() {
		return transformer.isCollection();
	}
}
