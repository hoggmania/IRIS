package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class LazyCollectionResourceState extends CollectionResourceState {

	/**
	 * No details held other than the name, the rest is lazily loaded on demand.
	 * @param name
	 */
	public LazyCollectionResourceState(String name) {
		super("", name, null, "LAZY");
	}

	public String getName() {
		return super.getName();
	}

}
