package com.temenos.interaction.loader.properties.resource.notification;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import com.temenos.interaction.core.loader.PropertiesEvent;
import com.temenos.interaction.core.loader.PropertiesResourceModificationAction;

public class PropertiesModificationNotifier implements ApplicationContextAware {
    private ApplicationContext ctx;
    private Collection<PropertiesResourceModificationAction> rmas;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
    
    public Set<String> getPatterns() {
    	Set<String> resourcePatterns = new HashSet<String>();
    	
		if(rmas == null) {
			rmas = ctx.getBeansOfType(PropertiesResourceModificationAction.class).values();
		}
    	    	
	    for(PropertiesResourceModificationAction rma: rmas) {
	    	resourcePatterns.add(rma.getResourcePattern());
		}
		
    	return resourcePatterns;
    }
    
	public void execute(PropertiesEvent<Resource> event) {
		if(rmas == null) {
			rmas = ctx.getBeansOfType(PropertiesResourceModificationAction.class).values();
		}
		
		Iterator<PropertiesResourceModificationAction> rmaIter = rmas.iterator();
		
		while(rmaIter.hasNext()) {
			rmaIter.next().notify(event);
		}
	}
}
