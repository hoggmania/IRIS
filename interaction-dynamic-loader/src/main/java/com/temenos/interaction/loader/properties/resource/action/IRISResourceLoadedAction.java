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
 * This class performs the necessary updates to load a new IRIS in memory resource from an underlying resource
 *
 * @author mlambert
 *
 */
public class IRISResourceLoadedAction implements Action<PropertiesEvent<Resource>> {
	private final Logger logger = LoggerFactory.getLogger(IRISResourceLoadedAction.class);	
	
	private SpringDSLResourceStateProvider resourceStateProvider;
		
	/**
	 * @param resourceStateProvider the resourceStateProvider to set
	 */
	public void setResourceStateProvider(SpringDSLResourceStateProvider resourceStateProvider) {
		this.resourceStateProvider = resourceStateProvider;
	}

	@Override
	public void execute(PropertiesEvent<Resource> event) {			
		logger.debug("Properties loaded: " + event.getNewProperties());

		for (Object key : event.getNewProperties().keySet()) {
			String name = key.toString();

			resourceStateProvider.addState(name, event.getNewProperties());
		}
	}
}
