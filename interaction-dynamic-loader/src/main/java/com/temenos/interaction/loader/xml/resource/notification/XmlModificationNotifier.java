package com.temenos.interaction.loader.xml.resource.notification;

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

import com.temenos.interaction.core.loader.FileEvent;
import com.temenos.interaction.loader.xml.resource.action.ResourceModificationAction;

public class XmlModificationNotifier implements ApplicationContextAware {
    private ApplicationContext ctx;
    private Collection<ResourceModificationAction> rmas;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
    
    public Set<String> getPatterns() {
    	Set<String> resourcePatterns = new HashSet<String>();
    	
		if(rmas == null) {
			rmas = ctx.getBeansOfType(ResourceModificationAction.class).values();
		}
    	    	
	    for(ResourceModificationAction rma: rmas) {
	    	resourcePatterns.add(rma.getResourcePattern());
		}
		
    	return resourcePatterns;
    }
    
	public void execute(FileEvent<Resource> event) {
		if(rmas == null) {
			rmas = ctx.getBeansOfType(ResourceModificationAction.class).values();
		}
		
		Iterator<ResourceModificationAction> rmaIter = rmas.iterator();
		
		while(rmaIter.hasNext()) {
			rmaIter.next().notify(event);
		}
	}
}
