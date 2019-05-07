package com.temenos.interaction.loader.properties.resource.notification;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.loader.properties.ReloadablePropertiesListener;

public class PropertiesModificationListener implements ReloadablePropertiesListener<Resource>, ApplicationListener<ContextRefreshedEvent> {
	private boolean applicationInitialized = false;	
	private PropertiesModificationNotifier notifier;
	
	public void setNotifier(PropertiesModificationNotifier notifier) {
		this.notifier = notifier;
	}

	@Override
	public String[] getResourcePatterns() {
		return notifier.getPatterns().toArray(new String[0]);
	}

	@Override
	public void propertiesChanged(PropertiesEvent<Resource> event) {
		if(applicationInitialized) {
			notifier.execute(event);
		}
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		applicationInitialized = true;		
	}	
}
