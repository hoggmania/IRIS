package com.temenos.interaction.loader.properties.resource.action;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.Action;
import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.springdsl.SpringDSLResourceStateProvider;

/**
 * This class performs the necessary updates to IRIS in memory resource when the underlying resource changes
 *
 * @author mlambert
 *
 */
public class IRISResourceChangedAction implements Action<PropertiesEvent<Resource>> {
	private final Logger logger = LoggerFactory.getLogger(IRISResourceChangedAction.class);
	
	private SpringDSLResourceStateProvider resourceStateProvider;

	/**
	 * @param resourceStateProvider the resourceStateProvider to set
	 */
	public void setResourceStateProvider(SpringDSLResourceStateProvider resourceStateProvider) {
		this.resourceStateProvider = resourceStateProvider;
	}

	@Override
	public void execute(PropertiesEvent<Resource> event) {					
		logger.debug("Properties changed: " + event.getNewProperties());

		for (Object key : event.getNewProperties().keySet()) {
			String name = key.toString();

			// Unload resource state
			resourceStateProvider.unload(name);
		}
	}
}
