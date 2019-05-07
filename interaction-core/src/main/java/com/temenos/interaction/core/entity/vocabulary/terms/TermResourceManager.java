package com.temenos.interaction.core.entity.vocabulary.terms;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.entity.vocabulary.Term;

/**
 * This Term binds entities to a specific resource manager 
 */
public class TermResourceManager implements Term {
	public final static String TERM_NAME = "TERM_RESOURCE_MANAGER";

	private String resourceManager;
	
	public TermResourceManager(String resourceManager) {
		this.resourceManager = resourceManager;
	}
	
	@Override
	public String getValue() {
		return resourceManager;
	}

	@Override
	public String getName() {
		return TERM_NAME;
	}	
}
