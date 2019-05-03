package com.temenos.interaction.loader.properties;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Properties;

import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.PropertiesChangedEvent;
import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.core.loader.PropertiesEventVisitor;

public class PropertiesChangedEventImpl implements PropertiesEvent<Resource>, PropertiesChangedEvent<Resource> {

	final ReloadableProperties<Resource> target;
	final Resource resource;
	final Properties newProperties;
	
	public PropertiesChangedEventImpl(ReloadableProperties<Resource> target, Resource resource, Properties newProperties) {
		this.target = target;
		this.resource = resource;
		this.newProperties = newProperties; 
	}

	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.properties.PropertiesChangedEvent#getResource()
	 */
	@Override
	public Resource getResource() {
		return resource;
	}

	public ReloadableProperties<Resource> getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.properties.PropertiesChangedEvent#getNewProperties()
	 */
	@Override
	public Properties getNewProperties() {
		return newProperties;
	}

	/* (non-Javadoc)
	 * @see com.temenos.interaction.loader.properties.PropertiesChangedEvent#accept(com.temenos.interaction.core.loader.PropertiesEventVisitor)
	 */
	@Override
	public void accept(PropertiesEventVisitor<Resource> visitor) {
		visitor.visit(this);		
	}
	
}
