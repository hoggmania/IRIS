package com.temenos.interaction.loader.xml;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.FileEvent;
import com.temenos.interaction.core.loader.XmlChangedEvent;

public class XmlChangedEventImpl implements FileEvent<Resource>, XmlChangedEvent {
	private final Resource resource;
		
	/**
	 * @param resource
	 */
	public XmlChangedEventImpl(Resource resource) {
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.xml.XmlChangedEvent#getResource()
	 */
	@Override
	public Resource getResource() {
		return resource;
	}
}
