package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Properties;

public interface PropertiesChangedEvent<T> extends FileEvent<T> {
	public Properties getNewProperties();

	public void accept(PropertiesEventVisitor<T> visitor);

}