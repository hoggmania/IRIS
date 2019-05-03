package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public interface PropertiesEventVisitor<T> {
	void visit(PropertiesLoadedEvent<T> event);	
	void visit(PropertiesChangedEvent<T> event);	
}
