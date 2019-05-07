package com.temenos.interaction.loader.properties;

import com.temenos.interaction.core.loader.PropertiesEvent;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 *
 */
public interface ReloadablePropertiesListener<T> {
	String[] getResourcePatterns();
	void propertiesChanged(PropertiesEvent<T> event);
}
