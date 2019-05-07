package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Properties;

public interface PropertiesLoadedEvent<T> extends FileEvent<T> {

	public abstract void accept(PropertiesEventVisitor<T> visitor);

	public abstract Properties getNewProperties();

}