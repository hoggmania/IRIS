package com.temenos.interaction.loader.detector;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Collection;

/**
 * Interface for relating changes in a collection of resources with 
 * a collection of listeners.
 * 
 * @author andres
 * @author trojan
 */
public interface ResourceChangeDetector<RESOURCE, LISTENER> {
    public void setResources(Collection<? extends RESOURCE> resources);
    public void setListeners(Collection<? extends LISTENER> listeners);
}
