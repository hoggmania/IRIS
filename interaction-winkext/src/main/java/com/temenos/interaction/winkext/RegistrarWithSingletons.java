package com.temenos.interaction.winkext;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.wink.common.DynamicResource;
import org.apache.wink.common.WinkApplication;
import org.apache.wink.common.internal.registry.ProvidersRegistry;
import org.apache.wink.server.internal.registry.ResourceRegistry;
import org.apache.wink.spring.Registrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.rim.HTTPResourceInteractionModel;
import com.temenos.interaction.core.rim.ResourceInteractionModel;
import com.temenos.interaction.springdsl.RIMRegistration;

/**
 * Extend the Wink Spring support to be able to bind Providers such as JAXB / JSON.
 * This class requires the wink-spring-support be a 'compile' time dependency.
 * @author aphethean
 */
public class RegistrarWithSingletons extends Registrar implements RIMRegistration {
    private Set<Object> singletons = Collections.emptySet();
    
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrarWithSingletons.class);    

    // key = resourcePath
    private Map<String, DynamicResourceDelegate> resources = new HashMap<String, DynamicResourceDelegate>();
    
    ResourceRegistry resourceRegistry;    
    
    public RegistrarWithSingletons() {
        if (this.getInstances() == null) {
            this.setInstances(new HashSet<Object>());
        }
    }
    
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    public void setSingletons(final Set<Object> singletons) {
        this.singletons = singletons;
    }
    
    /**
     * Using a ServiceRootFactory get a set of service roots to bind to this instance of wink.
     */
    public void setServiceRootFactory(ServiceRootFactory drs) {
    	setServiceRoots(drs.getServiceRoots());    	
    }
        
    /**
     * @precondition Set<HTTPResourceInteractionModel> != null
     * @param rootRIMs
     */
    public void setServiceRoots(Set<HTTPResourceInteractionModel> rootRIMs) {
    	if (rootRIMs == null)
    		throw new IllegalArgumentException("Must provide a set of resource interaction models");
    	for (HTTPResourceInteractionModel rim : rootRIMs)
    		registerAll(rim);
    }

    public void setServiceRoot(HTTPResourceInteractionModel rootRIM) {
    	registerAll(rootRIM);
    }

    private void registerAll(ResourceInteractionModel rim) {
    	register((HTTPResourceInteractionModel) rim);
    	Collection<ResourceInteractionModel> children = rim.getChildren();
    	if (children != null) {
        	for (ResourceInteractionModel child : children) {
        	    register((HTTPResourceInteractionModel) child);
        	}
    	}
    }

    /**
     * Using the path lookup the resource; return null if a resource has not been previously registered.
     * @param path
     * @return
     */
    public HTTPResourceInteractionModel getDynamicResource(String path) {
    	return resources.get(path);
    }

	@Override
	public void register(HTTPResourceInteractionModel rim) {
		LOGGER.info("Attempting to add resource: " + rim.getResourcePath());		

    	assert this.getInstances() != null;
    	String rimKey = rim.getFQResourcePath();
    	if (resources.get(rimKey) != null)
    		return;
    	DynamicResource parent = null;
    	// is this a root resource
    	if (rim.getParent() != null) {
    		// climb back up the graph adding parent if necessary
        	String parentKey = rim.getParent().getFQResourcePath();
    		if (resources.get(parentKey) == null) {
    			register((HTTPResourceInteractionModel) rim.getParent());
    		}
    		parent = resources.get(parentKey);
    	}
    	
    	//Register the resource
    	HTTPResourceInteractionModel parentResource = parent != null ? (HTTPResourceInteractionModel) parent : null;
    	DynamicResourceDelegate dr = new DynamicResourceDelegate(parentResource, rim);
    	resources.put(rimKey, dr);
    	if(resourceRegistry != null) {
    	    resourceRegistry.addResource(dr, WinkApplication.DEFAULT_PRIORITY);
    	}
        this.getInstances().add(dr);
   	
    	//Ensure OData collection resources are available with and without empty brackets (e.g. /customers() and /customers)
    	if(rimKey.endsWith("()")) {
    		String pathWithoutBrackets = rimKey.substring(0, rimKey.length() - 2);
    		final DynamicResourceDelegate drWithoutBrackets = new DynamicResourceDelegate(parentResource, rim) {
    			@Override
    		    public String getPath() {
    				String resourcePath = super.getResourcePath();
    				return resourcePath.substring(0, resourcePath.length() - 2);
    		    }
        	};
        	resources.put(pathWithoutBrackets, drWithoutBrackets);
        	if(resourceRegistry != null) {
        	    resourceRegistry.addResource(drWithoutBrackets, WinkApplication.DEFAULT_PRIORITY);
        	}
            this.getInstances().add(drWithoutBrackets);
    	}
		
	}
	
	@Override
    public void register(ResourceRegistry resourceRegistry, ProvidersRegistry providersRegistry) {
    	super.register(resourceRegistry, providersRegistry);
    	this.resourceRegistry = resourceRegistry;
    }
	
}
