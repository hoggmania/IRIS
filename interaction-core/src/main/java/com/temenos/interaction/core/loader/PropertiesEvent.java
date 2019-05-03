package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Properties;

public interface PropertiesEvent<T> extends FileEvent<T> {
	public void accept(PropertiesEventVisitor<T> visitor);
	public Properties getNewProperties();
}
