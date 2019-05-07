package com.temenos.interaction.core.entity.vocabulary.terms;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.entity.vocabulary.Term;

/**
 * This term identifies whether or not an entity property is a language type
 * field
 */
public class TermLangType implements Term {
	public final static String TERM_NAME = "TERM_LANG_TYPE";

	private boolean isLangType;

	public TermLangType(boolean isLangType) {
		this.isLangType = isLangType;
	}

	/**
	 * Returns whether or not the property represented by this term is a
	 * language type field.
	 * 
	 * @return true if the property is a language type field, false otherwise
	 */
	public boolean isLanguageType() {
		return isLangType;
	}

	@Override
	public String getValue() {
		return Boolean.toString(isLangType);
	}

	@Override
	public String getName() {
		return TERM_NAME;
	}
}
