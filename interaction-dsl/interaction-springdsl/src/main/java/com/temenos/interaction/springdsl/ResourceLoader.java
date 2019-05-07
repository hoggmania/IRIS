package com.temenos.interaction.springdsl;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

import com.temenos.interaction.core.hypermedia.MethodNotAllowedException;
import com.temenos.interaction.core.hypermedia.PathTree;
import com.temenos.interaction.core.hypermedia.ResourceState;

/**
 * Simple interface which decides which ResourceSystemStateLoaded should be
 * called based on the spring property.
 *
 * @author mohamednazir
 *
 */
public interface ResourceLoader {
    
    public String getResourceStateId(String httpMethod, String url) throws MethodNotAllowedException;

    public void initialise(Properties beanMap, ConcurrentMap<String, ResourceState> resources, ResourceState result, PathTree pathTree);
    
    public void load(String state);

    public boolean isLoaded();

    public ResourceState loaded();

    public void setIrisConfigDirPath(String location);
    
}
