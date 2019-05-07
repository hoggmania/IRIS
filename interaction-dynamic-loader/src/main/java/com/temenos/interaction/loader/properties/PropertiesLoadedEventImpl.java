package com.temenos.interaction.loader.properties;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Properties;

import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.core.loader.PropertiesEventVisitor;
import com.temenos.interaction.core.loader.PropertiesLoadedEvent;

public class PropertiesLoadedEventImpl implements PropertiesEvent<Resource>, PropertiesLoadedEvent<Resource> {

	private final ReloadableProperties<Resource> target;
	private final Resource resource;
	private final Properties newProperties;
	
	public PropertiesLoadedEventImpl(ReloadableProperties<Resource> target, Resource resource, Properties newProperties) {
		this.target = target;
		this.resource = resource;
		this.newProperties = newProperties;
	}

	@Override
	public Resource getResource() {
		return resource;
	}

	public ReloadableProperties<Resource> getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.properties.PropertiesLoadedEvent#accept(com.temenos.interaction.core.loader.PropertiesEventVisitor)
	 */
	@Override
	public void accept(PropertiesEventVisitor<Resource> visitor) {
		visitor.visit(this);		
	}

	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.properties.PropertiesLoadedEvent#getNewProperties()
	 */
	@Override
	public Properties getNewProperties() {
		return newProperties;
	}
}
