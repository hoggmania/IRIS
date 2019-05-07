package com.temenos.interaction.loader.properties;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;


/**
 * Useful base class for implementing {@link ReloadableProperties}. Credit to:
 * http://www.wuenschenswert.net/wunschdenken/archives/127
 */
public class ReloadablePropertiesBase extends DelegatingProperties implements ReloadableProperties<Resource> {
	private static final long serialVersionUID = 1882584866192427533L;
	private transient List<ReloadablePropertiesListener<Resource>> listeners = new ArrayList<>();
	private Properties internalProperties;

	public void setListeners(List<ReloadablePropertiesListener<Resource>> listeners) {
		this.listeners = listeners;
	}

	List<ReloadablePropertiesListener<Resource>> getListeners() {
        return this.listeners;
    }

    @Override
	protected Properties getDelegate() {
		synchronized (this) {
			return internalProperties;
		}
	}

	public Properties getProperties() {
		return getDelegate();
	}
	
	public void addReloadablePropertiesListener(ReloadablePropertiesListener<Resource> l) {
		listeners.add(l);
	}

	public boolean removeReloadablePropertiesListener(ReloadablePropertiesListener<Resource> l) {
		return listeners.remove(l);
	}

	protected void notifyPropertiesLoaded(Resource resource, Properties newProperties) {
		PropertiesLoadedEventImpl event = new PropertiesLoadedEventImpl(this, resource, newProperties);
		for (ReloadablePropertiesListener<Resource> listener : listeners) {
			listener.propertiesChanged(event);
		}
	}
	
	/*
	 * Adds any inexistent properties and updates the values of the existent ones 
	 */
	protected boolean updateProperties(Properties newProperties){
		synchronized (this) {
		    boolean newAdded = false;
		    for(Map.Entry<Object, Object> entry : newProperties.entrySet()) {
                if(internalProperties.put(entry.getKey(),  entry.getValue()) == null)
                    newAdded = true;
		    }
		    return newAdded;
		}
	}
	
	protected void notifyPropertiesChanged(Resource resource, Properties newProperties) {
		PropertiesChangedEventImpl event = new PropertiesChangedEventImpl(this, resource, newProperties);
		for (ReloadablePropertiesListener<Resource> listener : listeners) {
			listener.propertiesChanged(event);
		}
	}

	protected void setProperties(Properties properties) {
		synchronized (this) {
			internalProperties = properties;
		}
	}
}
