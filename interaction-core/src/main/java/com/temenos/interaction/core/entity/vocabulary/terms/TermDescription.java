package com.temenos.interaction.core.entity.vocabulary.terms;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.entity.vocabulary.Term;

/**
 * This term stores a description for the property
 * field
 */
public class TermDescription implements Term {
	public final static String TERM_NAME = "TERM_DESCRIPTION";

	private String description;

	public TermDescription(String description) {
		this.description = description;
	}

	@Override
	public String getValue() {
		return description;
	}

	@Override
	public String getName() {
		return TERM_NAME;
	}
}
