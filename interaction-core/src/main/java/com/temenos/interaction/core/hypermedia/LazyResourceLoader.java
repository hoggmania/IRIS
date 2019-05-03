package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


/**
 * This interface signals the ResourceFactory to call initialise after the
 * resource object has been instantiated.
 * @author aphethean
 */
public interface LazyResourceLoader {

	public boolean initialise();
	
}
