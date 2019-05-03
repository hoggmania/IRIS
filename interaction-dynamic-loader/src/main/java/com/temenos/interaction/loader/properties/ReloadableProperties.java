package com.temenos.interaction.loader.properties;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Properties;


/**
 * For Properties maps that notify about changes. Would extend interface
 * java.util.Properties if it were an interface. Classes implementing this
 * interface should consider extending {@link DelegatingProperties}. Credit to:
 * http://www.wuenschenswert.net/wunschdenken/archives/127
 */
public interface ReloadableProperties<T> {
	public Properties getProperties();

	void addReloadablePropertiesListener(ReloadablePropertiesListener<T> l);

	boolean removeReloadablePropertiesListener(ReloadablePropertiesListener<T> l);
}
