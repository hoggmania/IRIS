package com.temenos.interaction.core.loader;

import org.springframework.core.io.Resource;


/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



public interface PropertiesResourceModificationAction {
    public String getResourcePattern();
	public abstract void notify(PropertiesEvent<Resource> event);

}